package htgd.com.radiocontrol.visualaudio.utils;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.view.Surface;

import java.nio.ByteBuffer;

/**
 * H264视频解码工具类
 *
 * @author Administrator
 * @date 2017/12/06
 */
public class H2645Decoder {

    private static final String TAG = H2645Decoder.class.getSimpleName();

    private static int HEAD_OFFSET = 512;

    private MediaCodec mCodec;
    private MediaCodec.BufferInfo mBufferInfo;
    private ByteBuffer mInputBuffer;
    private ByteBuffer[] mInputBuffers;
    private long mIndex;
    private int mFps;

    public H2645Decoder(Surface surface, String mimeType, int width, int height, int fps) {
        try {
            mFps = fps;
            mCodec = MediaCodec.createDecoderByType(mimeType);
            MediaFormat mediaFormat = MediaFormat.createVideoFormat(mimeType, width, height);
            mediaFormat.setInteger(MediaFormat.KEY_MAX_INPUT_SIZE, 3 * width * height / 2);
            mediaFormat.setInteger(MediaFormat.KEY_FRAME_RATE, fps);
            mCodec.configure(mediaFormat, surface, null, 0);
            mCodec.start();

            mBufferInfo = new MediaCodec.BufferInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解码一帧数据
     *
     * @param buf    帧数据包
     * @param offset 偏移量
     * @param length 数据长度
     * @return 是否解码成功
     */
    public boolean onFrame(byte[] buf, int offset, int length) {
        try {
            if (mCodec != null) {
                mInputBuffers = mCodec.getInputBuffers();
                int inputBufferIndex = mCodec.dequeueInputBuffer(500000);
                if (inputBufferIndex >= 0) {
                    mInputBuffer = mInputBuffers[inputBufferIndex];
                    mInputBuffer.clear();
                    mInputBuffer.put(buf, offset, length);
                    mCodec.queueInputBuffer(inputBufferIndex, 0, length, mIndex++ * 1000000 /
                            mFps, 0);
                    dequeueAndRenderOutputBuffer(0);
                } else {
                    mCodec.flush();
                    return false;
                }

                if ((mBufferInfo.flags & MediaCodec.BUFFER_FLAG_END_OF_STREAM) != 0) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 解码视频帧并显示
     *
     * @param outTime 超时时间
     * @return 是否成功
     */
    private boolean dequeueAndRenderOutputBuffer(int outTime) {
        if (mCodec != null && mBufferInfo != null) {
            int outIndex = mCodec.dequeueOutputBuffer(mBufferInfo, outTime);
            switch (outIndex) {
                case MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED:
                case MediaCodec.INFO_OUTPUT_FORMAT_CHANGED:
                case MediaCodec.INFO_TRY_AGAIN_LATER:
                    return false;
                default:
                    mCodec.releaseOutputBuffer(outIndex, true);
                    return true;
            }
        }
        return false;
    }

    /**
     * Find H264 frame head
     *
     * @param buffer 数据集
     * @param len    长度
     * @return the offset of frame head, return 0 if can not find one
     */
    public int findHead(byte[] buffer, int len) {
        int i;
        for (i = HEAD_OFFSET; i < len; i++) {
            if (checkHead(buffer, i)) {
                break;
            }
        }
        if (i == len) {
            return 0;
        }
        if (i == HEAD_OFFSET) {
            return 0;
        }
        return i;
    }

    /**
     * Check if is H264 frame head
     *
     * @param buffer 数据集
     * @param offset 偏移量
     * @return whether the src buffer is frame head
     */
    public boolean checkHead(byte[] buffer, int offset) {
        // 00 00 00 01
        if (buffer[offset] == 0 && buffer[offset + 1] == 0
                && buffer[offset + 2] == 0 && buffer[3] == 1) {
            return true;
        }
        // 00 00 01
        if (buffer[offset] == 0 && buffer[offset + 1] == 0
                && buffer[offset + 2] == 1) {
            return true;
        }
        return false;
    }

    /**
     * 停止解码
     */
    public void stopDecode() {
        try {
            if (mCodec != null) {
                mCodec.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mCodec = null;
        }
    }

}
