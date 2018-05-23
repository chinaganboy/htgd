package htgd.com.radiocontrol.visualaudio.thread;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 音频播放线程
 *
 * @author Administrator
 * @date 2017/12/6
 */

public class PcmThread extends Thread {

    private static final String TAG = "PcmThread";
    private static final int AUDIO_DEFAULT_BUFFER_SIZE = 15;
    private static final int AUDIO_BROAD_BUFFER_SIZE = 50;

    private BlockingQueue<byte[]> mDataQueue;
    private AudioTrack mAudioTrack;
    private boolean mIsRunning;
    /**
     * 采样率
     */
    private int mSample;
    private boolean mIsBroad;
    private int mSync;
    /**
     * 广播类型：0[MP3广播]   1[语音广播]
     */
    private int mType;

    public PcmThread(int sample, int sync, boolean isBroad, int type) {
        mIsBroad = isBroad;
        mSync = sync;
        mType = type;
        //初始化保存参数
        if (mAudioTrack == null) {
            createAudioTrack(sample);
        }
        setQueue();
        mIsRunning = true;
    }

    /**
     * 销毁音频播放AudioTrack
     */
    private void destroyAudioTrack() {
        try {
            if (mAudioTrack != null) {
                mAudioTrack.stop();
                mAudioTrack.release();
                mAudioTrack = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置缓冲队列类型
     */
    private void setQueue() {
        if (mIsBroad) {
            // 缓冲模式，列队最大
            if (mSync == 2) {
                mDataQueue = new LinkedBlockingDeque<>();
                Log.d(TAG, "set queue type: buffer, size: infinite!");
            } else {
                // MP3广播队列大小 > 语音广播队列大小，语音广播包括语音合成、实时采播
                mDataQueue = new ArrayBlockingQueue<>(mType == 0 ? AUDIO_BROAD_BUFFER_SIZE :
                        AUDIO_DEFAULT_BUFFER_SIZE);
                Log.d(TAG, "set queue type: broad, size: " + (mType == 0 ? AUDIO_BROAD_BUFFER_SIZE :
                        AUDIO_DEFAULT_BUFFER_SIZE) + "!");
            }
        } else {
            // 对讲的队列大小不应过大
            mDataQueue = new ArrayBlockingQueue<>(AUDIO_DEFAULT_BUFFER_SIZE);
            Log.d(TAG, "set queue type: default, size: " + AUDIO_DEFAULT_BUFFER_SIZE + "!");
        }
    }

    /**
     * 添加音频数据包
     *
     * @param channel 音频声道数，默认是1，当出现0时，音频帧数据包有误
     * @param data  音频数据包
     * @param sync    同步标记，1时同步（即清空队列）
     */
    public void putData(int channel, byte[] data, int sync) {
        if (!mIsRunning || data == null) {
            return;
        }
        if (channel != 0 && mDataQueue != null) {
            // 同步标记
            if (sync == 1) {
                Log.d(TAG, "queue size: " + mDataQueue.size() + ", sync clear!!!");
                if (mDataQueue.size() > 0) {
                    mDataQueue.clear();
                }
            }
            // 缓冲区溢出或同步
            if (mSync == 0 && mDataQueue.size() == (mIsBroad ? (mType == 0 ?
                    AUDIO_BROAD_BUFFER_SIZE : AUDIO_DEFAULT_BUFFER_SIZE) :
                    AUDIO_DEFAULT_BUFFER_SIZE)) {
                // 如果size达到最大值，先清空（FIFO）
                mDataQueue.poll();
            }
            mDataQueue.offer(data);
        }
    }

    /**
     * 创建音频播放AudioTrack
     *
     * @param sample 采样率
     */
    private void createAudioTrack(int sample) {
        int bufferSize = AudioTrack.getMinBufferSize(
                sample,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT);
        Log.d(TAG, "getMinBufferSize: " + bufferSize + " bytes!");
        mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                sample,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                bufferSize,
                AudioTrack.MODE_STREAM);
        mAudioTrack.play();
        mSample = sample;
    }

    /**
     * 重置参数
     *
     * @param sample  采样率
     * @param sync    策略标记
     * @param isBroad 新的广播状态值
     * @param type    广播类型：0：MP3广播   1：语音广播
     */
    public void resetParameter(int sample, int sync, boolean isBroad, int type) {
        if (isBroad != mIsBroad) {
            Log.d(TAG, "reset broad: " + isBroad + ", original broad: " + mIsBroad);
            mIsBroad = isBroad;
        }
        if (mSample != sample) {
            Log.d(TAG, "reset sample: " + sample + ", original sample: " + mSample);
            destroyAudioTrack();
            createAudioTrack(sample);
        }
        if (sync != mSync || mType != type) {
            mSync = sync;
            mType = type;
            setQueue();
        }
    }

    @Override
    public void run() {
        super.run();
        byte[] data;
        while (mIsRunning) {
            try {
                if (mDataQueue != null) {
                    data = mDataQueue.poll();
                    if (data == null) {
                        Thread.sleep(5);
                        continue;
                    }
                    if (mAudioTrack != null && mIsRunning) {
                        int result = mAudioTrack.write(data, 0, data.length);
                        /*if (result == AudioRecord.ERROR_INVALID_OPERATION || result ==
                                AudioRecord.ERROR_BAD_VALUE || result == AudioRecord
                                .ERROR_DEAD_OBJECT || result == AudioRecord.ERROR) {
                            Log.d(TAG, "audio track write error result: " + result);
                        }*///H
                    }
                } else {
                    Thread.sleep(10);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (mDataQueue != null) {
            mDataQueue.clear();
            mDataQueue = null;
        }
        Log.d(TAG, "pcm thread stop!");
    }

    /**
     * 停止运行
     */
    public void stopRun() {
        mIsRunning = false;
        destroyAudioTrack();
    }

}
