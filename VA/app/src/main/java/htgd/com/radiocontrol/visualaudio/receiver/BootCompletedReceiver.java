package htgd.com.radiocontrol.visualaudio.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import htgd.com.radiocontrol.visualaudio.ui.activity.MainActivity;
import htgd.com.radiocontrol.visualaudio.utils.ToastUtil;

/**
 * Created by wzw on 2018/4/9.
 */

public class BootCompletedReceiver extends BroadcastReceiver{
    static final String ACTION = "android.intent.action.BOOT_COMPLETED";
    @Override
    public void onReceive(Context context, Intent intent) {
        ToastUtil.showToast(context,"kaijil");
        Log.e("监听广播","ss");
       /* if (intent.getAction().equals(ACTION)) {
            Intent mainActivityIntent = new Intent(context, MainActivity.class);  // 要启动的Activity
            mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mainActivityIntent);
        }*/

    }
}
