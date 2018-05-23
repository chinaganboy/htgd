package htgd.com.radiocontrol.visualaudio.thread;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理类
 *
 * @author Administrator
 * @date 2017/11/22
 */

public class AioThreadPool {

    private static final String TAG = AioThreadPool.class.getSimpleName();

    /**
     * 当前设备的可用CPU核心数
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    /**
     * 线程池核心线程数量
     */
    private static final int CORE_POOL_SIZE = CPU_COUNT + 4;
    /**
     * 线程池最大线程数量
     */
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 4 + 4;
    /**
     * 线程池线程空闲时间
     */
    private static final long KEEP_ALIVE_TIME = 60;

    private static ThreadPoolExecutor sExecutor;

    private AioThreadPool() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取线程池单实例
     *
     * @return 线程池实例
     */
    public static ThreadPoolExecutor getExecutor() {
        if (sExecutor == null) {
            sExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
                    KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, new ArrayBlockingQueue
                    (CORE_POOL_SIZE), new ThreadPoolExecutor.DiscardOldestPolicy());
        }

        Log.d(TAG, "Get thread pool instance, active thread count: " + sExecutor.getActiveCount());

        return sExecutor;
    }

}
