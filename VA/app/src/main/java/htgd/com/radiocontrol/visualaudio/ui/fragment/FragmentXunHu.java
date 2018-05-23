package htgd.com.radiocontrol.visualaudio.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.broadcast.android.android_sta_jni_avsz.ndk_wrapper;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import htgd.com.radiocontrol.visualaudio.base.BaseFragment;
import htgd.com.radiocontrol.visualaudio.base.Constants;

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
import htgd.com.radiocontrol.visualaudio.utils.PreferencesUtil;
import htgd.com.radiocontrol.visualaudio.utils.XmlUtils;
import radiocontrol.htgd.com.visualaudio.R;

public class FragmentXunHu extends  BaseFragment{


    private View view;
    private RecyclerView xListview;
    private String Tag="FragmentXunHu";
    private Context mContext;
    private int msessid;
    private String userId;
    private ArrayList<Usr> userList;

    public FragmentXunHu( ){

    }

    public  static FragmentXunHu newInstance(Context context){
        FragmentXunHu fgXunhu=   new FragmentXunHu();
        Bundle bundle = new Bundle();
        fgXunhu.setArguments(bundle);

        return  fgXunhu;
    }
    public void initData() {
        userList=new ArrayList<>();
        for(int i=0;i<20;i++) {
            userList.add(new Usr( ));
        }
        

    }
    public void showData() {


        CommonAdapter<Usr> commonAdapter = new CommonAdapter<Usr>(mContext, R.layout.item_machine, userList) {
            @Override
            protected void convert(ViewHolder holder, Usr bean, int position) {

                holder.setText(R.id.name, "导演:" + userList.get(position).getName());
            }
        };
        LoadMoreWrapper mLoadMoreWrapper = new LoadMoreWrapper(commonAdapter);
        xListview.setAdapter(mLoadMoreWrapper);

        /* *//**
         * 配置加载更多(通用适配器里面的类哦)
         *//*
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
        rvMovieList.setAdapter(mLoadMoreWrapper);//注意  这里添加的是包装器 不是适配器哦*/
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mContext = this.getActivity();
        view=inflater.inflate(R.layout.fragment_xunhu,container,false);
        xListview=(RecyclerView)view.findViewById(R.id.xunhu_listview);
       msessid= Integer.parseInt(PreferencesUtil.getInstance().getField(Constants.msessid,this.getActivity()));
       userId=  PreferencesUtil.getInstance().getField(Constants.userId,this.getActivity());
       initData();
       showData();
        xListview.setLayoutManager(new GridLayoutManager(mContext, 5));
        return  view;
    }
    /**
     * 执行手动任务（喊话）寻呼
     *
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
        SrcInfo srcInfo = new SrcInfo(userId);
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
            int result = ndk_wrapper.getInstance().avsz_send_svr_xml(msessid, xml);
            Log.d(Tag, "[avsz_send_svr_xml] result: " + result);
        }
    }
}
