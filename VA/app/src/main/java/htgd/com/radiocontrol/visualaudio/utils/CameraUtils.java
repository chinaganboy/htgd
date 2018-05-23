package htgd.com.radiocontrol.visualaudio.utils;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;



import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import htgd.com.radiocontrol.visualaudio.base.MyApplication;

/**
 * 相机工具类
 *
 * @author Administrator
 * @date 2017/12/7
 */

public class CameraUtils {

    private static final String TAG = "CameraUtils";

    public static final int VIDEO_FRAME_RATE = 25;

    private static CameraManager sCameraManager;
    private static WindowManager sWindowManager;

    public static int VIDEO_WIDTH = 640;
    public static int VIDEO_HEIGHT = 480;

    /**
     * 创建相机服务
     *
     * @return 是否初始化成功
     */
    public static boolean createCameraService(int cameraId) {
        if (sWindowManager == null) {
            sWindowManager = (WindowManager) MyApplication.getContext()
                    .getSystemService(Context.WINDOW_SERVICE);
            SurfaceView surfaceView = new SurfaceView(MyApplication.getContext());
            surfaceView.setKeepScreenOn(true);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(1, 1,
                    WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                    PixelFormat.TRANSLUCENT);
            layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
            sWindowManager.addView(surfaceView, layoutParams);
            if (sCameraManager == null) {
                sCameraManager = new CameraManager(surfaceView, cameraId, cameraId1 -> {
                    Log.d(TAG, "recreate camera service!!!");
                    UiUtils.runOnUiThread(() -> {
                        destroyCameraService();
                        createCameraService(cameraId1);
                    });
                });
                Log.d(TAG, "createCameraService");
            }
            return true;
        }
        return false;
    }

    /**
     * 销毁相机服务
     */
    public static void destroyCameraService() {
        if (sWindowManager != null && sCameraManager != null) {
            sWindowManager.removeViewImmediate(sCameraManager.getSurfaceView());
            Log.d(TAG, "remove surfaceView");
            sWindowManager = null;
            sCameraManager = null;
            Log.d(TAG, "destroyCameraService");
        }
    }

    /**
     * 相机管理者
     */
    private static class CameraManager {

        private static final String TAG = "CameraManager";
        private static final int OPEN_CAMERA = 1;
        private static final int CLOSE_CAMERA = 0;
        private static final Object CAMERA_LOCK = new Object();

        private Camera mCamera;
        private int mCameraId;
        private boolean mRunning;
        private HandlerThread mHandlerThread;
        private Handler mHandler;
        private SurfaceView mSurfaceView;
        private ErrorCallBack mCallBack;

