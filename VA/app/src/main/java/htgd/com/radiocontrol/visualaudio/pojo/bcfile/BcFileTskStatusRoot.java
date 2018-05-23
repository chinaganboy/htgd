package htgd.com.radiocontrol.visualaudio.pojo.bcfile;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 广播文件任务状态更新
 *
 * @author Administrator
 * @date 2018/1/12
 */
@XStreamAlias("root")
public class BcFileTskStatusRoot {
    /**
     * <root ord="bc_file_tsk_status" sess="0" ret="ok"
     * svr_tm="2018-01-12 10:05:40" tsk_guid="{9536BC8B-A3DC-488E-A07D-BA7B0E802C9F}" path=""
     * status="0"/>
     */
    @XStreamAsAttribute
    private String sess;
    @XStreamAsAttribute
    private String ret;
    @XStreamAsAttribute
    private String svr_tm;
    @XStreamAsAttribute
    private String tsk_guid;
    @XStreamAsAttribute
    private String path;
    @XStreamAsAttribute
    private String status;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BcFileTskStatusRoot{" +
                "sess='" + sess + '\'' +
                ", ret='" + ret + '\'' +
                ", svr_tm='" + svr_tm + '\'' +
                ", tsk_guid='" + tsk_guid + '\'' +
                ", path='" + path + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}
