package htgd.com.radiocontrol.visualaudio.pojo.usr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.io.Serializable;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("usr")
public class Usr  implements Serializable {
    /**
     * <usr name="5" nick_name="5" id="{0365C690-7CE8-425F-8C3D-74F76D7B7790}" pid="{-1}"
     * usr_priority="16" status="0" has_vid="0" rtsp="" tp="1"/>
     */
    @XStreamAsAttribute
    private String name;
    @XStreamAsAttribute
    private String nick_name;
    @XStreamAsAttribute
    private String id;
    @XStreamAsAttribute
    private String pid;
    @XStreamAsAttribute
    private String usr_priority;
    @XStreamAsAttribute
    private String status;
    @XStreamAsAttribute
    private String has_vid;
    @XStreamAsAttribute
    private String rtsp;
    @XStreamAsAttribute
    private String tp;

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    private boolean isChoose;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUsr_priority() {
        return usr_priority;
    }

    public void setUsr_priority(String usr_priority) {
        this.usr_priority = usr_priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHas_vid() {
        return has_vid;
    }

    public void setHas_vid(String has_vid) {
        this.has_vid = has_vid;
    }

    public String getRtsp() {
        return rtsp;
    }

    public void setRtsp(String rtsp) {
        this.rtsp = rtsp;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    @Override
    public String toString() {
        return "Usr{" +
                "name='" + name + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", usr_priority='" + usr_priority + '\'' +
                ", status='" + status + '\'' +
                ", has_vid='" + has_vid + '\'' +
                ", rtsp='" + rtsp + '\'' +
                ", tp='" + tp + '}';
    }

}
