package htgd.com.radiocontrol.visualaudio.pojo.aud;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 更新音频传输（播放）参数
 *
 * @author Administrator
 * @date 2017/12/22
 */

@XStreamAlias("root")
public class UpdateRoot {

    /**
     * <root ord="update_aud_play" sess="0" ret="ok"  aud_enc="mp3" vol="25" rate="32000"
     * io_idx="3" stp="mp3broad" proto="tcp" multicast_ip=""
     * tsk_guid="{7CD45590-5528-48A6-9E7A-BD4514FD9B29}" sync_flag="0" sync_cnt="10"/>
     */
    @XStreamAsAttribute
    private String ord;
    @XStreamAsAttribute
    private String sess;
    @XStreamAsAttribute
    private String ret;
    @XStreamAsAttribute
    private String aud_enc;
    @XStreamAsAttribute
    private String vol;
    @XStreamAsAttribute
    private String rate;
    @XStreamAsAttribute
    private String io_idx;
    @XStreamAsAttribute
    private String stp;
    @XStreamAsAttribute
    private String proto;
    @XStreamAsAttribute
    private String multicast_ip;
    @XStreamAsAttribute
    private String tsk_guid;
    @XStreamAsAttribute
    private String sync_flag;
    @XStreamAsAttribute
    private String sync_cnt;

    public String getOrd() {
        return ord;
    }

    public void setOrd(String ord) {
        this.ord = ord;
    }

    public String getSess() {
        return sess;
    }

    public void setSess(String sess) {
        this.sess = sess;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getAud_enc() {
        return aud_enc;
    }

    public void setAud_enc(String aud_enc) {
        this.aud_enc = aud_enc;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getIo_idx() {
        return io_idx;
    }

    public void setIo_idx(String io_idx) {
        this.io_idx = io_idx;
    }

    public String getStp() {
        return stp;
    }

    public void setStp(String stp) {
        this.stp = stp;
    }

    public String getProto() {
        return proto;
    }

    public void setProto(String proto) {
        this.proto = proto;
    }

    public String getMulticast_ip() {
        return multicast_ip;
    }

    public void setMulticast_ip(String multicast_ip) {
        this.multicast_ip = multicast_ip;
    }

    public String getTsk_guid() {
        return tsk_guid;
    }

    public void setTsk_guid(String tsk_guid) {
        this.tsk_guid = tsk_guid;
    }

    public String getSync_flag() {
        return sync_flag;
    }

    public void setSync_flag(String sync_flag) {
        this.sync_flag = sync_flag;
    }

    public String getSync_cnt() {
        return sync_cnt;
    }

    public void setSync_cnt(String sync_cnt) {
        this.sync_cnt = sync_cnt;
    }

    @Override
    public String toString() {
        return "UpdateRoot{" +
                "ord='" + ord + '\'' +
                ", sess='" + sess + '\'' +
                ", ret='" + ret + '\'' +
                ", aud_enc='" + aud_enc + '\'' +
                ", vol='" + vol + '\'' +
                ", rate='" + rate + '\'' +
                ", io_idx='" + io_idx + '\'' +
                ", stp='" + stp + '\'' +
                ", proto='" + proto + '\'' +
                ", multicast_ip='" + multicast_ip + '\'' +
                ", tsk_guid='" + tsk_guid + '\'' +
                ", sync_flag='" + sync_flag + '\'' +
                ", sync_cnt='" + sync_cnt + '\'' +
                '}';
    }

}
