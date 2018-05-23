package htgd.com.radiocontrol.visualaudio.pojo.bcusr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 手动执行任务或实时采播
 *
 * @author Administrator
 * @date 2018/3/8
 */
@XStreamAlias("root")
public class BcUsrTskRoot {
    /**
     * <root ord="bc_usr_tsk_status" sess="0" ret="ok"
     * tsk_guid="{013E5469-03FB-40E0-A9E5-AF743EC80704}"
     * callee_guid="{C9B2FA1A-BF2D-4F85-9CFE-65F0DBA3059D}" proto="1" status="2"/>
     */
    @XStreamAsAttribute
    private String ord;
    @XStreamAsAttribute
    private String sess;
    @XStreamAsAttribute
    private String tsk_guid;
    @XStreamAsAttribute
    private String callee_guid;
    @XStreamAsAttribute
    private String proto;
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

    public String getProto() {
        return proto;
    }

    public void setProto(String proto) {
        this.proto = proto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BcUsrTskRoot{" +
                "ord='" + ord + '\'' +
                ", sess='" + sess + '\'' +
                ", tsk_guid='" + tsk_guid + '\'' +
                ", callee_guid='" + callee_guid + '\'' +
                ", proto='" + proto + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}
