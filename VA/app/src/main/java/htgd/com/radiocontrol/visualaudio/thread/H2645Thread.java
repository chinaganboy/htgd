package htgd.com.radiocontrol.visualaudio.thread;

import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import htgd.com.radiocontrol.visualaudio.utils.H2645Decoder;

/**
 * 视频播放线程
 *
 * @author Administrator
 * @date 2017/12/6
 */

public class H2645Thread extends Thread {

    private static final String TAG = H2645Thread.class.getSimpleName();

    private BlockingQueue<byte[]> mDataQueue;
    private H2645Decoder mDecoder;
    private boolean mIsRunning;
    private Surface mSurface;

    public H2645Thread(SurfaceView surfaceView) {
        if (surfaceView == null) {
            return;
        }
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mSurface = holder.getSurface();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // 界面已被销毁
                stopRun();
            }
        });
        mDataQueue = new ArrayBlockingQueue<>(25);
        mIsRunning = true;
    }

    /**
     * 添加视频帧数据包
     *
     * @param sessId 当前会话sessId
     * @param type   视频编码类型
     * @param data   视频帧数据包
     * @param width  视频宽
     * @param height 视频高
     * @param fps    视频帧率
     */
    public void putData(int sessId, String type, byte[] data, int width, int height, int fps) {
        if (!mIsRunning) {
            return;
        }
        if (mDecoder == null && mSurface != null) {
            // MediaFormat.MIMETYPE_VIDEO_AVC = "video/avc";
            // MediaFormat.MIMETYPE_VIDEO_HEVC = "video/hevc";
            mDecoder = new H2645Decoder(mSurface, type, width, height, fps);
            // 自定义本地广播消息，通知解码器已创建
           /* BroadcastUtil.sendLocalBroadcast(AioApplication.getContext(), new AppEvent(sessId,
                    "decoder_created", "x", "ok"));*/
        }
        if (mDecoder != null && mDataQueue != null) {
            mDataQueue.offer(data);
        }
    }

    @Override
    public void run() {
        super.run();
        byte[] data;
        while (mIsRunning) {
            try {
                data = mDataQueue.poll();
                if (data == null) {
                    Thread.sleep(5);
                    continue;
                }
                int i = 0;
                do {
                    if (mDecoder != null && !mDecoder.onFrame(data, 0, data.length)) {
                        i++;
                        Thread.sleep(1);
                    } else {
                        break;
                    }
                } while (i < 10 && mIsRunning);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mDataQueue.clear();
        mDataQueue = null;
        mSurface = null;
        Log.d(TAG, "h2645 thread stop!");
    }

    /**
     * 停止运行
     */
    public void stopRun() {
        mIsRunning = false;
        if (mDecoder != null) {
            mDecoder.stopDecode();
            mDecoder = null;
        }
    }

}
