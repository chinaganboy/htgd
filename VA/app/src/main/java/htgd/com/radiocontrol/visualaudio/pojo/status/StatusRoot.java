package htgd.com.radiocontrol.visualaudio.pojo.status;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 用户状态
 *
 * @author Administrator
 * @date 2017/12/7
 */

@XStreamAlias("root")
public class StatusRoot {
    /**
     * <root ord="usr_status" sess="0" ret="ok" name="new_dev" status="1"
     * usr_guid="{F9C85DA7-0DB0-4C08-9D32-1B4ABE4BEDE1}"/>
     */
    @XStreamAsAttribute
    private String ord;
    @XStreamAsAttribute
    private String sess;
    @XStreamAsAttribute
    private String ret;
    @XStreamAsAttribute
    private String name;
    @XStreamAsAttribute
    private String status;
    @XStreamAsAttribute
    private String usr_guid;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsr_guid() {
        return usr_guid;
    }

    public void setUsr_guid(String usr_guid) {
        this.usr_guid = usr_guid;
    }

    @Override
    public String toString() {
        return "StartRoot{" +
                "ord='" + ord + '\'' +
                ", sess='" + sess + '\'' +
                ", ret='" + ret + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", usr_guid='" + usr_guid + '\'' +
                '}';
    }

}
