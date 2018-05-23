package htgd.com.radiocontrol.visualaudio.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import htgd.com.radiocontrol.visualaudio.pojo.AppEvent;


/**
 * 广播工具类（用于发送本地广播，发送事件为应用内事件）
 *
 * @author Administrator
 * @date 2018/4/9
 */

public class BroadcastUtil {

    /**
     * 本地广播action
     */
    public static final String LOCAL_ACTION = "com.peakfire.aiodemo.LOCAL_BROADCAST";

    /**
     * 私有化构造函数
     */
    private BroadcastUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 发送本地广播
     *
     * @param context  上下文
     * @param appEvent 应用内事件实例
     */
 public static void sendLocalBroadcast(Context context, AppEvent appEvent) {
        Intent intent = new Intent(LOCAL_ACTION);
        intent.putExtra("data", appEvent);
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(context);
        manager.sendBroadcast(intent);
    }

}
