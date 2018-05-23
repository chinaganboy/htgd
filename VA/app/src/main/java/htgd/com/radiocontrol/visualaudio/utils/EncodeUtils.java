package htgd.com.radiocontrol.visualaudio.utils;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.util.Log;


import com.broadcast.android.android_sta_jni_avsz.ndk_wrapper;

import java.io.IOException;
import java.nio.ByteBuffer;


import htgd.com.radiocontrol.visualaudio.base.MyApplication;
import htgd.com.radiocontrol.visualaudio.encode.EncoderDebugger;
import htgd.com.radiocontrol.visualaudio.encode.NV21Convertor;

/**
 * 视频编码工具类
 *
 * @author Administrator
 * @date 2018/1/22
 */

public class EncodeUtils {

    private static final String TAG = EncodeUtils.class.getSimpleName();
    private static final Object ENCODE_LOCK = new Object();

    private static NV21Convertor sConvertor;
    private static MediaCodec sMediaCodec;
    private static byte[] sSpsPpsData;
    /**
     * 码流类型，默认为主码流
     */
    private static int sStrm = 0;

    /**
     * 初始化视频编码器
     *
     * @param debugger 编码器调试器实例
     */
    private static void initMediaCodec(EncoderDebugger debugger) {
        if (debugger != null) {
            try {
                int bitrate = 2 * CameraUtils.VIDEO_WIDTH * CameraUtils.VIDEO_HEIGHT * CameraUtils
                        .VIDEO_FRAME_RATE / 20;
                sMediaCodec = MediaCodec.createByCodecName(debugger.getEncoderName());
                MediaFormat mediaFormat = MediaFormat.createVideoFormat("video/avc",
                        CameraUtils.VIDEO_WIDTH, CameraUtils.VIDEO_HEIGHT);
                mediaFormat.setInteger(MediaFormat.KEY_BIT_RATE, bitrate);
                mediaFormat.setInteger(MediaFormat.KEY_MAX_INPUT_SIZE, 3 * CameraUtils
                        .VIDEO_WIDTH * CameraUtils.VIDEO_HEIGHT / 2);
                mediaFormat.setInteger(MediaFormat.KEY_FRAME_RATE, CameraUtils.VIDEO_FRAME_RATE);
                mediaFormat.setInteger(MediaFormat.KEY_COLOR_FORMAT, debugger
                        .getEncoderColorFormat());
                mediaFormat.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 25);
                sMediaCodec.configure(mediaFormat, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);
                sMediaCodec.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 开始视频编码
     */
    public static void startEncode() {
        if (sConvertor == null) {
            EncoderDebugger debugger = EncoderDebugger.debug(MyApplication.getContext(),
                    CameraUtils.VIDEO_WIDTH, CameraUtils.VIDEO_HEIGHT);
            sConvertor = debugger.getNV21Convertor();
            initMediaCodec(debugger);
        }
    }

    /**
     * 停止视频编码
     */
    public static void stopEncode() {
        synchronized (ENCODE_LOCK) {
            if (sConvertor != null) {
                sConvertor.release();
                sConvertor = null;
            }
            if (sMediaCodec != null) {
                sMediaCodec.release();
                sMediaCodec = null;
            }
            sSpsPpsData = null;
        }
    }

    /**
     * 设置视频码流类型
     *
     * @param strm 码流参数
     */
    public static void setVideoStrm(int strm) {
        if (strm >= 0) {
            sStrm = strm;
        }
    }

    /**
     * 编码相机数据为H264数据
     *
     * @param data 相机数据
     */
    public static void encodeH264Data(byte[] data) {
        synchronized (ENCODE_LOCK) {
            if (sMediaCodec != null) {
                ByteBuffer[] inputBuffers = sMediaCodec.getInputBuffers();
                ByteBuffer[] outputBuffers = sMediaCodec.getOutputBuffers();
                int bufferIndex = sMediaCodec.dequeueInputBuffer(0);
                if (bufferIndex >= 0) {
                    inputBuffers[bufferIndex].clear();
                    sConvertor.convert(data, inputBuffers[bufferIndex]);
                    sMediaCodec.queueInputBuffer(bufferIndex, 0, inputBuffers[bufferIndex]
                            .position(), System.nanoTime() / 1000, 0);

                    int outputBufferIndex;
                    MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                    while ((outputBufferIndex = sMediaCodec.dequeueOutputBuffer(bufferInfo, 0))
                            >= 0) {
                        ByteBuffer outputBuffer = outputBuffers[outputBufferIndex];
                        byte[] datas = new byte[bufferInfo.size];
                        outputBuffer.get(datas);
                        makeSpsPps(datas);

                        sMediaCodec.releaseOutputBuffer(outputBufferIndex, false);
                        bufferInfo = new MediaCodec.BufferInfo();
                    }
                } else {
                    sMediaCodec.flush();
                }
            }
        }
    }

    /**
     * 关键帧前面加上sps/pps
     *
     * @param data H264编码后的数据
     */
    private static void makeSpsPps(byte[] data) {
        if (data == null) {
            return;
        }

        int keyframe = 2;
        //记录pps和sps
        if (sSpsPpsData == null) {
            ByteBuffer buffer = ByteBuffer.wrap(data);
            if (buffer.getInt() == 0x00000001) {
                sSpsPpsData = new byte[data.length];
                System.arraycopy(data, 0, sSpsPpsData, 0, data.length);
                Log.d(TAG, "get sps pps data.");
                return;
            }
        }

        if ((data[3] & 0x1f) == 5 || (data[4] & 0x1f) == 5) {
            if (sSpsPpsData == null) {
                return;
            }

            // 关键帧
            keyframe = 1;
            byte[] datas = new byte[sSpsPpsData.length + data.length];
            System.arraycopy(sSpsPpsData, 0, datas, 0, sSpsPpsData.length);
            System.arraycopy(data, 0, datas, sSpsPpsData.length, data.length);
            data = datas;
        }
        int result = ndk_wrapper.getInstance().avsz_send_vid(0, sStrm, data, keyframe, CameraUtils
                .VIDEO_WIDTH, CameraUtils.VIDEO_HEIGHT, CameraUtils.VIDEO_FRAME_RATE);
        Log.d(TAG, "[avsz_send_vid] result: " + result);
    }

}
