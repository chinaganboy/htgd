package htgd.com.radiocontrol.visualaudio.thread;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.broadcast.android.android_sta_jni_avsz.ndk_wrapper;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import htgd.com.radiocontrol.visualaudio.pojo.AppEvent;
import htgd.com.radiocontrol.visualaudio.pojo.aud.UpdateRoot;
import htgd.com.radiocontrol.visualaudio.pojo.av.StartRoot;
import htgd.com.radiocontrol.visualaudio.pojo.bcfile.BcFileTskStatusRoot;
import htgd.com.radiocontrol.visualaudio.pojo.bcusr.BcUsrTskRoot;
import htgd.com.radiocontrol.visualaudio.pojo.call.CallRoot;
import htgd.com.radiocontrol.visualaudio.pojo.call.CancelRoot;
import htgd.com.radiocontrol.visualaudio.pojo.manual.ExecManualTskRet;
import htgd.com.radiocontrol.visualaudio.pojo.monitor.MonitorRoot;
import htgd.com.radiocontrol.visualaudio.pojo.refresh.RefreshRoot;
import htgd.com.radiocontrol.visualaudio.pojo.task.Row;
import htgd.com.radiocontrol.visualaudio.pojo.task.Tsk;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskGuid;
import htgd.com.radiocontrol.visualaudio.pojo.tsk.TskRoot;
import htgd.com.radiocontrol.visualaudio.pojo.usr.Usr;
import htgd.com.radiocontrol.visualaudio.utils.AudioCapture;
import htgd.com.radiocontrol.visualaudio.utils.AudioUtils;
import htgd.com.radiocontrol.visualaudio.utils.CameraUtils;
import htgd.com.radiocontrol.visualaudio.utils.EncodeUtils;
import htgd.com.radiocontrol.visualaudio.utils.UiUtils;
import htgd.com.radiocontrol.visualaudio.utils.VideoUtils;
import htgd.com.radiocontrol.visualaudio.utils.XmlUtils;


public class LocalReceiver extends BroadcastReceiver {
    private String mUsrGuid;

