package htgd.com.radiocontrol.visualaudio.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.broadcast.android.android_sta_jni_avsz.ndk_wrapper;
import com.zhy.adapter.recyclerview.CommonAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import htgd.com.radiocontrol.visualaudio.base.BaseActivity;
import htgd.com.radiocontrol.visualaudio.model.EventBusEvent;
import htgd.com.radiocontrol.visualaudio.pojo.AppEvent;
import htgd.com.radiocontrol.visualaudio.pojo.bcusr.BcUsrTskRoot;
import htgd.com.radiocontrol.visualaudio.pojo.call.CallRoot;
import htgd.com.radiocontrol.visualaudio.pojo.call.CancelRoot;
import htgd.com.radiocontrol.visualaudio.pojo.login.LoginRoot;
import htgd.com.radiocontrol.visualaudio.pojo.manual.ExecManualTskRet;
import htgd.com.radiocontrol.visualaudio.pojo.prog.Prog;
import htgd.com.radiocontrol.visualaudio.pojo.status.StatusRoot;
import htgd.com.radiocontrol.visualaudio.pojo.task.Dsts;
import htgd.com.radiocontrol.visualaudio.pojo.task.Row;
import htgd.com.radiocontrol.visualaudio.pojo.task.SrcInfo;
import htgd.com.radiocontrol.visualaudio.pojo.task.Srcs;
import htgd.com.radiocontrol.visualaudio.pojo.task.TaskRoot;
import htgd.com.radiocontrol.visualaudio.pojo.task.Tsk;
import htgd.com.radiocontrol.visualaudio.pojo.task.UsrGuid;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskChNum;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskDuration;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskHasDura;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskHasStopTime;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskJobType;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskMdTp;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskPlayVolumn;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskSrcType;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskStartTime;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskStopTime;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskTransProto;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskWeekData;
import htgd.com.radiocontrol.visualaudio.pojo.tsk.TskRoot;
import htgd.com.radiocontrol.visualaudio.pojo.usr.Usr;
import htgd.com.radiocontrol.visualaudio.thread.AioThreadPool;
import htgd.com.radiocontrol.visualaudio.ui.adapter.UserAdapter;
import htgd.com.radiocontrol.visualaudio.utils.DateUtils;
import htgd.com.radiocontrol.visualaudio.utils.LogUtils;
import htgd.com.radiocontrol.visualaudio.utils.UiUtils;
import htgd.com.radiocontrol.visualaudio.utils.Utils;
import htgd.com.radiocontrol.visualaudio.utils.VideoUtils;
import htgd.com.radiocontrol.visualaudio.utils.XmlUtils;
import htgd.com.radiocontrol.visualaudio.widget.CallDialog;
import htgd.com.radiocontrol.visualaudio.widget.DialogConnectOrNot;
import radiocontrol.htgd.com.visualaudio.BuildConfig;
import radiocontrol.htgd.com.visualaudio.R;


