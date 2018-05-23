package htgd.com.radiocontrol.visualaudio.pojo.tsk;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 对讲任务状态
 *
 * @author Administrator
 * @date 2017/12/12
 */
@XStreamAlias("root")
public class TskRoot {
    /**
     * <root ord="tk_tsk_status" sess="0" ret="ok"
     * tsk_guid="{CFD85FDC-E1A8-4A13-9613-47D7F278723E}"
     * callee_guid="{DB7D618E-70DA-4FD7-9D0A-CA5BB7D61F43}" ch="0" strm="0" proto="1" tp="-1"
     * wnd="-1" status="2"/>
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
    private String callee_guid;
    @XStreamAsAttribute
    private String ch;
    @XStreamAsAttribute
    private String strm;
    @XStreamAsAttribute
    private String proto;
    @XStreamAsAttribute
    private String tp;
    @XStreamAsAttribute
    private String wnd;
    @XStreamAsAttribute
    private String status;

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

    public String getCallee_guid() {
        return callee_guid;
    }

    public void setCallee_guid(String callee_guid) {
        this.callee_guid = callee_guid;
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

    public String getProto() {
        return proto;
    }

    public void setProto(String proto) {
        this.proto = proto;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getWnd() {
        return wnd;
    }

    public void setWnd(String wnd) {
        this.wnd = wnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StartRoot{" +
                "ord='" + ord + '\'' +
                ", sess='" + sess + '\'' +
                ", ret='" + ret + '\'' +
                ", tsk_guid='" + tsk_guid + '\'' +
                ", callee_guid='" + callee_guid + '\'' +
                ", ch='" + ch + '\'' +
                ", strm='" + strm + '\'' +
                ", proto='" + proto + '\'' +
                ", tp='" + tp + '\'' +
                ", wnd='" + wnd + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}
