package htgd.com.radiocontrol.visualaudio.pojo.task;


import com.thoughtworks.xstream.annotations.XStreamAlias;

import htgd.com.radiocontrol.visualaudio.pojo.task.attr.Status;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskAlmAudFile;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskChNum;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskDbAlmLevel;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskDuration;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskFileSec;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskFlagDbAlm;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskFlagRandom;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskForceSignal;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskFreeze;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskGuid;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskHasDura;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskHasStopTime;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskJobType;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskMdTp;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskName;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskOffline;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskOnlyPower;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskPlayStrategyTp;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskPlayVolumn;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskProjGuid;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskSrcType;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskStartTime;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskStopTime;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskStrmMode;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskSyncStrategyCnt;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskTransProto;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskUsrGuid;
import htgd.com.radiocontrol.visualaudio.pojo.task.attr.TskWeekData;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("row")
public class Row {
    /**
     * <row>
     * <status val="0"/>
     * <tsk_guid val=”{151196D6-33D7-4426-9E3C-E374FE12AE51}” />
     * <tsk_name val=”任务名[utf8编码]” />
     * <tsk_usr_guid val=” {B7BEE137-901C-46C2-A7F8-DBA588A04774}” />
     * <tsk_src_type val=” 0[空源] 1[文件]2[指定声卡]3[指定IP终端或用户]” />
     * <tsk_file_sec val=”50[文件总时长秒数]”/>
     * <tsk_job_type val=”0[每天任务]1[一次任务]2[手动任务]7[每周任务]” />
     * <tsk_start_time val=”2017-07-31 14:00:00[任务开始时间]” />
     * <tsk_has_dura val=”0/1[是否指定持续时间]” />
     * <tsk_duration val=”30[指定持续时间总秒数]” />
     * <tsk_has_stop_time val=”0/1[是否指定任务有效截止时间]” />
     * <tsk_stop_time val=”2017-08-10 16:00:00[任务有效截止时间]” />
     * <tsk_week_data val=” 0110011 [从周日到周六,1表示生效，0表示失效]” />
     * <tsk_flag_random val=”0/1[是否随机标识]” />
     * <tsk_flag_db_alm val=”0/1[是否启用喧哗报警]” />
     * <tsk_db_alm_level val=”50[喧哗报警分贝值]” />
     * <tsk_alm_aud_file val=”报警声音文件全路径” />
     * <tsk_only_power val=”0/1[是否仅控制功放电源]” />
     * <tsk_force_signal val=”0/1[是否同步控制强切信号]” />
     * <tsk_play_volumn val=”任务执行时输出音量大小” />
     * <tsk_proj_guid val=""/>
     * <tsk_freeze val=”0/1[是否冻结任务]” />
     * <tsk_offline val="0/1[是否脱机任务]"/>
     * <tsk_trans_proto val=”1[tcp]2[udp]3[udp组播]4[rtp]5[rtp组播]” />
     * <tsk_strm_mode val="0"/>
     * <tsk_md_tp val="0"/>
     * <tsk_ch_num val="0"/>
     * <tsk_play_strategy_tp val="0"/>
     * <tsk_sync_strategy_cnt val="5"/>
     * </row>
     */
    private Status status;
    private TskGuid tsk_guid;
    private TskName tsk_name;
    private TskUsrGuid tsk_usr_guid;
    private TskSrcType tsk_src_type;
    private TskFileSec tsk_file_sec;
    private TskJobType tsk_job_type;
    private TskStartTime tsk_start_time;
    private TskHasDura tsk_has_dura;
    private TskDuration tsk_duration;
    private TskHasStopTime tsk_has_stop_time;
    private TskStopTime tsk_stop_time;
    private TskWeekData tsk_week_data;
    private TskFlagRandom tsk_flag_random;
    private TskFlagDbAlm tsk_flag_db_alm;
    private TskDbAlmLevel tsk_db_alm_level;
    private TskAlmAudFile tsk_alm_aud_file;
    private TskOnlyPower tsk_only_power;
    private TskForceSignal tsk_force_signal;
    private TskPlayVolumn tsk_play_volumn;
    private TskProjGuid tsk_proj_guid;
    private TskFreeze tsk_freeze;
    private TskOffline tsk_offline;
    private TskTransProto tsk_trans_proto;
    private TskStrmMode tsk_strm_mode;
    private TskMdTp tsk_md_tp;
    private TskChNum tsk_ch_num;
    private TskPlayStrategyTp tsk_play_strategy_tp;
    private TskSyncStrategyCnt tsk_sync_strategy_cnt;

