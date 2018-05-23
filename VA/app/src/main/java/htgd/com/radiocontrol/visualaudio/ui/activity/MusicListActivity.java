package htgd.com.radiocontrol.visualaudio.ui.activity;

import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.broadcast.android.android_sta_jni_avsz.ndk_wrapper;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import htgd.com.radiocontrol.visualaudio.base.BaseActivity;
import htgd.com.radiocontrol.visualaudio.model.EventBusEvent;
import htgd.com.radiocontrol.visualaudio.pojo.AppEvent;
import htgd.com.radiocontrol.visualaudio.pojo.login.LoginRoot;
import htgd.com.radiocontrol.visualaudio.pojo.prog.Prog;
import htgd.com.radiocontrol.visualaudio.pojo.refresh.RefreshRoot;
import htgd.com.radiocontrol.visualaudio.pojo.task.Dsts;
import htgd.com.radiocontrol.visualaudio.pojo.task.Row;
import htgd.com.radiocontrol.visualaudio.pojo.task.SrcInfo;
import htgd.com.radiocontrol.visualaudio.pojo.task.Srcs;
import htgd.com.radiocontrol.visualaudio.pojo.task.TaskRoot;
import htgd.com.radiocontrol.visualaudio.pojo.task.Tsk;
import htgd.com.radiocontrol.visualaudio.pojo.task.UsrGuid;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskChNum;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskDuration;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskFlagRandom;
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
import htgd.com.radiocontrol.visualaudio.thread.AioThreadPool;
import htgd.com.radiocontrol.visualaudio.ui.adapter.MusicGridViewAdapter;

import htgd.com.radiocontrol.visualaudio.ui.adapter.ViewPagerMusicAdapter;
import htgd.com.radiocontrol.visualaudio.utils.DateUtils;
import htgd.com.radiocontrol.visualaudio.utils.LogUtils;
import htgd.com.radiocontrol.visualaudio.utils.UiUtils;
import htgd.com.radiocontrol.visualaudio.utils.Utils;
import htgd.com.radiocontrol.visualaudio.utils.XmlUtils;
import htgd.com.radiocontrol.visualaudio.widget.CallDialog;
import radiocontrol.htgd.com.visualaudio.R;