        public CameraManager(SurfaceView surfaceView, int cameraId, ErrorCallBack callBack) {
            mCameraId = cameraId;
            mCallBack = callBack;
            if (mHandlerThread == null) {
                mHandlerThread = new HandlerThread("camera-thread");
                mHandlerThread.start();
            }
            if (mHandler == null) {
                mHandler = new Handler(mHandlerThread.getLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what) {
                            case OPEN_CAMERA:
                                openCamera((SurfaceHolder) msg.obj);
                                break;
                            case CLOSE_CAMERA:
                                releaseCamera();
                                mHandlerThread = null;
                                mHandler = null;
                                break;
                            default:
                                break;
                        }
                    }
                };
            }
            if (surfaceView != null) {
                mSurfaceView = surfaceView;
                surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
                    @Override
                    public void surfaceCreated(SurfaceHolder holder) {
                        if (mHandler != null) {
                            Message message = Message.obtain();
                            message.what = OPEN_CAMERA;
                            message.obj = holder;
                            mHandler.sendMessage(message);
                        }
                    }

                    @Override
                    public void surfaceChanged(SurfaceHolder holder, int format, int width, int
                            height) {

                    }

                    @Override
                    public void surfaceDestroyed(SurfaceHolder holder) {
                        if (mHandler != null) {
                            Message message = Message.obtain();
                            message.what = CLOSE_CAMERA;
                            mHandler.sendMessage(message);
                        }
                    }
                });
            }
        }

        /**
         * 获取相机预览用SurfaceView
         *
         * @return SurfaceView
         */
        public SurfaceView getSurfaceView() {
            return mSurfaceView;
        }

        /**
         * 设置错误监听器
         *
         * @param callBack 监听器
         */
        public void setErrorCallBack(ErrorCallBack callBack) {
            mCallBack = callBack;
        }

        /**
         * 打开相机
         *
         * @param holder 预览用SurfaceHolder
         */
        private void openCamera(SurfaceHolder holder) {
            releaseCamera();
            try {
                int numberOfCameras = Camera.getNumberOfCameras();
                if (mCameraId > (numberOfCameras - 1)) {
                    Log.d(TAG, "numberOfCameras: out of index");
                    return;
                }
                mCamera = Camera.open(mCameraId);
                Log.d(TAG, "openCamera");
                initCamera(holder);
                startPreview();
            } catch (RuntimeException | IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 初始化Camera
         *
         * @param holder 预览用SurfaceHolder
         */
        private void initCamera(SurfaceHolder holder) throws IOException {
            if (mCamera != null) {
                mCamera.setPreviewDisplay(holder);
                mCamera.setErrorCallback((error, camera) -> {
                    switch (error) {
                        case Camera.CAMERA_ERROR_UNKNOWN:
                            Log.d(TAG, "camera error unknown");
                            break;
                        case Camera.CAMERA_ERROR_SERVER_DIED:
                            Log.d(TAG, "camera error server died");
                            if (mCallBack != null) {
                                mCallBack.onError(mCameraId);
                            }
                            break;
                        default:
                            break;
                    }
                });

                Camera.Parameters parameters = mCamera.getParameters();
                Camera.CameraInfo camInfo = new Camera.CameraInfo();
                Camera.getCameraInfo(mCameraId, camInfo);
                int orientation = camInfo.orientation;
                parameters.setRotation((360 + orientation - 180) % 360);
                parameters.setPreviewFormat(ImageFormat.NV21);
                List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();
                for (int i = 0; i < sizes.size(); i++) {
                    Camera.Size size = sizes.get(i);
                    Log.d(TAG, "supported preview size: " + size.width + " x " + size.height);
                    // 支持的分辨率可自行按需设置！
                    if (i == 0) {
                        VIDEO_WIDTH = size.width;
                        VIDEO_HEIGHT = size.height;
                    }
                }
                Log.d(TAG, "set preview size: " + VIDEO_WIDTH + " x " + VIDEO_HEIGHT);
                parameters.setPreviewSize(VIDEO_WIDTH, VIDEO_HEIGHT);
                int[] max = determineMaximumSupportedFramerate(parameters);
                parameters.setPreviewFpsRange(max[0], max[1]);
                mCamera.setParameters(parameters);
                mCamera.autoFocus(null);
                mCamera.setDisplayOrientation(0);
            }
        }

        /**
         * 获取支持的帧率范围
         *
         * @param parameters 相机参数集
         * @return 帧率范围
         */
        private int[] determineMaximumSupportedFramerate(Camera.Parameters parameters) {
            int[] maxFps = new int[]{0, 0};
            List<int[]> supportedFpsRanges = parameters.getSupportedPreviewFpsRange();
            for (Iterator<int[]> it = supportedFpsRanges.iterator(); it.hasNext(); ) {
                int[] interval = it.next();
                if (interval[1] > maxFps[1] || (interval[0] > maxFps[0] && interval[1] ==
                        maxFps[1])) {
                    maxFps = interval;
                }
            }
            return maxFps;
        }

        /**
         * 释放相机
         */
        private void releaseCamera() {
            if (mCamera != null) {
                Log.d(TAG, "releaseCamera");
                stopPreview();
                try {
                    mCamera.setPreviewDisplay(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mCamera.setPreviewCallback(null);
                mCamera.setPreviewCallbackWithBuffer(null);
                mCamera.release();
                mCamera = null;
            }
        }

        /**
         * 开始预览
         */
        private void startPreview() {
            synchronized (CAMERA_LOCK) {
                EncodeUtils.startEncode();
                if (!mRunning && mCamera != null) {
                    Log.d(TAG, "startPreview");
                    mCamera.startPreview();
                    Camera.Size previewSize = mCamera.getParameters().getPreviewSize();
                    int size = previewSize.width * previewSize.height * ImageFormat.getBitsPerPixel
                            (mCamera.getParameters().getPreviewFormat()) / 8;
                    mCamera.addCallbackBuffer(new byte[size]);
                    mCamera.setPreviewCallbackWithBuffer((data, camera) -> {
                        if (data != null && data.length > 0) {
                            EncodeUtils.encodeH264Data(data);
                        }
                        mCamera.addCallbackBuffer(data);
                    });
                    mRunning = true;
                }
            }
        }

        /**
         * 停止预览
         */
        private void stopPreview() {
            synchronized (CAMERA_LOCK) {
                EncodeUtils.stopEncode();
                if (mRunning && mCamera != null) {
                    Log.d(TAG, "stopPreview");
                    mCamera.setErrorCallback(null);
                    mCamera.stopPreview();
                    mRunning = false;
                }
            }
        }

        /**
         * 错误监听器
         */
        public interface ErrorCallBack {

            /**
             * 错误回调
             *
             * @param cameraId 相机id
             */
            void onError(int cameraId);

        }
    }

}
