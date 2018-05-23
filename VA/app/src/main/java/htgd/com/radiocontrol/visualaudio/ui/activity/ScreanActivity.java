package htgd.com.radiocontrol.visualaudio.ui.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import htgd.com.radiocontrol.visualaudio.utils.IpUtils;
import htgd.com.radiocontrol.visualaudio.utils.LogUtils;
import htgd.com.radiocontrol.visualaudio.utils.TimeUtils;
import radiocontrol.htgd.com.visualaudio.R;

public class ScreanActivity  extends Activity {

    private static String Tag = "ScreanActivity";
    private TextView ipText;
    private TextView cTime;
    private Timer timer;
    private TextView dayText;
    private TextView weekText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screan);
         hideBottomUIMenu();
        hideNavigationBar();
        initView();
        ipText.setText("本机IP：" + IpUtils.getIpAddress());
        weekText.setText(TimeUtils.getWeek());
        dayText.setText(TimeUtils.getDate());
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        cTime.setText(TimeUtils.getDateTime());
                    }
                });

            }
        }, 0, 1000);
    }

    private void initView() {
        ipText = (TextView) findViewById(R.id.ip);
        cTime = (TextView) findViewById(R.id.c_time);
        dayText=(TextView)findViewById(R.id.day);
        weekText=(TextView)findViewById(R.id.week);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            //当按下
            case MotionEvent.ACTION_DOWN:
                LogUtils.setLog("dian");
                finish();
                break;
            //当按下并且移动
            case MotionEvent.ACTION_MOVE:
                break;
            //当抬起
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void hideNavigationBar() {
        int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN; // hide status bar

        if (android.os.Build.VERSION.SDK_INT >= 19) {
            uiFlags |= View.SYSTEM_UI_FLAG_IMMERSIVE;//0x00001000; // SYSTEM_UI_FLAG_IMMERSIVE_STICKY: hide
        } else {
            uiFlags |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
        }

        try {
            getWindow().getDecorView().setSystemUiVisibility(uiFlags);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}