public class MusicListActivity extends BaseActivity implements View.OnClickListener {
    private LayoutInflater mInflater;
    private ArrayList<View> mViewList = new ArrayList<>();//页卡视图集合
    private ViewHolder vHolder = new ViewHolder();
    private MusicGridViewAdapter gridViewAdapter;
    private ArrayList<Prog> musicList= new ArrayList<>();
    private ArrayList<Prog> sMusicList  = new ArrayList<>();;
    private ImageView btSetting;
    private TextView state;
    private TextView next;
    private TextView back;
    private ArrayList<Usr> userList;
   private  String Tag="MusicListActivity";
    private int s;
    private CallDialog callDialog;
    private Tsk tsk;
    private Row row;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_music_list;
    }

    @Override
    protected void initSubViews() {

       userList= (ArrayList<Usr>)getIntent().getSerializableExtra("userlist");
       musicList=(ArrayList<Prog>)getIntent().getSerializableExtra("musiclist");
       LogUtils.setLog("yinyue"+musicList.size());
        gridViewAdapter = new MusicGridViewAdapter(this, musicList);
        initView();
    }

    // 设置滑动监听
    private void setListerForGird() {
        ViewPagerMusicAdapter viewPagerMusicAdapter = new ViewPagerMusicAdapter(this, mViewList);
        vHolder.mViewPager.setAdapter(viewPagerMusicAdapter);
        vHolder.mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTitleView(position);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vHolder.music_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (musicList.get(position).isChoose) {
                    musicList.get(position).setChoose(false);
                    --s;
                } else {
                    musicList.get(position).setChoose(true);
                    ++s;
                }
               LogUtils.setLog(Tag,"xuan l "+s);
                gridViewAdapter.notifyDataSetChanged();
                if(s>0){
                    next.setVisibility(View.VISIBLE);

                }else{
                    next.setVisibility(View.INVISIBLE);
                }
            }
        });
        vHolder.localMusic_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (musicList.get(position).isChoose) {
                    musicList.get(position).setChoose(false);
                    --s;
                } else {
                    musicList.get(position).setChoose(true);
                    ++s;
                }

                gridViewAdapter.notifyDataSetChanged();
                LogUtils.setLog(Tag,"xuan l "+s);
                if(s>0){
                    next.setVisibility(View.VISIBLE);

                }else{
                    next.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_next:
                for(int i=0;i<musicList.size();i++){
                    if(musicList.get(i).isChoose){
                        sMusicList.add(musicList.get(i));
                    }
                }
                execPlayTsk(userList, sMusicList);
                 showPlayingMusicDialog();
                break;
            case  R.id.title_back:
                finish();
                break;

        }
    }

    private void showPlayingMusicDialog() {
        callDialog = new CallDialog(this, new CallDialog.OnViewClickListener() {
            @Override
            public void cancel() {
                //停止点播任务
                Log.d(Tag, "关闭的点播任务id " );
                int  result = ndk_wrapper.getInstance().avsz_task_stop(1, row.getTsk_guid().getVal());
                Log.d(Tag, "[avsz_task_stop] result: " + result);

            }
        });
        callDialog.show();
    }

    private class ViewHolder {
        GridView music_gv, localMusic_gv;
        ViewPager mViewPager;
        TextView table_text_1, table_text_2; //头部显示卡，注意tableLayout 在4.4 手机上找不到资源
        View table_line_1, table_line_2;
        View view1, view2;//页卡视图
    }

    private void initView() {
        btSetting = (ImageView) findViewById(R.id.set);
        btSetting.setOnClickListener(this);
        state = (TextView) findViewById(R.id.state);
        next = (TextView) findViewById(R.id.title_next);
        next.setVisibility(View.INVISIBLE);
        next.setOnClickListener(this);
        back = (TextView) findViewById(R.id.title_back);
        back.setOnClickListener(this);

        vHolder.mViewPager = (ViewPager) findViewById(R.id.vp_view);
        vHolder.table_text_1 = (TextView) findViewById(R.id.table_text1);
        vHolder.table_text_2 = (TextView) findViewById(R.id.table_text2);
        vHolder.table_line_1 = findViewById(R.id.table_line1);
        vHolder.table_line_2 = findViewById(R.id.table_line2);
        mInflater = LayoutInflater.from(this);
        vHolder.view1 = mInflater.inflate(R.layout.music_gridview, null);
        vHolder.view2 = mInflater.inflate(R.layout.music_gridview, null);
        vHolder.localMusic_gv = (GridView) vHolder.view1.findViewById(R.id.music_grid);
        vHolder.music_gv = (GridView) vHolder.view2.findViewById(R.id.music_grid);
        vHolder.music_gv.setAdapter(gridViewAdapter);
        vHolder.localMusic_gv.setAdapter(gridViewAdapter);
        mViewList.add(vHolder.view1);
        mViewList.add(vHolder.view2);
        setListerForGird();
    }

    private void changeTitleView(int position) {
        if (position == 0) {
            vHolder.table_text_1.setTextColor(this.getResources().getColor(R.color.colorBlue));
            vHolder.table_line_1.setBackgroundResource(R.color.colorBlue);
            vHolder.table_text_2.setTextColor(this.getResources().getColor(R.color.black));
            vHolder.table_line_2.setBackgroundResource(R.color.white);
        } else if (position == 1) {
            vHolder.table_text_1.setTextColor(this.getResources().getColor(R.color.black));
            vHolder.table_line_1.setBackgroundResource(R.color.white);
            vHolder.table_text_2.setTextColor(this.getResources().getColor(R.color.colorBlue));
            vHolder.table_line_2.setBackgroundResource(R.color.colorBlue);
        }
    }

    /**
     * 执行点播任务
     *
     * @param usrList  目标终端或主机数据集
     * @param progList 播放节目数据集
     */
    private void execPlayTsk(List<Usr> usrList, List<Prog> progList) {
        TaskRoot taskRoot = new TaskRoot();
        taskRoot.setOrd("exec_manual_tsk");
        tsk = new Tsk();
         row = new Row();
        // 随机播放 0[顺序] 1[随机]
        row.setTsk_flag_random(new TskFlagRandom(/*mRgContain.getCheckedRadioButtonId() == R.id
                .activity_main_rb_order ? "0" : "1"*/"0"));
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
        // usrGuids.add(new UsrGuid(mUsrGuid));
        dsts.setUsr_guid(usrGuids);
        tsk.setDsts(dsts);
        taskRoot.setTsk(tsk);
        String xml = XmlUtils.toXml(taskRoot);
        if (!TextUtils.isEmpty(xml)) {

            int result = ndk_wrapper.getInstance().avsz_send_svr_xml(/*mSessId*/1, xml);
           LogUtils.setLog(Tag,"点播启动result"+result);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusEvent event) {
        AppEvent appEvent = event.getMessage();
        String value = appEvent.getValue();
        LogUtils.setLog(Tag, "this is event music");
        switch (appEvent.getType()) {
            // 节目库数据刷新反馈
            case "prog_refresh":
                Log.e("fankuil", "yinyuejiemushujushuaxin");
                RefreshRoot refreshRoot = XmlUtils.toBean(RefreshRoot.class, value
                        .getBytes());
                if (refreshRoot != null) {
                    if (refreshRoot.getProgs() != null && refreshRoot.getProgs().getProg
                            () != null) {
                        AioThreadPool.getExecutor().execute(() -> {
                            if (musicList == null) {
                                musicList = new ArrayList<>();
                            } else {
                                musicList.clear();
                            }
                            for (Prog prog : refreshRoot.getProgs().getProg()) {
                                // 去除目录路径（时间为00:00:00）和过滤非MP3文件
                                String name = prog.getName();
                                if (!TextUtils.isEmpty(name) && (name.contains(".mp3") ||
                                        name.contains(".MP3"))) {
                                    musicList.add(prog);
                                }
                            }
                            if (!musicList.isEmpty() && gridViewAdapter != null) {
                                UiUtils.runOnUiThread(() -> gridViewAdapter.refrech(musicList));
                            }
                        });
                    }
                }
                break;
        }
    }
}
