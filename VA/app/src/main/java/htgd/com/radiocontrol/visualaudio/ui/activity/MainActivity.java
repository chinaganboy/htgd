package htgd.com.radiocontrol.visualaudio.ui.activity;



import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import htgd.com.radiocontrol.visualaudio.base.BaseActivity;
import htgd.com.radiocontrol.visualaudio.ui.fragment.FragmentDianBo;
import htgd.com.radiocontrol.visualaudio.ui.fragment.FragmentDuiJiang;
import htgd.com.radiocontrol.visualaudio.ui.fragment.FragmentHistory;
import htgd.com.radiocontrol.visualaudio.ui.fragment.FragmentXunHu;
import radiocontrol.htgd.com.visualaudio.R;


/**
 * Created by wzw on 2018/4/9.
 */

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private FragmentTransaction transaction;
    private FragmentXunHu fgXunhu ;
    private FragmentDuiJiang fgDuijiang  ;
    private FragmentDianBo   fgDianbo  ;
    private FragmentHistory  fdHistory ;
    private Context mContext;
    private RadioButton rdMenuXunhu, rdMenuDuijiang, rdMenuDianbo, rdMenuHistory;
    private String Tag = "MainActivity";
    private RadioGroup rpTab;
    private ImageView btSetting;
    private TextView state;
    private TextView next;
    private TextView back;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initSubViews() {
        initView();
    }

    private void initView() {
        rpTab = (RadioGroup) findViewById(R.id.rd_group);
        rpTab.setOnCheckedChangeListener(this);
        rdMenuXunhu = (RadioButton) findViewById(R.id.rd_menu_xunhu);
        rdMenuXunhu.setOnClickListener(this);
        rdMenuDianbo = (RadioButton) findViewById(R.id.rd_menu_dianbo);
        rdMenuDianbo.setOnClickListener(this);
        rdMenuDuijiang = (RadioButton) findViewById(R.id.rd_menu_duijiang);
        rdMenuDuijiang.setOnClickListener(this);
        rdMenuHistory = (RadioButton) findViewById(R.id.rd_menu_history);
        rdMenuHistory.setOnClickListener(this);
        btSetting = (ImageView) findViewById(R.id.set);
        btSetting.setOnClickListener(this);
        state = (TextView) findViewById(R.id.state);
        next = (TextView) findViewById(R.id.title_next);
        next.setVisibility(View.INVISIBLE);
        back = (TextView) findViewById(R.id.title_back);
        back.setOnClickListener(this);
        reSetRadioImage();
        rdMenuXunhu.setChecked(true);
    }

    public void hideAllFragment(FragmentTransaction transaction) {
        if (fgXunhu != null) {
            transaction.hide(fgXunhu);
        }
        if (fgDuijiang != null) {
            transaction.hide(fgDuijiang);
        }
        if (fgDianbo != null) {
            transaction.hide(fgDianbo);
        }
        if (fdHistory != null) {
            transaction.hide(fdHistory);
        }

    }

    private void reSetRadioImage() {
        rdMenuXunhu.setBackgroundResource(R.mipmap.call_nor);
        rdMenuDuijiang.setBackgroundResource(R.mipmap.visual_speak_nor);
        rdMenuDianbo.setBackgroundResource(R.mipmap.play_music_nor);
        rdMenuHistory.setBackgroundResource(R.mipmap.history_nor);
    }

    private void changeRadioStatu(int i) {
        reSetRadioImage();
        switch (i) {
            case 1:
                rdMenuXunhu.setBackgroundResource(R.mipmap.call_sel);
                break;
            case 2:
                rdMenuDuijiang.setBackgroundResource(R.mipmap.visual_speak_sel);
                break;
            case 3:
                rdMenuDianbo.setBackgroundResource(R.mipmap.play_music_sel);
                break;
            case 4:
                rdMenuHistory.setBackgroundResource(R.mipmap.history_sel);
                break;

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.set:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.title_back:
                startActivity(new Intent(this, ScreanActivity.class));
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        transaction = getFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (i) {
            case R.id.rd_menu_xunhu:
                changeRadioStatu(1);
                if (fgXunhu == null) {

                    transaction.add(R.id.fragment_container, FragmentXunHu.newInstance(mContext));
                } else {
                    transaction.show(fgXunhu);
                }

                break;
            case R.id.rd_menu_duijiang:
                changeRadioStatu(2);
                if (fgDuijiang == null) {

                    transaction.add(R.id.fragment_container, FragmentDuiJiang.newInstance(mContext));
                } else {
                    transaction.show(fgDuijiang);
                }

                break;
            case R.id.rd_menu_dianbo:
                changeRadioStatu(3);
                if (fgDianbo == null) {
                   // transaction.add(R.id.fragment_container, FragmentDianBo.getInstance(mContext));
                } else {
                    transaction.show(fgDianbo);
                }

                break;

            case R.id.rd_menu_history:
                changeRadioStatu(4);
                if (fdHistory == null) {
                    transaction.add(R.id.fragment_container, FragmentHistory.newInstance(mContext));
                } else {
                    transaction.show(fdHistory);
                }

                break;

        }
        transaction.commit();
    }
}
