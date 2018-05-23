package htgd.com.radiocontrol.visualaudio.pojo.call;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 取消呼叫
 *
 * @author Administrator
 * @date 2018/1/26
 */
@XStreamAlias("root")
public class CancelRoot {
    /**
     * <root ord="usr_call_req_cancel" sess="0" ret="ok" svr_tm="2018-01-26 10:40:46"
     * tsk_guid="{1671525E-FDEA-46DA-B0B7-DFC6FBEBE5FA}"
     * caller_guid="{0D5DF66D-F3B7-4142-98FA-62C484425D4D}" caller_name="1"/>
     */

    @XStreamAsAttribute
    private String ord;
    @XStreamAsAttribute
    private String sess;
    @XStreamAsAttribute
    private String ret;
    @XStreamAsAttribute
    private String svr_tm;
    @XStreamAsAttribute
    private String tsk_guid;
    @XStreamAsAttribute
    private String caller_guid;
    @XStreamAsAttribute
    private String caller_name;

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

    public String getSvr_tm() {
        return svr_tm;
    }

    public void setSvr_tm(String svr_tm) {
        this.svr_tm = svr_tm;
    }

    public String getTsk_guid() {
        return tsk_guid;
    }

    public void setTsk_guid(String tsk_guid) {
        this.tsk_guid = tsk_guid;
    }

    public String getCaller_guid() {
        return caller_guid;
    }

    public void setCaller_guid(String caller_guid) {
        this.caller_guid = caller_guid;
    }

    public String getCaller_name() {
        return caller_name;
    }

    public void setCaller_name(String caller_name) {
        this.caller_name = caller_name;
    }

    @Override
    public String toString() {
        return "CancelRoot{" +
                "ord='" + ord + '\'' +
                ", sess='" + sess + '\'' +
                ", ret='" + ret + '\'' +
                ", svr_tm='" + svr_tm + '\'' +
                ", tsk_guid='" + tsk_guid + '\'' +
                ", caller_guid='" + caller_guid + '\'' +
                ", caller_name='" + caller_name + '\'' +
                '}';
    }

}