public class NewMainActivity extends BaseActivity implements View.OnClickListener {
    private int mSessId;
    private String mUsrGuid;
    private String Tag = "NewMainActivity";
    private ArrayList<Usr> userList = new ArrayList<>();
    ;
    private CommonAdapter<Usr> commonAdapter;
    private Button call, speak, playMusic;
    private GridView gvUser;
    private UserAdapter userAdapter;
    private int sNumber;
    private TextView msg;
    private TskRoot mTskRoot;
    private DialogConnectOrNot dia;
    private ArrayList<Usr> chooseList = new ArrayList<>();
    private CallDialog callDialog;
    private ImageView btSetting;
    private TextView state;
    private TextView next;
    private TextView back;
    private ArrayList<Prog> musicList;
    private BcUsrTskRoot mBcUsrTskRoot;
    private int result;
    private ExecManualTskRet mExecManualTskRet;
    private CallRoot mCallRoot;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_main;
    }

    @Override
    protected void initSubViews() {
        initView();
        initData();
        // 预加载
        int result = ndk_wrapper.getInstance().avsz_preload();

        if (result != 0) {
            // 尝试从备份恢复
            result = ndk_wrapper.getInstance().avsz_recover_auth_from_bak();
        }
        result = ndk_wrapper.getInstance().avsz_init("192.168.7.9", (short) 1220, "3", "111111",
                BuildConfig.VERSION_NAME);
        mSessId=result;
        LogUtils.setLog(Tag, "result" + result);
        userAdapter = new UserAdapter(this, userList);

        gvUser.setAdapter(userAdapter);
        //选择用户
        gvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (userList.get(position).getStatus().equals("1")) {//针对在线用户操作
                    if (userList.get(position).isChoose()) {
                        userList.get(position).setChoose(false);
                        sNumber = sNumber - 1;
                    } else {
                        sNumber = sNumber + 1;
                        userList.get(position).setChoose(true);
                    }

                    userAdapter.notifyDataSetChanged();
                }
                if (sNumber > 1) {
                    playMusic.setClickable(true);
                    playMusic.setBackgroundResource(R.mipmap.play_music_sel);
                    call.setClickable(true);
                    call.setBackgroundResource(R.mipmap.call_sel);
                    speak.setClickable(false);
                    speak.setBackgroundResource(R.mipmap.visual_speak_nor);
                } else if (sNumber == 1) {
                    playMusic.setClickable(true);
                    playMusic.setBackgroundResource(R.mipmap.play_music_sel);
                    call.setClickable(true);
                    call.setBackgroundResource(R.mipmap.call_sel);
                    speak.setClickable(true);
                    speak.setBackgroundResource(R.mipmap.visual_speak_sel);
                } else if (sNumber == 0) {
                    playMusic.setClickable(false);
                    playMusic.setBackgroundResource(R.mipmap.play_music_nor);
                    call.setClickable(false);
                    call.setBackgroundResource(R.mipmap.call_nor);
                    speak.setClickable(false);
                    speak.setBackgroundResource(R.mipmap.visual_speak_nor);
                }
            }
        });


    }

    private void initView() {
        msg = (TextView) findViewById(R.id.msg_back);
        gvUser = (GridView) findViewById(R.id.userlist);
        call = (Button) findViewById(R.id.call);
        speak = (Button) findViewById(R.id.speak);
        playMusic = (Button) findViewById(R.id.play_music);
        call.setOnClickListener(this);
        speak.setOnClickListener(this);
        playMusic.setOnClickListener(this);


       /* btSetting = (ImageView) findViewById(R.id.set);
        btSetting.setOnClickListener(this);
        state = (TextView) findViewById(R.id.state);
        next = (TextView) findViewById(R.id.title_next);
        next.setVisibility(View.INVISIBLE);
        back = (TextView) findViewById(R.id.title_back);
        back.setOnClickListener(this);*/
    }

    public void initData() {
        //  mSessId = Integer.parseInt(PreferencesUtil.getInstance().getField(Constants.msessid, this));

    }

    @Override
    public void onClick(View view) {
        if (userList.size() > 0) {
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).isChoose()) {
                    chooseList.add(userList.get(i));
                }
            }
            if (chooseList.size() > 0) {
                switch (view.getId()) {
           /* case R.id.set:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.title_back:
                startActivity(new Intent(this, ScreanActivity.class));
                break;*/
                    case R.id.speak://对讲
                        showConnectDialog();
              /* if (mIsSuccess) {
                     // 示例不处理多任务问题！
                     if (mCallRoot != null || mMonitorRoot != null || mBcUsrTskRoot != null ||
                             mExecManualTskRet != null) {
                         showMsg("正在执行其他任务！");
                         return;
                     }*/
                        if (userAdapter != null && mTskRoot == null) {


                            if (chooseList.size() == 1) {
                                Usr usr = chooseList.get(0);
                                if (usr != null) {
                                    if (!"0".equalsIgnoreCase(usr.getStatus())) {
                                        // 需要考虑是用户还是终端，用户媒体类型为3，终端要判断是否有视频，无则只请求语音
                                        int mdTp = "1".equalsIgnoreCase(usr.getTp()) ? 3 : ("0"
                                                .equalsIgnoreCase(usr.getHas_vid()) ? 2 : 3);
                                        int result = ndk_wrapper.getInstance().avsz_usr_call_req
                                                (mSessId, usr.getId(), 0, 0, mdTp, 1);
                                        Log.d(Tag, "[avsz_usr_call_req] result: " + result);
                                        showMsg("正在呼叫[" + usr.getName() + "]");
                                    } else {
                                        showMsg("[" + usr.getName() + "]不在线！");
                                    }
                                }
                            } else {
                                showMsg("不能同时对多个终端或用户对讲！");
                            }
                        }
                  /*  } else {
                     showMsg("你已离线！");
                 }*/
                        Intent intents = new Intent(this, CallingActivity.class);
                        startActivity(intents);
                        break;
                    case R.id.call://寻呼
                        if (mTskRoot != null ||   mCallRoot != null ||
                                mExecManualTskRet != null) {
                            showMsg("正在执行其他任务！");
                            return;
                        }
                        execManualTsk(chooseList);//启动寻呼
                       // showSpeakingDialog();
                       /* Intent intent = new Intent(this, SpeakingActivity.class);
                        startActivity(intent);*/
                        break;
                    case R.id.play_music:
                        // gvUser.setAdapter(musicAdapter);

                        Intent in = new Intent(this, MusicListActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("userlist", userList);
                        bundle.putSerializable("musiclist", musicList);
                        in.putExtras(bundle);
                        startActivity(in);
                        break;
                }
            }
        }
    }
      //正在寻呼对话框
    private void showSpeakingDialog() {
        callDialog = new CallDialog(this, new CallDialog.OnViewClickListener() {
            @Override
            public void cancel() {
                //停止寻呼任务
                for (int i = 0; i < chooseList.size(); i++) {
                    result = ndk_wrapper.getInstance().avsz_stop_task(mSessId, mTskRoot.getTsk_guid()/*mBcUsrTskRoot
                        .getTsk_guid()*/);
                }
                callDialog.dismiss();
            }
        });
        callDialog.show();
    }

    /**
     * 执行手动任务（喊话）
     * @param usrList 目标终端或主机数据集
     */
    private void execManualTsk(List<Usr> usrList) {
        TaskRoot taskRoot = new TaskRoot();
        taskRoot.setOrd("exec_manual_tsk");
        Tsk tsk = new Tsk();
        Row row = new Row();
        // 任务音量可自行设置，100以内！
        row.setTsk_play_volumn(new TskPlayVolumn("70"));
        row.setTsk_src_type(new TskSrcType("4"));
        // 传输协议，1[TCP] 2[UDP] 3[UDP组播]
        row.setTsk_trans_proto(new TskTransProto("1"));
        row.setTsk_has_dura(new TskHasDura("0"));
        row.setTsk_job_type(new TskJobType("-1"));
        SimpleDateFormat format = new SimpleDateFormat(DateUtils.DATE_TIME_FORMAT);
        Date date = new Date();
        String dateAndTime = format.format(date);
        // 设置开始日期时间为当前日期时间
        row.setTsk_start_time(new TskStartTime(dateAndTime));
        row.setTsk_has_stop_time(new TskHasStopTime("0"));
        row.setTsk_stop_time(new TskStopTime(dateAndTime.split(" ")[0] + " 23:59:59"));
        row.setTsk_duration(new TskDuration("0"));
        row.setTsk_week_data(new TskWeekData("0000000"));
        // 媒体类型为音频
        row.setTsk_md_tp(new TskMdTp("2"));
        row.setTsk_ch_num(new TskChNum("0"));
        tsk.setRow(row);
        Srcs srcs = new Srcs();
        List<SrcInfo> srcInfos = new ArrayList<>();
        // 源为当前用户的GUID
        SrcInfo srcInfo = new SrcInfo(mUsrGuid);
        srcInfos.add(srcInfo);
        srcs.setSrc_info(srcInfos);
        tsk.setSrcs(srcs);
        Dsts dsts = new Dsts();
        List<UsrGuid> usrGuids = new ArrayList<>();
        for (Usr usr : usrList) {
            usrGuids.add(new UsrGuid(usr.getId()));
        }
        dsts.setUsr_guid(usrGuids);
        tsk.setDsts(dsts);
        taskRoot.setTsk(tsk);
        String xml = XmlUtils.toXml(taskRoot);
        if (!TextUtils.isEmpty(xml)) {
            Log.d(Tag, "speak tsk[xml]: " + xml);
            int result = ndk_wrapper.getInstance().avsz_send_svr_xml(mSessId, xml);
            Log.d(Tag, "[avsz_send_svr_xml] result: " + result);
        }else{
            LogUtils.setLog(Tag,"未发出寻呼");
        }
    }

    private void showConnectDialog() {
        callDialog = new CallDialog(this, new CallDialog.OnViewClickListener() {
            @Override
            public void cancel() {
            }
        });
        callDialog.show();
    }

    //设置属性对话框
    private void connectDialog() {
        dia = new DialogConnectOrNot(this, new DialogConnectOrNot.OnViewClickListener() {
            @Override
            public void confirm() {
            }

            @Override
            public void cancel() {

            }

            @Override
            public void ignore() {
                dia.dismiss();
            }
        });
        dia.show();
    }

    private void dissmissDialog() {
        if (dia != null) {
            dia.dismiss();
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusEvent event) {
        AppEvent appEvent = event.getMessage();
        String value = appEvent.getValue();

        LogUtils.setLog(Tag, "this is event ");
        msg.append(Utils.getDateTime() + " -- " + appEvent.toString() + "\n\n");
        switch (appEvent.getType()) {

            case "usr_login_ret":
                LogUtils.setLog(Tag, "this is back login");
                LoginRoot loginRoot = XmlUtils.toBean(LoginRoot.class, value
                        .getBytes());

                if (loginRoot.getUsrs() != null && loginRoot.getUsrs()
                        .getUsr() != null) {
                    userList = (ArrayList) loginRoot.getUsrs().getUsr();
                    LogUtils.setLog(Tag, "this is data  up" + userList.size());
                } else {
                    LogUtils.setLog(Tag, "meiyou" + userList.size());
                }
                userAdapter.refrech(userList);
                LogUtils.setLog(Tag, "yinyuesss ");
                // 节目库数据
                if (loginRoot.getProgs() != null && loginRoot.getProgs()
                        .getProg() != null) {
                    musicList = new ArrayList<Prog>();
                    LogUtils.setLog(Tag, "yinyue ");
                    for (Prog prog : loginRoot.getProgs().getProg()) {
                        // 去除目录路径（时间为00:00:00）和过滤非MP3文件
                        String name = prog.getName();
                        if (!TextUtils.isEmpty(name) && (name.contains("" +
                                ".mp3") || name.contains(".MP3"))) {
                            musicList.add(prog);
                        }
                    }
                    LogUtils.setLog(Tag, "音乐数量" + musicList.size());
                }
                break;
            case "tcp":
                if ("close".equalsIgnoreCase(appEvent.getKey())) {
                    showMsg("已断开连接！");
                } else if ("timeout".equalsIgnoreCase(appEvent.getKey())) {
                    showMsg("已断开连接！");
                }
                break;
            // 终端或用户状态变化反馈
            case "usr_status":
                StatusRoot statusRoot = XmlUtils.toBean(StatusRoot.class, value.getBytes());
                if (statusRoot != null) {
                    AioThreadPool.getExecutor().execute(() -> {
                        if (userList != null) {
                            LogUtils.setLog(Tag, userList.size() + "yonghu shuliang");
                            for (Usr usr : userList) {
                                if (usr.getId().equalsIgnoreCase(statusRoot.getUsr_guid()
                                ) && usr.getName().equalsIgnoreCase(statusRoot.getName())) {
                                    usr.setStatus(statusRoot.getStatus());
                                    break;
                                }
                            }
                        }
                        for (Usr usr : userList) {
                            if (usr.getId().equalsIgnoreCase(statusRoot
                                    .getUsr_guid()) && usr.getName()
                                    .equalsIgnoreCase(statusRoot.getName())) {
                                usr.setStatus(statusRoot.getStatus());
                                break;
                            }
                        }
                        UiUtils.runOnUiThread(() -> {
                            if (userAdapter != null) {
                                userAdapter.refrech(userList);
                            }
                        });
                    });

                    // 被呼叫方离线
                    if (mTskRoot != null && "0".equalsIgnoreCase(statusRoot.getStatus())) {
                        if (statusRoot.getUsr_guid().equalsIgnoreCase(mTskRoot
                                .getCallee_guid())) {
                            mTskRoot = null;

                            if ("2".equalsIgnoreCase(mTskRoot.getTp())) {
                                VideoUtils.stopH2645Thread();
                                //mFlVdContain.removeAllViews();
                            }
                        }
                    }
                }
                break;
            //寻呼反馈
            case "exec_manual_tsk_ret":
                LogUtils.setLog("xunhul ");
                    mExecManualTskRet = XmlUtils.toBean(ExecManualTskRet
                            .class, value.getBytes());
                    if (mExecManualTskRet != null) {
                    if ("ok".equalsIgnoreCase(mExecManualTskRet.getRet())) {
                        showMsg("执行成功！");
                        showSpeakingDialog();
                    } else {
                        showMsg("执行失败！");
                    }
                }
                break;
            // 终端或用户取消呼叫请求
            case "usr_call_req_cancel":
                Log.e("fankuil", "取消呼叫");
                CancelRoot cancelRoot = XmlUtils.toBean(CancelRoot.class, value.getBytes());
                   /* if (cancelRoot != null && mCallRoot != null) {
                        if (cancelRoot.getTsk_guid().equalsIgnoreCase(mCallRoot.getTsk_guid()
                        )) {
                            mCallRoot = null;
                            mLlMenuContain.setVisibility(View.INVISIBLE);
                        }
                    }*/
                break;
            // 终端或用户呼叫请求反馈
            case "usr_call_req":
                LogUtils.setLog("fachuhujiao");
                CallRoot callRoot = XmlUtils.toBean(CallRoot.class, value.getBytes());
                if (callRoot != null && userList != null) {
                    AioThreadPool.getExecutor().execute(() -> {
                        for (Usr usr : userList) {
                            if (usr.getName().equalsIgnoreCase(callRoot.getCaller_name())
                                    && usr.getId().equalsIgnoreCase(callRoot
                                    .getCaller_guid())) {
                                UiUtils.runOnUiThread(() -> {
                                    // mCallRoot = callRoot;
                                    showMsg("[" + usr.getName() + "]正在呼叫你......");
                                    connectDialog();
                                       /* mLlMenuContain.setVisibility(View.VISIBLE);
                                        mBtnSure.setVisibility(View.VISIBLE);
                                        mBtnCancel.setVisibility(View.VISIBLE);
                                        mBtnCancel.setText("拒绝");
                                        mBtnBusy.setVisibility(View.VISIBLE);
                                        showFunctionMenu();*/
                                });
                                break;
                            }
                        }
                    });
                }
                break;

            // 对讲任务状态反馈
            case "tk_tsk_status":
                LogUtils.setLog("duijiang");
                TskRoot tskRoot = XmlUtils.toBean(TskRoot.class, value.getBytes());
                mTskRoot=tskRoot;
               // TskRoot tskRoot = XmlUtils.toBean(TskRoot.class, value.getBytes());
                if (tskRoot != null) {
                    // 状态值参考ndk_wrapper类中的avsz_usr_call_req函数说明
                    switch (tskRoot.getStatus()) {
                        case "0":
                            LogUtils.setLog("duijiang0");
                            mTskRoot = null;
                            dissmissDialog();
                            //  mBtnStop.setVisibility(View.INVISIBLE);
                            // 非音频，需要视频解码线程
                            if (!"2".equalsIgnoreCase(tskRoot.getTp())) {
                                VideoUtils.stopH2645Thread();

                            }
                            break;
                        case "2":
                            LogUtils.setLog("duijiang2");
                            mTskRoot = tskRoot;
                            connectDialog();
                               /* mBtnSure.setVisibility(View.INVISIBLE);
                                mBtnCancel.setVisibility(View.VISIBLE);
                                mBtnCancel.setText("取消");
                                mBtnBusy.setVisibility(View.INVISIBLE);*/
                            break;
                        case "3":
                            LogUtils.setLog("duijiang3");
                            mTskRoot = tskRoot;
                            break;
                        case "4":
                            LogUtils.setLog("duijiang4");

                            break;
                        default:
                            break;
                    }
                }
                break;

            //用户任务状态
            case "bc_usr_tsk_status":
                Log.e("fankuil", "dangqianTaskzhuangtai");
                BcUsrTskRoot bcUsrTskRoot = XmlUtils.toBean(BcUsrTskRoot.class, value
                        .getBytes());
                if (bcUsrTskRoot != null) {

                    switch (bcUsrTskRoot.getStatus()) {
                        case "0":
                            mBcUsrTskRoot = null;

                            break;
                        case "2":
                            mBcUsrTskRoot = bcUsrTskRoot;

                            break;
                        default:
                            break;

                    }
                }
                break;
        }
    }
}