    @Override
    public void onReceive(Context context, Intent intent) {


      /*  AppEvent appEvent = intent.getParcelableExtra("data");
        mTvInfo.append(mFormat.format(new Date()) + " -- " + appEvent.toString() + "\n\n");
        if (appEvent.getSessId() == mSessId) {
            String value = appEvent.getValue();
            switch (appEvent.getType()) {
                // 登录反馈
                case "usr_login_ret":
                    if (value.contains("ok")) {
                        final LoginRoot loginRoot = XmlUtils.toBean(LoginRoot.class, value
                                .getBytes());
                        if (loginRoot != null) {
                            mUsrGuid = loginRoot.getUsr_guid();
                            AioThreadPool.getExecutor().execute(() -> {
                                // 分区数据
                                if (loginRoot.getAreas() != null && loginRoot.getAreas()
                                        .getArea() != null) {
                                    mAreas = loginRoot.getAreas().getArea();
                                }
                                // 终端和用户数据
                                if (loginRoot.getUsrs() != null && loginRoot.getUsrs()
                                        .getUsr() != null) {
                                    mUsrs = loginRoot.getUsrs().getUsr();
                                }
                                if (mAreas != null && mUsrs != null) {
                                    mListDatas = new ArrayList<>();
                                    for (Area area : mAreas) {
                                        List<Usr> tmp = new ArrayList<>();
                                        for (Usr usr : mUsrs) {
                                            if (area.getId().equalsIgnoreCase(usr.getPid())) {
                                                tmp.add(usr);
                                            }
                                        }
                                        mListDatas.add(tmp);
                                    }
                                    UiUtils.runOnUiThread(() -> {
                                        if (mElvAdapter != null) {
                                            mElvAdapter.update(mAreas, mListDatas);
                                        }
                                    });
                                }
                                // 节目库数据
                                if (loginRoot.getProgs() != null && loginRoot.getProgs()
                                        .getProg() != null) {
                                    mProgs = new ArrayList<>();
                                    for (Prog prog : loginRoot.getProgs().getProg()) {
                                        // 去除目录路径（时间为00:00:00）和过滤非MP3文件
                                        String name = prog.getName();
                                        if (!TextUtils.isEmpty(name) && (name.contains("" +
                                                ".mp3") || name.contains(".MP3"))) {
                                            mProgs.add(prog);
                                        }
                                    }
                                    if (!mProgs.isEmpty() && mLvProgAdapter != null) {
                                        UiUtils.runOnUiThread(() -> mLvProgAdapter.update
                                                (mProgs));
                                    }
                                }
                                // 定时任务数据
                                if (loginRoot.getTasks() != null && loginRoot.getTasks()
                                        .getTsk() != null) {
                                    mTsks = loginRoot.getTasks().getTsk();
                                    if (!mTsks.isEmpty() && mLvTaskAdapter != null) {
                                        UiUtils.runOnUiThread(() -> mLvTaskAdapter.update
                                                (mTsks));
                                    }
                                }
                            });
                        }
                        setLoginStatus(true);
                    } else {
                        if (value.contains("invalid_pwd") || value.contains("invalid_usr")) {
                            showMsg("用户名或密码错误！");
                        } else if (value.contains("invalid_dog")) {
                            showMsg("服务端未检测到加密狗！");
                        } else if (value.contains("exceed_usr")) {
                            showMsg("超出加密狗数量限制！");
                        }
                        setLoginStatus(false);
                    }
                    break;
                // 连接状态反馈
                case "tcp":
                    if ("close".equalsIgnoreCase(appEvent.getKey())) {
                        if (mIsSuccess) {
                            showMsg("已断开连接！");
                        }
                        setLoginStatus(false);
                    } else if ("timeout".equalsIgnoreCase(appEvent.getKey())) {
                        // 超时需要手动结束当前连接
                        logout();
                        if (mIsSuccess) {
                            showMsg("已断开连接！");
                        }
                        setLoginStatus(false);
                    }
                    break;
                // 终端或用户状态变化反馈
                case "usr_status":
                    StatusRoot statusRoot = XmlUtils.toBean(StatusRoot.class, value.getBytes());
                    if (statusRoot != null) {
                        if (mUsrs != null && mListDatas != null) {
                            AioThreadPool.getExecutor().execute(() -> {
                                for (Usr usr : mUsrs) {
                                    if (usr.getId().equalsIgnoreCase(statusRoot.getUsr_guid()
                                    ) && usr.getName().equalsIgnoreCase(statusRoot.getName())) {
                                        usr.setStatus(statusRoot.getStatus());
                                        break;
                                    }
                                }
                                for (List<Usr> listData : mListDatas) {
                                    if (listData != null) {
                                        for (Usr usr : listData) {
                                            if (usr.getId().equalsIgnoreCase(statusRoot
                                                    .getUsr_guid()) && usr.getName()
                                                    .equalsIgnoreCase(statusRoot.getName())) {
                                                usr.setStatus(statusRoot.getStatus());
                                                break;
                                            }
                                        }
                                    }
                                }
                                UiUtils.runOnUiThread(() -> {
                                    if (mElvAdapter != null) {
                                        mElvAdapter.update(mAreas, mListDatas);
                                    }
                                });
                            });
                        }
                        // 被呼叫方离线
                        if (mTskRoot != null && "0".equalsIgnoreCase(statusRoot.getStatus())) {
                            if (statusRoot.getUsr_guid().equalsIgnoreCase(mTskRoot
                                    .getCallee_guid())) {
                                mTskRoot = null;
                                mLlMenuContain.setVisibility(View.INVISIBLE);
                                mBtnStop.setVisibility(View.INVISIBLE);
                                if ("2".equalsIgnoreCase(mTskRoot.getTp())) {
                                    VideoUtils.stopH2645Thread();
                                    mFlVdContain.removeAllViews();
                                }
                            }
                        }
                    }
                    break;
                // 授权状态反馈
                case "auth":
                    if ("ok".equalsIgnoreCase(appEvent.getKey())) {
                        showMsg("授权成功！");
                    } else {
                        showMsg("授权失败！");
                    }
                    break;
                // 对讲任务状态反馈
                case "tk_tsk_status":
                    TskRoot tskRoot = XmlUtils.toBean(TskRoot.class, value.getBytes());
                    if (tskRoot != null) {
                        // 状态值参考ndk_wrapper类中的avsz_usr_call_req函数说明
                        switch (tskRoot.getStatus()) {
                            case "0":
                                mTskRoot = null;
                                mLlMenuContain.setVisibility(View.INVISIBLE);
                                mBtnStop.setVisibility(View.INVISIBLE);
                                // 非音频，需要视频解码线程
                                if (!"2".equalsIgnoreCase(tskRoot.getTp())) {
                                    VideoUtils.stopH2645Thread();
                                    mFlVdContain.removeAllViews();
                                }
                                break;
                            case "2":
                                mTskRoot = tskRoot;
                                mLlMenuContain.setVisibility(View.VISIBLE);
                                mBtnSure.setVisibility(View.INVISIBLE);
                                mBtnCancel.setVisibility(View.VISIBLE);
                                mBtnCancel.setText("取消");
                                mBtnBusy.setVisibility(View.INVISIBLE);
                                break;
                            case "3":
                                mTskRoot = tskRoot;
                                break;
                            case "4":
                                mTskRoot = tskRoot;
                                if (!"2".equalsIgnoreCase(tskRoot.getTp())) {
                                    VideoUtils.startH2645Thread(getSurfaceView());
                                }
                                if (mCallRoot != null) {
                                    // 当被呼叫者为当前用户时，因在请求关键帧需要，置换双方的GUID
                                    mTskRoot.setCallee_guid(mCallRoot.getCaller_guid());
                                    mCallRoot = null;
                                }
                                mLlMenuContain.setVisibility(View.INVISIBLE);
                                mBtnStop.setVisibility(View.VISIBLE);
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                // 更新音频播放反馈
                case "update_aud_play":
                    UpdateRoot updateRoot = XmlUtils.toBean(UpdateRoot.class, value.getBytes());
                    if (updateRoot != null) {
                        String stp = updateRoot.getStp();
                        AudioUtils.startPcmThread(Integer.parseInt(updateRoot
                                .getRate()), Integer.parseInt(updateRoot.getSync_flag
                                ()), stp.contains("broad"), "mp3broad".equalsIgnoreCase(stp) ? 0
                                : 1);
                        if (stp.contains("broad")) {
                            showMsg("广播中！");
                        }
                    }
                    break;
                case "stop_aud_trans":
                    AudioUtils.stopPcmThread();
                    break;
                // 自定义本地消息
                case "decoder_created":
                    if ((mTskRoot != null || mMonitorRoot != null) && mUsrs != null) {
                        AioThreadPool.getExecutor().execute(() -> {
                            String calleeGuid, tskGuid;
                            if (mTskRoot != null) {
                                calleeGuid = mTskRoot.getCallee_guid();
                                tskGuid = mTskRoot.getTsk_guid();
                            } else {
                                calleeGuid = mMonitorRoot.getCallee_guid();
                                tskGuid = mMonitorRoot.getTsk_guid();
                            }
                            for (Usr usr : mUsrs) {
                                // 请求关键帧，仅对终端有效
                                if (usr.getId().equalsIgnoreCase(calleeGuid)) {
                                    if ("2".equalsIgnoreCase(usr.getTp())) {
                                        int result = ndk_wrapper.getInstance().avsz_req_idr
                                                (mSessId, tskGuid);
                                        Log.d(TAG, "[avsz_req_idr] result: " + result);
                                    }
                                    break;
                                }
                            }
                        });
                    }
                    break;
                // 开启音视频服务的反馈
                case "start_av":
                    StartRoot startRoot = XmlUtils.toBean(StartRoot.class, value.getBytes());
                    if (startRoot != null) {
                        String tmp = "ok";
                        switch (startRoot.getTp()) {
                            case "av":
                                EncodeUtils.setVideoStrm(Integer.parseInt(startRoot.getStrm()));
                                if (!CameraUtils.createCameraService(Camera.CameraInfo
                                        .CAMERA_FACING_BACK)) {
                                    tmp = "already";
                                }
                                AudioUtils.setAudioStrm(Integer.parseInt(startRoot.getStrm()));
                                if (!AudioUtils.createAudioCapture(AudioCapture
                                        .DEFAULT_SAMPLE_RATE)) {
                                    tmp = "already";
                                }
                                break;
                            case "vd":
                                EncodeUtils.setVideoStrm(Integer.parseInt(startRoot.getStrm()));
                                if (!CameraUtils.createCameraService(Camera.CameraInfo
                                        .CAMERA_FACING_BACK)) {
                                    tmp = "already";
                                }
                                break;
                            case "ad":
                                AudioUtils.setAudioStrm(Integer.parseInt(startRoot.getStrm()));
                                if (!AudioUtils.createAudioCapture(AudioCapture
                                        .DEFAULT_SAMPLE_RATE)) {
                                    tmp = "already";
                                }
                                break;
                            default:
                                tmp = "fail";
                                break;
                        }
                        // 启动完相应的音视频服务，需要给服务器反馈
                        int result = ndk_wrapper.getInstance().avsz_start_av_ret(mSessId,
                                startRoot.getTsk_guid(), tmp, Integer.parseInt(startRoot
                                        .getSess()), AudioCapture.DEFAULT_SAMPLE_RATE);
                        Log.d(TAG, "[avsz_start_av_ret] result: " + result);
                    }
                    break;
                // 停止音视频服务的反馈
                case "stop_av":
                    switch (appEvent.getKey()) {
                        case "ad":
                            AudioUtils.destroyAudioCapture();
                            break;
                        case "vd":
                            CameraUtils.destroyCameraService();
                            break;
                        default:
                            break;
                    }
                    break;
                // 终端或用户呼叫请求反馈
                case "usr_call_req":
                    CallRoot callRoot = XmlUtils.toBean(CallRoot.class, value.getBytes());
                    if (callRoot != null && mUsrs != null) {
                        AioThreadPool.getExecutor().execute(() -> {
                            for (Usr usr : mUsrs) {
                                if (usr.getName().equalsIgnoreCase(callRoot.getCaller_name())
                                        && usr.getId().equalsIgnoreCase(callRoot
                                        .getCaller_guid())) {
                                    UiUtils.runOnUiThread(() -> {
                                        mCallRoot = callRoot;
                                        showMsg("[" + usr.getName() + "]正在呼叫你......");
                                        mLlMenuContain.setVisibility(View.VISIBLE);
                                        mBtnSure.setVisibility(View.VISIBLE);
                                        mBtnCancel.setVisibility(View.VISIBLE);
                                        mBtnCancel.setText("拒绝");
                                        mBtnBusy.setVisibility(View.VISIBLE);
                                        showFunctionMenu();
                                    });
                                    break;
                                }
                            }
                        });
                    }
                    break;
                // 终端或用户取消呼叫请求
                case "usr_call_req_cancel":
                    CancelRoot cancelRoot = XmlUtils.toBean(CancelRoot.class, value.getBytes());
                    if (cancelRoot != null && mCallRoot != null) {
                        if (cancelRoot.getTsk_guid().equalsIgnoreCase(mCallRoot.getTsk_guid()
                        )) {
                            mCallRoot = null;
                            mLlMenuContain.setVisibility(View.INVISIBLE);
                        }
                    }
                    break;
                case "monitor_tsk_status":
                    MonitorRoot monitorRoot = XmlUtils.toBean(MonitorRoot.class, value
                            .getBytes());
                    if (monitorRoot != null) {
                        switch (monitorRoot.getStatus()) {
                            // 任务已停止
                            case "0":
                                mMonitorRoot = null;
                                mLlMenuContain.setVisibility(View.INVISIBLE);
                                mBtnStop.setVisibility(View.INVISIBLE);
                                // 非音频，需要视频解码线程
                                if (!"2".equalsIgnoreCase(monitorRoot.getTp())) {
                                    VideoUtils.stopH2645Thread();
                                    mFlVdContain.removeAllViews();
                                }
                                break;
                            // 任务已运行
                            case "2":
                                mMonitorRoot = monitorRoot;
                                if (!"2".equalsIgnoreCase(monitorRoot.getTp())) {
                                    VideoUtils.startH2645Thread(getSurfaceView());
                                }
                                mLlMenuContain.setVisibility(View.INVISIBLE);
                                mBtnStop.setVisibility(View.VISIBLE);
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                // 手动执行任务（喊话）反馈
                case "exec_manual_tsk_ret":
                    mExecManualTskRet = XmlUtils.toBean(ExecManualTskRet
                            .class, value.getBytes());
                    if (mExecManualTskRet != null) {
                        if ("ok".equalsIgnoreCase(mExecManualTskRet.getRet())) {
                            showMsg("执行成功！");
                            mBtnStop.setVisibility(View.VISIBLE);
                        } else {
                            showMsg("执行失败！");
                        }
                    }
                    break;
                case "bc_usr_tsk_status":
                    BcUsrTskRoot bcUsrTskRoot = XmlUtils.toBean(BcUsrTskRoot.class, value
                            .getBytes());
                    if (bcUsrTskRoot != null) {
                        if (mTsks != null) {
                            AioThreadPool.getExecutor().execute(() -> {
                                boolean flag = false;
                                for (Tsk tsk : mTsks) {
                                    Row row = tsk.getRow();
                                    if (row.getTsk_guid().getVal().equalsIgnoreCase
                                            (bcUsrTskRoot.getTsk_guid())) {
                                        row.getStatus().setVal(bcUsrTskRoot.getStatus());
                                        flag = true;
                                    }
                                }
                                if (flag && mLvTaskAdapter != null) {
                                    UiUtils.runOnUiThread(() -> mLvTaskAdapter.update(mTsks));
                                }
                                // 不在任务列表中
                                if (!flag) {
                                    UiUtils.runOnUiThread(() -> {
                                        switch (bcUsrTskRoot.getStatus()) {
                                            case "0":
                                                mBcUsrTskRoot = null;
                                                mBtnStop.setVisibility(View.INVISIBLE);
                                                break;
                                            case "2":
                                                mBcUsrTskRoot = bcUsrTskRoot;
                                                mExecManualTskRet = null;
                                                mBtnStop.setVisibility(View.VISIBLE);
                                                break;
                                            default:
                                                break;
                                        }
                                    });
                                }
                            });
                            // 任务类型为实时采播时任务状态回调为bc_usr_tsk_status
                            if (mTsk != null) {
                                if (!"0".equalsIgnoreCase(bcUsrTskRoot.getStatus())) {
                                    mBtnTakDelete.setVisibility(View.GONE);
                                    mBtnTaskSure.setEnabled(false);
                                } else {
                                    mBtnTakDelete.setVisibility(View.VISIBLE);
                                    mBtnTaskSure.setEnabled(true);
                                }
                            }
                        } else {
                            switch (bcUsrTskRoot.getStatus()) {
                                case "0":
                                    mBcUsrTskRoot = null;
                                    mBtnStop.setVisibility(View.INVISIBLE);
                                    break;
                                case "2":
                                    mBcUsrTskRoot = bcUsrTskRoot;
                                    mExecManualTskRet = null;
                                    mBtnStop.setVisibility(View.VISIBLE);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    break;
                // 节目库数据刷新反馈
                case "prog_refresh":
                    RefreshRoot refreshRoot = XmlUtils.toBean(RefreshRoot.class, value
                            .getBytes());
                    if (refreshRoot != null) {
                        if (refreshRoot.getProgs() != null && refreshRoot.getProgs().getProg
                                () != null) {
                            AioThreadPool.getExecutor().execute(() -> {
                                if (mProgs == null) {
                                    mProgs = new ArrayList<>();
                                } else {
                                    mProgs.clear();
                                }
                                for (Prog prog : refreshRoot.getProgs().getProg()) {
                                    // 去除目录路径（时间为00:00:00）和过滤非MP3文件
                                    String name = prog.getName();
                                    if (!TextUtils.isEmpty(name) && (name.contains(".mp3") ||
                                            name.contains(".MP3"))) {
                                        mProgs.add(prog);
                                    }
                                }
                                if (!mProgs.isEmpty() && mLvProgAdapter != null) {
                                    UiUtils.runOnUiThread(() -> mLvProgAdapter.update(mProgs));
                                }
                            });
                        }
                    }
                    break;
                // 定时节目状态反馈
                case "bc_file_tsk_status":
                    BcFileTskStatusRoot bcFileTskStatusRoot = XmlUtils.toBean
                            (BcFileTskStatusRoot.class, value.getBytes());
                    if (bcFileTskStatusRoot != null && mTsks != null) {
                        AioThreadPool.getExecutor().execute(() -> {
                            boolean flag = false;
                            for (Tsk tsk : mTsks) {
                                Row row = tsk.getRow();
                                if (row.getTsk_guid().getVal().equalsIgnoreCase
                                        (bcFileTskStatusRoot.getTsk_guid())) {
                                    row.getStatus().setVal(bcFileTskStatusRoot.getStatus());
                                    flag = true;
                                    break;
                                }
                            }
                            if (flag && mLvTaskAdapter != null) {
                                UiUtils.runOnUiThread(() -> mLvTaskAdapter.update(mTsks));
                            }
                        });
                        // 任务类型为文件节目时任务状态回调为bc_file_tsk_status
                        if (mTsk != null) {
                            // 正在执行的任务不能再编辑或删除
                            if (bcFileTskStatusRoot.getTsk_guid().equalsIgnoreCase(mTsk
                                    .getRow().getTsk_guid().getVal())) {
                                if (!"0".equalsIgnoreCase(bcFileTskStatusRoot.getStatus())) {
                                    mBtnTakDelete.setVisibility(View.GONE);
                                    mBtnTaskSure.setEnabled(false);
                                } else {
                                    mBtnTakDelete.setVisibility(View.VISIBLE);
                                    mBtnTaskSure.setEnabled(true);
                                }
                            }
                        }
                    }
                    break;
                // 删除任务反馈（注意：不一定是当前用户的操作，有可能是在服务端删除了任务！）
                case "task_del_ret":
                    if (mTsk != null) {
                        if (appEvent.getKey().equalsIgnoreCase(mTsk.getRow().getTsk_guid()
                                .getVal())) {
                            if ("ok".equalsIgnoreCase(appEvent.getValue())) {
                                showMsg("[" + mTsk.getRow().getTsk_name() + "]删除成功！");
                            } else {
                                showMsg("[" + mTsk.getRow().getTsk_name() + "]删除失败！");
                            }
                            mTsk = null;
                            clearTaskOperation();
                            mAddOrEditTskOp = false;
                        }
                    }
                    if (mTsks != null) {
                        AioThreadPool.getExecutor().execute(() -> {
                            Iterator<Tsk> iterator = mTsks.iterator();
                            while (iterator.hasNext()) {
                                Tsk tsk = iterator.next();
                                if (appEvent.getKey().equalsIgnoreCase(tsk.getRow()
                                        .getTsk_guid().getVal())) {
                                    iterator.remove();
                                    break;
                                }
                            }
                            if (mLvTaskAdapter != null) {
                                UiUtils.runOnUiThread(() -> mLvTaskAdapter.update(mTsks));
                            }
                        });
                    }
                    break;
                // 添加任务反馈
                case "task_add_ret":
                    if (mTsk != null) {
                        if ("ok".equalsIgnoreCase(value)) {
                            mTsk.getRow().setTsk_guid(new TskGuid(appEvent.getKey()));
                            if (mTsks == null) {
                                mTsks = new ArrayList<>();
                            }
                            mTsks.add(mTsk);
                            if (mLvTaskAdapter != null) {
                                mLvTaskAdapter.update(mTsks);
                            }
                            showMsg("[" + mTsk.getRow().getTsk_name().getVal() + "]添加成功！");
                        } else {
                            showMsg("[" + mTsk.getRow().getTsk_name().getVal() + "]添加失败！");
                        }
                        mTsk = null;
                        clearTaskOperation();
                        mAddOrEditTskOp = false;
                    }
                    break;
                // 编辑任务反馈
                case "task_edit_ret":
                    if (mTsk != null) {
                        if (mTsk.getRow().getTsk_guid().getVal().equalsIgnoreCase(appEvent
                                .getKey())) {
                            if ("ok".equalsIgnoreCase(value)) {
                                if (mTsks != null) {
                                    AioThreadPool.getExecutor().execute(() -> {
                                        Iterator<Tsk> iterator = mTsks.iterator();
                                        int index = 0;
                                        while (iterator.hasNext()) {
                                            index++;
                                            Tsk tsk = iterator.next();
                                            if (tsk.getRow().getTsk_guid().getVal()
                                                    .equalsIgnoreCase(appEvent.getKey())) {
                                                iterator.remove();
                                                break;
                                            }
                                        }
                                        mTsks.add(index, mTsk);
                                        if (mLvTaskAdapter != null) {
                                            UiUtils.runOnUiThread(() -> mLvTaskAdapter.update
                                                    (mTsks));
                                        }
                                    });
                                }
                                showMsg("[" + mTsk.getRow().getTsk_name().getVal() + "]编辑成功！");
                            } else {
                                showMsg("[" + mTsk.getRow().getTsk_name().getVal() + "]编辑失败！");
                            }
                            mTsk = null;
                            clearTaskOperation();
                            mAddOrEditTskOp = false;
                        }
                    }
                default:
                    break;
            }
        }*/
    }
}
