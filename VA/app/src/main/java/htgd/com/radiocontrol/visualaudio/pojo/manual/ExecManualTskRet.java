package htgd.com.radiocontrol.visualaudio.pojo.manual;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 手动执行任务
 *
 * @author Administrator
 * @date 2018/4/3
 */
@XStreamAlias("root")
public class ExecManualTskRet {
    /**
     * <root ord="exec_manual_tsk_ret" sess="0" ret="ok"
     * tsk_guid="{B7C714F4-5685-4484-87C1-331D468106A4}" tsk_src_type="1"/>
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
    private String tsk_src_type;

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

    public String getTsk_src_type() {
        return tsk_src_type;
    }

    public void setTsk_src_type(String tsk_src_type) {
        this.tsk_src_type = tsk_src_type;
    }

    @Override
    public String toString() {
        return "ExecManualTskRet{" +
                "ord='" + ord + '\'' +
                ", sess='" + sess + '\'' +
                ", ret='" + ret + '\'' +
                ", tsk_guid='" + tsk_guid + '\'' +
                ", tsk_src_type='" + tsk_src_type + '\'' +
                '}';
    }

}
