package htgd.com.radiocontrol.visualaudio.pojo.login;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import htgd.com.radiocontrol.visualaudio.pojo.area.Areas;
import htgd.com.radiocontrol.visualaudio.pojo.prog.Progs;
import htgd.com.radiocontrol.visualaudio.pojo.task.Tasks;
import htgd.com.radiocontrol.visualaudio.pojo.usr.Usrs;

/**
 * 用户登录反馈
 *
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("root")
public class LoginRoot {
    /**
     * <root ord="usr_login_ret" sess="15" ret="ok"
     * usr_guid="{E41FFB74-F14E-433C-A5C8-BC66CFAE36A1}" usr_nickname="9"  usr_priority="16"
     * time="2018-02-08 15:52:04" rtsp="" upgrade_path="" upgrade_force="">
     * <areas>
     * </areas>
     * <usrs>
     * </usrs>
     * <tasks>
     * </tasks>
     * <progs>
     * </progs>
     * </root>
     */
    @XStreamAsAttribute
    private String ord;
    @XStreamAsAttribute
    private String sess;
    @XStreamAsAttribute
    private String ret;
    @XStreamAsAttribute
    private String usr_guid;
    @XStreamAsAttribute
    private String usr_nickname;
    @XStreamAsAttribute
    private String usr_priority;
    @XStreamAsAttribute
    private String time;
    @XStreamAsAttribute
    private String rtsp;
    @XStreamAsAttribute
    private String upgrade_path;
    @XStreamAsAttribute
    private String upgrade_force;
    private Areas areas;
    private Usrs usrs;
    private Tasks tasks;
    private Progs progs;

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

    public String getUsr_guid() {
        return usr_guid;
    }

    public void setUsr_guid(String usr_guid) {
        this.usr_guid = usr_guid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsr_nickname() {
        return usr_nickname;
    }

    public void setUsr_nickname(String usr_nickname) {
        this.usr_nickname = usr_nickname;
    }

    public String getUsr_priority() {
        return usr_priority;
    }

    public void setUsr_priority(String usr_priority) {
        this.usr_priority = usr_priority;
    }

    public String getRtsp() {
        return rtsp;
    }

    public void setRtsp(String rtsp) {
        this.rtsp = rtsp;
    }

    public String getUpgrade_path() {
        return upgrade_path;
    }

    public void setUpgrade_path(String upgrade_path) {
        this.upgrade_path = upgrade_path;
    }

    public String getUpgrade_force() {
        return upgrade_force;
    }

    public void setUpgrade_force(String upgrade_force) {
        this.upgrade_force = upgrade_force;
    }

    public Areas getAreas() {
        return areas;
    }

    public void setAreas(Areas areas) {
        this.areas = areas;
    }

    public Usrs getUsrs() {
        return usrs;
    }

    public void setUsrs(Usrs usrs) {
        this.usrs = usrs;
    }

    public Tasks getTasks() {
        return tasks;
    }

    public void setTasks(Tasks tasks) {
        this.tasks = tasks;
    }

    public Progs getProgs() {
        return progs;
    }

    public void setProgs(Progs progs) {
        this.progs = progs;
    }

    @Override
    public String toString() {
        return "LoginRoot{" +
                "ord='" + ord + '\'' +
                ", sess='" + sess + '\'' +
                ", ret='" + ret + '\'' +
                ", usr_guid='" + usr_guid + '\'' +
                ", usr_nickname='" + usr_nickname + '\'' +
                ", usr_priority='" + usr_priority + '\'' +
                ", time='" + time + '\'' +
                ", rtsp='" + rtsp + '\'' +
                ", upgrade_path='" + upgrade_path + '\'' +
                ", upgrade_force='" + upgrade_force + '\'' +
                ", areas=" + areas +
                ", usrs=" + usrs +
                ", tasks=" + tasks +
                ", progs=" + progs +
                '}';
    }

}
