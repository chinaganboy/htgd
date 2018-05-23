package htgd.com.radiocontrol.visualaudio.utils;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AutomaticGainControl;
import android.media.audiofx.NoiseSuppressor;
import android.util.Log;

import htgd.com.radiocontrol.visualaudio.thread.AioThreadPool;


/**
 * COPYRIGHT NOTICE
 * Copyright (C) 2016, Jhuster <lujun.hust@gmail.com>
 * https://github.com/Jhuster/Android
 *
 * @author Jhuster
 * @version 1.0
 * @license under the Apache License, Version 2.0
 * @file AudioCapture.java
 * @date 2016/03/10
 */
public class AudioCapture {

    private static final String TAG = AudioCapture.class.getSimpleName();
    private static final int DEFAULT_SOURCE = MediaRecorder.AudioSource.MIC;
    private static final int DEFAULT_CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO;
    private static final int DEFAULT_AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;

    public static int DEFAULT_SAMPLE_RATE = 44100;

    private AudioRecord mAudioRecord;
    private int mMinBufferSize;
    private boolean mIsCaptureStarted = false;
    private boolean recordFlag = false;
    private OnAudioFrameCapturedListener mAudioFrameCapturedListener;
    private AudioCaptureRunnable mCaptureRunnable;

    /**
     * 音频帧数据回调接口
     */
    public interface OnAudioFrameCapturedListener {

        /**
         * 音频帧数据回调
         *
         * @param data   音频帧数据
         * @param sample 采样率
         */
        void onAudioFrameCaptured(byte[] data, int sample);

    }

    /**
     * 是否在采集音频
     *
     * @return true是/false否
     */
    public boolean isCaptureStarted() {
        return mIsCaptureStarted;
    }

    /**
     * 设置音频帧数据回调监听
     *
     * @param listener 监听器
     */
    public void setOnAudioFrameCapturedListener(OnAudioFrameCapturedListener listener) {
        mAudioFrameCapturedListener = listener;
    }

    /**
     * * 创建AudioTrack实例并开始音频采播
     *
     * @return 是否创建成功
     */
    public boolean startCapture() {
        Log.d(TAG, "startCapture");
        return startCapture(DEFAULT_SOURCE, DEFAULT_SAMPLE_RATE, DEFAULT_CHANNEL_CONFIG,
                DEFAULT_AUDIO_FORMAT);
    }

    /**
     * 创建AudioTrack实例并开始音频采播
     *
     * @param sample 采样率
     * @return 是否创建成功
     */
    public boolean startCapture(int sample) {
        Log.d(TAG, "startCapture sample: " + sample);
        DEFAULT_SAMPLE_RATE = sample;
        return startCapture(DEFAULT_SOURCE, DEFAULT_SAMPLE_RATE, DEFAULT_CHANNEL_CONFIG,
                DEFAULT_AUDIO_FORMAT);
    }

    /**
     * 创建AudioTrack实例并开始音频采播
     *
     * @param audioSource    音源设备
     * @param sampleRateInHz 采样率
     * @param channelConfig  声道配置
     * @param audioFormat    音频格式
     * @return 是否创建成功
     */
    public boolean startCapture(int audioSource, int sampleRateInHz, int channelConfig, int
            audioFormat) {
        Log.d(TAG, "startCapture audioSource: " + audioSource + ", sample: " + sampleRateInHz +
                ", " +
                "channelConfig: " + channelConfig + ", audioFormat: " + audioFormat);
        if (mIsCaptureStarted) {
            return false;
        }
        mMinBufferSize = AudioRecord.getMinBufferSize(DEFAULT_SAMPLE_RATE, channelConfig,
                audioFormat);
        if (mMinBufferSize == AudioRecord.ERROR_BAD_VALUE) {
            Log.d(TAG, "Invalid parameter !");
            return false;
        }
        Log.d(TAG, "getMinBufferSize: " + mMinBufferSize + " bytes!");

        if (mCaptureRunnable == null) {
            mCaptureRunnable = new AudioCaptureRunnable();
            AioThreadPool.getExecutor().execute(mCaptureRunnable);
        }

        mAudioRecord = new AudioRecord(audioSource,
                DEFAULT_SAMPLE_RATE,
                channelConfig,
                audioFormat,
                mMinBufferSize * 2);
        if (mAudioRecord.getState() == AudioRecord.STATE_UNINITIALIZED) {
            Log.d(TAG, "AudioRecord initialize fail !");
            return false;
        }

        if (isAECAvailable()) {
            Log.d(TAG, "AEC enable.");
            AcousticEchoCanceler.create(mAudioRecord.getAudioSessionId()).setEnabled(true);
        }

        if (isNSAvailable()) {
            Log.d(TAG, "NS enable.");
            NoiseSuppressor.create(mAudioRecord.getAudioSessionId()).setEnabled(true);
        }

        if (isAGCAvailable()) {
            Log.d(TAG, "AGC enable.");
            AutomaticGainControl.create(mAudioRecord.getAudioSessionId()).setEnabled(true);
        }

        mAudioRecord.startRecording();

        mIsCaptureStarted = true;
        recordFlag = true;

        Log.d(TAG, "Start audio capture success!");

        return true;
    }

    /**
     * 停止音频采播
     */
    public void stopCapture() {
        if (!mIsCaptureStarted) {
            return;
        }

        if (mCaptureRunnable != null) {
            mCaptureRunnable.exit();
            mCaptureRunnable = null;
        }

        if (mAudioRecord.getRecordingState() == AudioRecord.RECORDSTATE_RECORDING) {
            recordFlag = false;
            mAudioRecord.release();
        }

        mAudioRecord = null;
        mIsCaptureStarted = false;
        mAudioFrameCapturedListener = null;

        Log.d(TAG, "Stop audio capture success!");
    }

    /**
     * 音频采集线程Runnable
     */
    private class AudioCaptureRunnable implements Runnable {

        private boolean mIsLoopExit = false;
        private int mRet;
        private byte[] mData = new byte[mMinBufferSize];

        @Override
        public void run() {
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_URGENT_AUDIO);

            while (!mIsLoopExit) {
                if (null != mAudioRecord && recordFlag) {
                    mRet = mAudioRecord.read(mData, 0, mMinBufferSize);
                   /* if (mRet == AudioRecord.ERROR_INVALID_OPERATION || mRet == AudioRecord
                            .ERROR_BAD_VALUE || mRet == AudioRecord.ERROR_DEAD_OBJECT || mRet ==
                            AudioRecord.ERROR) {
                        continue;
                    }*/
                    if (mAudioFrameCapturedListener != null) {
                        mAudioFrameCapturedListener.onAudioFrameCaptured(mData,
                                DEFAULT_SAMPLE_RATE);
                    }
                }
            }
        }

        /**
         * 退出循环
         */
        public void exit() {
            mIsLoopExit = true;
        }

    }

    /**
     * 是否支持回音消除，API文档地址：http://android.toolib.net/reference/android/media/
     * audiofx/AcousticEchoCanceler.html
     *
     * @return 是否支持
     */
    private static boolean isAECAvailable() {
        return AcousticEchoCanceler.isAvailable();
    }

    /**
     * 是否支持静音消除
     *
     * @return 是否支持
     */
    private static boolean isNSAvailable() {
        return NoiseSuppressor.isAvailable();
    }

    /**
     * 是否支持自动增益
     *
     * @return 是否支持
     */
    private static boolean isAGCAvailable() {
        return AutomaticGainControl.isAvailable();
    }

}