    public TskGuid getTsk_guid() {
        return tsk_guid;
    }

    public void setTsk_guid(TskGuid tsk_guid) {
        this.tsk_guid = tsk_guid;
    }

    public TskName getTsk_name() {
        return tsk_name;
    }

    public void setTsk_name(TskName tsk_name) {
        this.tsk_name = tsk_name;
    }

    public TskUsrGuid getTsk_usr_guid() {
        return tsk_usr_guid;
    }

    public void setTsk_usr_guid(TskUsrGuid tsk_usr_guid) {
        this.tsk_usr_guid = tsk_usr_guid;
    }

    public TskSrcType getTsk_src_type() {
        return tsk_src_type;
    }

    public void setTsk_src_type(TskSrcType tsk_src_type) {
        this.tsk_src_type = tsk_src_type;
    }

    public TskFileSec getTsk_file_sec() {
        return tsk_file_sec;
    }

    public void setTsk_file_sec(TskFileSec tsk_file_sec) {
        this.tsk_file_sec = tsk_file_sec;
    }

    public TskJobType getTsk_job_type() {
        return tsk_job_type;
    }

    public void setTsk_job_type(TskJobType tsk_job_type) {
        this.tsk_job_type = tsk_job_type;
    }

    public TskStartTime getTsk_start_time() {
        return tsk_start_time;
    }

    public void setTsk_start_time(TskStartTime tsk_start_time) {
        this.tsk_start_time = tsk_start_time;
    }

    public TskHasDura getTsk_has_dura() {
        return tsk_has_dura;
    }

    public void setTsk_has_dura(TskHasDura tsk_has_dura) {
        this.tsk_has_dura = tsk_has_dura;
    }

    public TskDuration getTsk_duration() {
        return tsk_duration;
    }

    public void setTsk_duration(TskDuration tsk_duration) {
        this.tsk_duration = tsk_duration;
    }

    public TskHasStopTime getTsk_has_stop_time() {
        return tsk_has_stop_time;
    }

    public void setTsk_has_stop_time(TskHasStopTime tsk_has_stop_time) {
        this.tsk_has_stop_time = tsk_has_stop_time;
    }

    public TskStopTime getTsk_stop_time() {
        return tsk_stop_time;
    }

    public void setTsk_stop_time(TskStopTime tsk_stop_time) {
        this.tsk_stop_time = tsk_stop_time;
    }

    public TskWeekData getTsk_week_data() {
        return tsk_week_data;
    }

    public void setTsk_week_data(TskWeekData tsk_week_data) {
        this.tsk_week_data = tsk_week_data;
    }

    public TskFlagRandom getTsk_flag_random() {
        return tsk_flag_random;
    }

    public void setTsk_flag_random(TskFlagRandom tsk_flag_random) {
        this.tsk_flag_random = tsk_flag_random;
    }

    public TskFlagDbAlm getTsk_flag_db_alm() {
        return tsk_flag_db_alm;
    }

    public void setTsk_flag_db_alm(TskFlagDbAlm tsk_flag_db_alm) {
        this.tsk_flag_db_alm = tsk_flag_db_alm;
    }

    public TskDbAlmLevel getTsk_db_alm_level() {
        return tsk_db_alm_level;
    }

    public void setTsk_db_alm_level(TskDbAlmLevel tsk_db_alm_level) {
        this.tsk_db_alm_level = tsk_db_alm_level;
    }

    public TskAlmAudFile getTsk_alm_aud_file() {
        return tsk_alm_aud_file;
    }

    public void setTsk_alm_aud_file(TskAlmAudFile tsk_alm_aud_file) {
        this.tsk_alm_aud_file = tsk_alm_aud_file;
    }

    public TskOnlyPower getTsk_only_power() {
        return tsk_only_power;
    }

    public void setTsk_only_power(TskOnlyPower tsk_only_power) {
        this.tsk_only_power = tsk_only_power;
    }

    public TskForceSignal getTsk_force_signal() {
        return tsk_force_signal;
    }

    public void setTsk_force_signal(TskForceSignal tsk_force_signal) {
        this.tsk_force_signal = tsk_force_signal;
    }

