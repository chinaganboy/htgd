package htgd.com.radiocontrol.visualaudio.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.broadcast.android.android_sta_jni_avsz.ndk_wrapper;


import htgd.com.radiocontrol.visualaudio.base.BaseActivity;
import radiocontrol.htgd.com.visualaudio.R;

public class SettingActivity extends BaseActivity  implements OnClickListener{
    private TextView back;
    private TextView state;
    private TextView next;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initSubViews() {
        back=(TextView)findViewById(R.id.btn_exit);
        back.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.btn_exit:
                finish();
                break;
            case  R.id.btn_logout:
                int result = ndk_wrapper.getInstance().avsz_fini(0);
                break;
            case R.id.btn_login:
                break;

        }
    }
}
