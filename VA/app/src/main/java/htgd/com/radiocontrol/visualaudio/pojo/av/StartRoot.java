package htgd.com.radiocontrol.visualaudio.pojo.av;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 开启音视频
 *
 * @author Administrator
 * @date 2017/12/7
 */

@XStreamAlias("root")
public class StartRoot {
    /**
     * <root ord="start_av" sess="23" ret="ok" ch="0" strm="0" aud_enc="g711" vid_enc="h264"
     * tp="av" stp="preview" tsk_guid="{611C4A24-CD23-4051-B4B9-ADAB1CF399CA}"/>
     */
    @XStreamAsAttribute
    private String ord;
    @XStreamAsAttribute
    private String sess;
    @XStreamAsAttribute
    private String ret;
    @XStreamAsAttribute
    private String ch;
    @XStreamAsAttribute
    private String strm;
    @XStreamAsAttribute
    private String aud_enc;
    @XStreamAsAttribute
    private String vid_enc;
    @XStreamAsAttribute
    private String tp;
    @XStreamAsAttribute
    private String stp;
    @XStreamAsAttribute
    private String tsk_guid;

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

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public String getStrm() {
        return strm;
    }

    public void setStrm(String strm) {
        this.strm = strm;
    }

    public String getAud_enc() {
        return aud_enc;
    }

    public void setAud_enc(String aud_enc) {
        this.aud_enc = aud_enc;
    }

    public String getVid_enc() {
        return vid_enc;
    }

    public void setVid_enc(String vid_enc) {
        this.vid_enc = vid_enc;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getStp() {
        return stp;
    }

    public void setStp(String stp) {
        this.stp = stp;
    }

    public String getTsk_guid() {
        return tsk_guid;
    }

    public void setTsk_guid(String tsk_guid) {
        this.tsk_guid = tsk_guid;
    }

    @Override
    public String toString() {
        return "StartRoot{" +
                "ord='" + ord + '\'' +
                ", sess='" + sess + '\'' +
                ", ret='" + ret + '\'' +
                ", ch='" + ch + '\'' +
                ", strm='" + strm + '\'' +
                ", aud_enc='" + aud_enc + '\'' +
                ", vid_enc='" + vid_enc + '\'' +
                ", tp='" + tp + '\'' +
                ", stp='" + stp + '\'' +
                ", tsk_guid='" + tsk_guid + '\'' +
                '}';
    }

}
