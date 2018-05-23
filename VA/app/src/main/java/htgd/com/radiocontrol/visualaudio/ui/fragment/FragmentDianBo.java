package htgd.com.radiocontrol.visualaudio.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import htgd.com.radiocontrol.visualaudio.base.BaseFragment;
import htgd.com.radiocontrol.visualaudio.pojo.prog.Prog;
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
import htgd.com.radiocontrol.visualaudio.pojo.usr.Usr;
import htgd.com.radiocontrol.visualaudio.utils.DateUtils;
import htgd.com.radiocontrol.visualaudio.utils.LogUtils;
import htgd.com.radiocontrol.visualaudio.utils.MediaUtils;
import htgd.com.radiocontrol.visualaudio.utils.XmlUtils;

public class FragmentDianBo extends BaseFragment {

  /*  private View view;
    private List<MusicBean> musicList;
    private RecyclerView lRecycleview;
    private RecyclerView uRecycleview;
    private Context mContext;
    private ArrayList<MusicBean> uMusicList;
    private  String Tag="FragmentDianBo";
    private RegisterReceiver mBroadcastReceiver;

    public static FragmentDianBo getInstance(Context context) {
        FragmentDianBo fgDianBo = new FragmentDianBo();
        Bundle bundle = new Bundle();
        fgDianBo.setArguments(bundle);
        return fgDianBo;
    }

    public FragmentDianBo() {
   super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mContext = this.getActivity();
        view = inflater.inflate(R.layout.fragment_dianbo, container, false);
        lRecycleview = (RecyclerView) view.findViewById(R.id.local);
        uRecycleview=(RecyclerView)view.findViewById(R.id.uPan);
        uRecycleview.setLayoutManager(new LinearLayoutManager(mContext));
        lRecycleview.setLayoutManager(new GridLayoutManager(mContext, 2));
        initData();
        showData();
        return view;
    }


    public void initData() {
        musicList=new ArrayList<>();
        for(int i=0;i<20;i++) {
            musicList.add(new MusicBean("feng"));
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
           MediaUtils.scannerMusic(mContext);
           LogUtils.setLog(Tag,"get u file'size"+ MediaUtils.getMusic(mContext).size());
            }
        }).start();
        registerUsbBroadcast();

    }
    //注册广播
    private void registerUsbBroadcast() {
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(Intent.ACTION_MEDIA_EJECT);
        iFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        iFilter.addAction(Intent.ACTION_MEDIA_REMOVED);
        iFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        iFilter.addDataScheme("file");
           mBroadcastReceiver=  new RegisterReceiver();
        mContext.registerReceiver(mBroadcastReceiver,iFilter);
        LogUtils.setLog(Tag+"usb ");
    }
     //动态广播类
    class RegisterReceiver extends BroadcastReceiver{
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(Intent.ACTION_MEDIA_EJECT)) {
                    //USB设备移除，更新UI
                    LogUtils.setLog(Tag+"usb  clear");
                } else if (action.equals(Intent.ACTION_MEDIA_MOUNTED)) {
                    //USB设备挂载，更新UI
                    String usbPath = intent.getDataString();
                    LogUtils.setLog(Tag+"usb get on");

                }
            }
        };

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.unregisterReceiver(mBroadcastReceiver);
    }

    public  void  Search(File file, String  e){
        if(file!=null){
            if(file.isDirectory()){
                File[]  listFile=file.listFiles();
                if(listFile!=null){
                    for(int i=0;i<listFile.length;i++){
                        Search(listFile[i],e);

                    }
                }
            }else{
               String filename= file.getAbsolutePath();
                String name = file.getName();
                if(filename.endsWith(e));
                musicList.add(new MusicBean(filename));
            }
        }
     }
    public void showData() {

        LogUtils.setLog("XUAN","sd");
        CommonAdapter<MusicBean> commonAdapter = new CommonAdapter<MusicBean>(mContext, R.layout.item_machine, musicList) {
            @Override
            protected void convert(ViewHolder holder, MusicBean bean, int position) {
                LogUtils.setLog("XUAN","sd");
                holder.setText(R.id.name, "导演:" + musicList.get(position).getName());
            }
        };
        LoadMoreWrapper mLoadMoreWrapper = new LoadMoreWrapper(commonAdapter);
        lRecycleview.setAdapter(mLoadMoreWrapper);

        CommonAdapter<MusicBean> commonAdapters = new CommonAdapter<MusicBean>( mContext, R.layout.item_machine, musicList) {
            @Override
            protected void convert(ViewHolder holder, MusicBean bean, int position) {
                LogUtils.setLog("XUAN");
                holder.setText(R.id.name, "导演:" + musicList.get(position).getName());
            }
        };
        LoadMoreWrapper mLoadMoreWrappers = new LoadMoreWrapper(commonAdapters);
        uRecycleview.setAdapter(mLoadMoreWrappers);
        *//* *//**//**
         * 配置加载更多(通用适配器里面的类哦)
         *//**//*
        LoadMoreWrapper mLoadMoreWrapper = new LoadMoreWrapper(commonAdapter);//加载更多的包装器(传入通用适配器)
        View view = View.inflate(mActivity, R.layout.load_more, null);
        //要设置一下的布局参数,因为布局填充到包装器的时候,自己的一些属性会无效
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(mLayoutParams);
        load_more = (TextView) view.findViewById(R.id.tv_load_more);
        //监听点击加载更多事件
        load_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_more.setText("加载中...");//点击加载更多-->加载中
                mMoviePresenter.loadMoreMovie();
            }
        });
        mLoadMoreWrapper.setLoadMoreView(view);
        rvMovieList.setAdapter(mLoadMoreWrapper);//注意  这里添加的是包装器 不是适配器哦*//*
    }

    *//**
     * 执行点播任务
     *
     * @param usrList  目标终端或主机数据集
     * @param progList 播放节目数据集
     *//*
    private void execPlayTsk(List<Usr> usrList, List<Prog> progList) {
        TaskRoot taskRoot = new TaskRoot();
        taskRoot.setOrd("exec_manual_tsk");
        Tsk tsk = new Tsk();
        Row row = new Row();
        // 随机播放 0[顺序] 1[随机]
       *//* row.setTsk_flag_random(new TskFlagRandom(mRgContain.getCheckedRadioButtonId() == R.id
                .activity_main_rb_order ? "0" : "1"));*//*
        // 任务音量可自行设置，100以内！
        row.setTsk_play_volumn(new TskPlayVolumn("70"));
        row.setTsk_src_type(new TskSrcType("1"));
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
        for (Prog prog : progList) {
            srcInfos.add(new SrcInfo(prog.getPath() + "\\" + prog.getName()));
        }
        srcs.setSrc_info(srcInfos);
        tsk.setSrcs(srcs);
        Dsts dsts = new Dsts();
        List<UsrGuid> usrGuids = new ArrayList<>();
        for (Usr usr : usrList) {
            usrGuids.add(new UsrGuid(usr.getId()));
        }
        // 可以把当前用户也添加进去
       // usrGuids.add(new UsrGuid(mUsrGuid));//
        dsts.setUsr_guid(usrGuids);
        tsk.setDsts(dsts);
        taskRoot.setTsk(tsk);
        String xml = XmlUtils.toXml(taskRoot);
        if (!TextUtils.isEmpty(xml)) {
            Log.d(Tag, "play tsk[xml]: " + xml);
        *//*    int result = ndk_wrapper.getInstance().avsz_send_svr_xml(mSessId, xml);//
            Log.d(Tag, "[avsz_send_svr_xml] result: " + result);*//*
        }
    }*/
}
