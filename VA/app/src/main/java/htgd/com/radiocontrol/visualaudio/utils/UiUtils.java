package htgd.com.radiocontrol.visualaudio.utils;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import htgd.com.radiocontrol.visualaudio.base.MyApplication;


/**
 * UI相关工具类
 *
 * @author Administrator
 * @date 2017/11/21
 */

public class UiUtils {

    private static final String TAG = UiUtils.class.getSimpleName();

    private UiUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 在主线程运行runnable
     *
     * @param runnable 需要运行的runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            runnable.run();
        } else {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(runnable);
        }
    }

    /**
     * 填充视图
     *
     * @param layoutId     布局id
     * @param root         父容器
     * @param attachToRoot 是否绑定到父容器
     * @return 填充的视图
     */
    public static View inflateView(int layoutId, ViewGroup root, boolean attachToRoot) {
        return LayoutInflater.from(MyApplication.getContext()).inflate(layoutId, root,
                attachToRoot);
    }

    /**
     * 获取color资源值的对应值
     *
     * @param resId 资源值
     * @return 对应值
     */
    public static int getColor(int resId) {
        return MyApplication.getContext().getResources().getColor(resId);
    }

}
