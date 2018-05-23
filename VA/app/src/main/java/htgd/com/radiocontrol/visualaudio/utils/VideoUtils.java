package htgd.com.radiocontrol.visualaudio.utils;

import android.view.SurfaceView;

import htgd.com.radiocontrol.visualaudio.thread.AioThreadPool;
import htgd.com.radiocontrol.visualaudio.thread.H2645Thread;


/**
 * 视频解码工具类
 *
 * @author Administrator
 * @date 2017/12/6
 */

public class VideoUtils {

    private static final String TAG = VideoUtils.class.getSimpleName();

    private static H2645Thread sH2645Thread;

    /**
     * 添加视频数据包
     *
     * @param sessId 当前会话sessId
     * @param type   视频编码类型
     * @param data   视频帧数据包
     * @param width  视频宽
     * @param height 视频高
     * @param fps    视频帧率
     */
    public static void putVideoData(int sessId, String type, byte[] data, int width, int height,
                                    int fps) {
        if (sH2645Thread != null) {
            sH2645Thread.putData(sessId, type, data, width, height, fps);
        }
    }

    /**
     * 增加视频数据播放
     *
     * @param surfaceView 视频播放用SurfaceView
     */
    public static void startH2645Thread(SurfaceView surfaceView) {
        if (sH2645Thread == null && surfaceView != null) {
            sH2645Thread = new H2645Thread(surfaceView);
            AioThreadPool.getExecutor().execute(sH2645Thread);
        }
    }

    /**
     * 移除视频数据播放
     */
    public static void stopH2645Thread() {
        if (sH2645Thread != null) {
            sH2645Thread.stopRun();
            sH2645Thread.interrupt();
            sH2645Thread = null;
        }
    }

}