    public TskPlayVolumn getTsk_play_volumn() {
        return tsk_play_volumn;
    }

    public void setTsk_play_volumn(TskPlayVolumn tsk_play_volumn) {
        this.tsk_play_volumn = tsk_play_volumn;
    }

    public TskFreeze getTsk_freeze() {
        return tsk_freeze;
    }

    public void setTsk_freeze(TskFreeze tsk_freeze) {
        this.tsk_freeze = tsk_freeze;
    }

    public TskOffline getTsk_offline() {
        return tsk_offline;
    }

    public void setTsk_offline(TskOffline tsk_offline) {
        this.tsk_offline = tsk_offline;
    }

    public TskTransProto getTsk_trans_proto() {
        return tsk_trans_proto;
    }

    public void setTsk_trans_proto(TskTransProto tsk_trans_proto) {
        this.tsk_trans_proto = tsk_trans_proto;
    }

    public TskProjGuid getTsk_proj_guid() {
        return tsk_proj_guid;
    }

    public void setTsk_proj_guid(TskProjGuid tsk_proj_guid) {
        this.tsk_proj_guid = tsk_proj_guid;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TskStrmMode getTsk_strm_mode() {
        return tsk_strm_mode;
    }

    public void setTsk_strm_mode(TskStrmMode tsk_strm_mode) {
        this.tsk_strm_mode = tsk_strm_mode;
    }

    public TskMdTp getTsk_md_tp() {
        return tsk_md_tp;
    }

    public void setTsk_md_tp(TskMdTp tsk_md_tp) {
        this.tsk_md_tp = tsk_md_tp;
    }

    public TskChNum getTsk_ch_num() {
        return tsk_ch_num;
    }

    public void setTsk_ch_num(TskChNum tsk_ch_num) {
        this.tsk_ch_num = tsk_ch_num;
    }

    public TskPlayStrategyTp getTsk_play_strategy_tp() {
        return tsk_play_strategy_tp;
    }

    public void setTsk_play_strategy_tp(TskPlayStrategyTp tsk_play_strategy_tp) {
        this.tsk_play_strategy_tp = tsk_play_strategy_tp;
    }

    public TskSyncStrategyCnt getTsk_sync_strategy_cnt() {
        return tsk_sync_strategy_cnt;
    }

    public void setTsk_sync_strategy_cnt(TskSyncStrategyCnt tsk_sync_strategy_cnt) {
        this.tsk_sync_strategy_cnt = tsk_sync_strategy_cnt;
    }

    @Override
    public String toString() {
        return "Row{" +
                "status=" + status +
                ", tsk_guid=" + tsk_guid +
                ", tsk_name=" + tsk_name +
                ", tsk_usr_guid=" + tsk_usr_guid +
                ", tsk_src_type=" + tsk_src_type +
                ", tsk_file_sec=" + tsk_file_sec +
                ", tsk_job_type=" + tsk_job_type +
                ", tsk_start_time=" + tsk_start_time +
                ", tsk_has_dura=" + tsk_has_dura +
                ", tsk_duration=" + tsk_duration +
                ", tsk_has_stop_time=" + tsk_has_stop_time +
                ", tsk_stop_time=" + tsk_stop_time +
                ", tsk_week_data=" + tsk_week_data +
                ", tsk_flag_random=" + tsk_flag_random +
                ", tsk_flag_db_alm=" + tsk_flag_db_alm +
                ", tsk_db_alm_level=" + tsk_db_alm_level +
                ", tsk_alm_aud_file=" + tsk_alm_aud_file +
                ", tsk_only_power=" + tsk_only_power +
                ", tsk_force_signal=" + tsk_force_signal +
                ", tsk_play_volumn=" + tsk_play_volumn +
                ", tsk_proj_guid=" + tsk_proj_guid +
                ", tsk_freeze=" + tsk_freeze +
                ", tsk_offline=" + tsk_offline +
                ", tsk_trans_proto=" + tsk_trans_proto +
                ", tsk_strm_mode=" + tsk_strm_mode +
                ", tsk_md_tp=" + tsk_md_tp +
                ", tsk_ch_num=" + tsk_ch_num +
                ", tsk_play_strategy_tp=" + tsk_play_strategy_tp +
                ", tsk_sync_strategy_cnt=" + tsk_sync_strategy_cnt +
                '}';
    }

}
