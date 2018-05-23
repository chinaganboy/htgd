package htgd.com.radiocontrol.visualaudio.pojo.call;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 对讲呼叫请求
 *
 * @author Administrator
 * @date 2017/12/12
 */
@XStreamAlias("root")
public class CallRoot {
    /**
     * <root ord="usr_call_req" sess="0" ret="ok"
     * tsk_guid="{7FD3CBAA-6E9A-40A0-A9D8-EC9A0165F29F}"
     * caller_guid="{8660BE12-504B-45F7-9C9F-E8B5AD7C846B}" caller_name="3" ch="0" strm="0"
     * tp="av" proto="tcp"/>
     */
    @XStreamAsAttribute
    private String ord;
    @XStreamAsAttribute
    private String sess;
    @XStreamAsAttribute
    private String ret;
    @XStreamAsAttribute
    private String tsk_guid;
    @XStreamAsAttribute
    private String caller_guid;
    @XStreamAsAttribute
    private String caller_name;
    @XStreamAsAttribute
    private String ch;
    @XStreamAsAttribute
    private String strm;
    @XStreamAsAttribute
    private String tp;
    @XStreamAsAttribute
    private String proto;

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

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getProto() {
        return proto;
    }

    public void setProto(String proto) {
        this.proto = proto;
    }

    @Override
    public String toString() {
        return "CallRoot{" +
                "ord='" + ord + '\'' +
                ", sess='" + sess + '\'' +
                ", ret='" + ret + '\'' +
                ", tsk_guid='" + tsk_guid + '\'' +
                ", caller_guid='" + caller_guid + '\'' +
                ", caller_name='" + caller_name + '\'' +
                ", ch='" + ch + '\'' +
                ", strm='" + strm + '\'' +
                ", tp='" + tp + '\'' +
                ", proto='" + proto + '\'' +
                '}';
    }

}
