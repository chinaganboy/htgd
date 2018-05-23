package htgd.com.radiocontrol.visualaudio.base;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.broadcast.android.android_sta_jni_avsz.ndk_wrapper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import htgd.com.radiocontrol.visualaudio.model.EventBusConnectString;
import htgd.com.radiocontrol.visualaudio.model.EventBusEvent;
import htgd.com.radiocontrol.visualaudio.pojo.AppEvent;
import htgd.com.radiocontrol.visualaudio.utils.BroadcastUtil;
import htgd.com.radiocontrol.visualaudio.utils.LogUtils;
import htgd.com.radiocontrol.visualaudio.utils.NetUtils;
import htgd.com.radiocontrol.visualaudio.utils.ZTLUtils;
import radiocontrol.htgd.com.visualaudio.R;


/**
 * Created by Administrator on 2016/7/22.
 */
public abstract class BaseActivity extends Activity {
    public Activity mActivity;
    private String Tag = "BaseActivity";
    private CountTimer countTimerView;
    private TextView connectToast;
    private Toast mToast;

    private int mSessId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initSubViews();
        int result = ndk_wrapper.getInstance().avsz_preload();
        mSessId = result;
        mActivity = this;
        new ZTLUtils(mActivity).setTranslucentStatus();
        EventBus.getDefault().register(this);
        hideBottomUIMenu();
        hideNavigationBar();
        showOrHideNavigationBar(false);
        getLayoutId();
        init();
        showNetworkDisableToast(mActivity);
    }

    abstract protected int getLayoutId();

    abstract protected void initSubViews();

    private void showOrHideNavigationBar(boolean show) {
        // 显示或隐藏导航栏
        Intent mIntent = new Intent(show ? "show.systemui" : "hide.systemui");
        sendBroadcast(mIntent);
    }
    //开始计时
    private void timeStart() {
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                countTimerView.start();
            }
        });
    }

    private void init() {
        //初始化CountTimer，设置倒计时为2分钟。
        countTimerView = new CountTimer(60000, 1000, BaseActivity.this);
    }

    /**
     * 主要的方法，重写dispatchTouchEvent
     *  判断到上一次触摸完屏幕的时间
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            //获取触摸动作，如果ACTION_UP，计时开始。
            case MotionEvent.ACTION_UP:
                countTimerView.start();
                break;
            //否则其他动作计时取消
            default:
                countTimerView.cancel();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onPause() {
        super.onPause();
        countTimerView.cancel();
    }

    @Override
    protected void onResume() {

        super.onResume();
        timeStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showOrHideNavigationBar(true);
        EventBus.getDefault().unregister(this);

    }

    public void showNetworkDisableToast(Activity activity) {
        if (!NetUtils.checkNetworkEnable(this)) {
            connectToast = (TextView) activity.findViewById(R.id.state);
            connectToast.setText("未检测到网络，请检查网络配置");
        }
    }


    /**
     * 提示消息
     *
     * @param msg 消息值
     */
    public void showMsg(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            if (mToast == null) {
                mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(msg);
            }
            mToast.show();
        }
    }
     //隐藏导航栏
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusConnectString event) {
        connectToast = (TextView) findViewById(R.id.state);
        LogUtils.setLog(Tag, "the event is on here" + event.getMessage());

        if (event.getMessage() != "") {
            connectToast.setVisibility(View.VISIBLE);
            connectToast.setText(event.getMessage());

        } else {
            connectToast.setVisibility(View.GONE);
        }
    }

  /*  @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusEvent event) {
        AppEvent appEvent = event.getMessage();
        String value = appEvent.getValue();
        LogUtils.setLog(Tag, "this is event lianjie");
        switch (appEvent.getType()) {

          *//*  // 连接状态反馈
            case "tcp":
                if ("close".equalsIgnoreCase(appEvent.getKey())) {
                    showMsg("已断开连接！");
                } else if ("timeout".equalsIgnoreCase(appEvent.getKey())) {
                    showMsg("已断开连接！");
                }
                break;*//*
        }
    }*/
}
