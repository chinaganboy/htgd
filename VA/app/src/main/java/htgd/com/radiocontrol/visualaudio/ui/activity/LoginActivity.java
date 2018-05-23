package htgd.com.radiocontrol.visualaudio.ui.activity;

import android.app.ActionBar;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.broadcast.android.android_sta_jni_avsz.ndk_wrapper;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;



import htgd.com.radiocontrol.visualaudio.base.BaseActivity;
import htgd.com.radiocontrol.visualaudio.base.Constants;
import htgd.com.radiocontrol.visualaudio.model.EventBusEvent;
import htgd.com.radiocontrol.visualaudio.pojo.AppEvent;
import htgd.com.radiocontrol.visualaudio.pojo.login.LoginRoot;
import htgd.com.radiocontrol.visualaudio.thread.AioThreadPool;
import htgd.com.radiocontrol.visualaudio.utils.BroadcastUtil;
import htgd.com.radiocontrol.visualaudio.utils.IpUtils;
import htgd.com.radiocontrol.visualaudio.utils.LogUtils;
import htgd.com.radiocontrol.visualaudio.utils.PreferencesUtil;
import htgd.com.radiocontrol.visualaudio.utils.UiUtils;
import htgd.com.radiocontrol.visualaudio.utils.XmlUtils;
import radiocontrol.htgd.com.visualaudio.BuildConfig;
import radiocontrol.htgd.com.visualaudio.R;


/**
 * Created by wzw on 2018/4/9.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private String TAG = "LoginActivity";
    private EditText etLoginUserName, etLoginPwd, ipaddress;
    private Button btnLogin, btnExit, btnLoginOut;
    private int mSessId;
    private String mIsSuccess;
    private Context mContext;
    private TextView tvLocalIp;


    // private int mSessId;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_new;
    }

    @Override
    protected void initSubViews() {
        initView();
        mContext = this;


        if (PreferencesUtil.getInstance().getField(Constants.ISLOGIN, mContext) != null) {
            mIsSuccess = PreferencesUtil.getInstance().getField(Constants.ISLOGIN, mContext);
        }
        int result = ndk_wrapper.getInstance().avsz_preload();
        // 授权校验失败
        if (result != 0) {
            // 尝试从备份恢复
            result = ndk_wrapper.getInstance().avsz_recover_auth_from_bak();
            LogUtils.setLog(TAG, "[avsz_recover_auth_from_bak] result: " + result);
            // 恢复失败，授权失败
            if (result != 0) {
                showMsg("授权校验失败！");
            }
        }
    }



    private void initView() {
        etLoginUserName = (EditText) findViewById(R.id.login_uername);

        etLoginPwd = (EditText) findViewById(R.id.login_password);

        ipaddress = (EditText) findViewById(R.id.login_ip);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvLocalIp=(TextView)findViewById(R.id.local_ip);
        tvLocalIp.setOnClickListener(this);
        tvLocalIp.setText(IpUtils.getIpAddress( ));
        btnExit = (Button) findViewById(R.id.btn_exit);
        btnExit.setVisibility(View.GONE);
        btnLoginOut = (Button) findViewById(R.id.btn_logout);
        btnLoginOut.setVisibility(View.GONE);
        btnLogin.setOnClickListener(this);
       ViewGroup.LayoutParams lp= btnLogin.getLayoutParams();
       lp.width=290;
        btnLogin.setLayoutParams( lp);
    }

    /**
     * 登录操作
     */
    private void login() {
        String name = etLoginUserName.getText().toString().trim();
        String pwd = etLoginPwd.getText().toString().trim();
        String svrAdd = ipaddress.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            showMsg("用户名为空！");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            showMsg("密码为空！");
            return;
        }
        if (TextUtils.isEmpty(svrAdd)) {
            showMsg("服务器IP地址为空！");
            return;
        }
        int result = ndk_wrapper.getInstance().avsz_init(svrAdd, (short) 1220, name, pwd,
                BuildConfig.VERSION_NAME);
        LogUtils.setLog(TAG, "[avsz_init] result: " + result);
        if (result < 0) {
            showMsg("授权校验失败！");
            return;
        } else {

            startActivity(new Intent(this, NewMainActivity.class));
        }

        // 记录会话sessId
        mSessId = result;
        PreferencesUtil.getInstance().keepField(Constants.msessid, mSessId + "", mContext);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.local_ip:
                startActivity(new Intent(Settings.ACTION_WIFI_IP_SETTINGS ));
                break;
        }
    }

}
