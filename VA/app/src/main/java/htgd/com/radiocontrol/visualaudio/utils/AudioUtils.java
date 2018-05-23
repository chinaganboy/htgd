package htgd.com.radiocontrol.visualaudio.utils;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;


import com.broadcast.android.android_sta_jni_avsz.ndk_wrapper;

import htgd.com.radiocontrol.visualaudio.base.MyApplication;
import htgd.com.radiocontrol.visualaudio.thread.AioThreadPool;
import htgd.com.radiocontrol.visualaudio.thread.PcmThread;

/**
 * 音频解码工具类
 *
 * @author Administrator
 * @date 2017/12/6
 */

public class AudioUtils {

    private static final String TAG = AudioUtils.class.getSimpleName();

    private static PcmThread sPcmThread;
    private static AudioCapture sAudioCapture;
    private static int sStrm = 0;

    /**
     * 添加音频数据包
     *
     * @param channel 音频声道数，默认是1，当出现0时，音频帧数据包有误
     * @param data    音频数据包
     * @param sync    同步标记，1时同步（即清空队列）
     */
    public static void putAudioData(int channel, byte[] data, int sync) {
        if (sPcmThread != null) {
            sPcmThread.putData(channel, data, sync);
        }
    }

    /**
     * 开始音频数据播放
     *
     * @param sample  音频采样率
     * @param sync    同步标记：0：无   2：缓冲
     * @param isBroad 是否为广播
     * @param type    广播类型：0：MP3广播   1：语音广播
     */
    public static void startPcmThread(int sample, int sync, boolean isBroad, int type) {
        if (sPcmThread == null) {
            sPcmThread = new PcmThread(sample, sync, isBroad, type);
            AioThreadPool.getExecutor().execute(sPcmThread);
        } else {
            sPcmThread.resetParameter(sample, sync, isBroad, type);
        }
    }

    /**
     * 停止音频数据播放
     */
    public static void stopPcmThread() {
        if (sPcmThread != null) {
            sPcmThread.stopRun();
            sPcmThread.interrupt();
            sPcmThread = null;
        }
    }

    /**
     * 设置音频码流类型
     *
     * @param strm 码流类型
     */
    public static void setAudioStrm(int strm) {
        if (strm >= 0) {
            sStrm = strm;
        }
    }

    /**
     * 创建AudioTrack实例并开始采集音频数据
     *
     * @param sample 采样率
     * @return 是否在运行
     */
    public static boolean createAudioCapture(int sample) {
        if (sAudioCapture == null) {
            setMicMute();
            sAudioCapture = new AudioCapture();
            sAudioCapture.setOnAudioFrameCapturedListener((data, sample1) -> {
                int result = ndk_wrapper.getInstance().avsz_send_aud(0, sStrm, data, 1, 16,
                        sample1);
                Log.d(TAG, "[avsz_send_aud] result: " + result);
            });
            sAudioCapture.startCapture(sample);
            Log.d(TAG, "createAudioCapture");
        }
        return sAudioCapture.isCaptureStarted();
    }

    /**
     * 设置麦克风可录音
     */
    private static void setMicMute() {
        AudioManager audioManager = (AudioManager) MyApplication.getContext().getSystemService
                (Context.AUDIO_SERVICE);
        if (audioManager != null) {
            // 控制麦克风
            if (audioManager.isMicrophoneMute()) {
                audioManager.setMicrophoneMute(false);
            }
        }
    }

    /**
     * 销毁AudioTrack实例并停止音频数据采集
     */
    public static void destroyAudioCapture() {
        if (sAudioCapture != null && sAudioCapture.isCaptureStarted()) {
            sAudioCapture.stopCapture();
            sAudioCapture = null;
            Log.d(TAG, "destroyAudioCapture");
        }
    }

}
