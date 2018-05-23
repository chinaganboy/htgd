package htgd.com.radiocontrol.visualaudio.pojo.task;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 添加/编辑任务
 *
 * @author Administrator
 * @date 2018/1/10
 */
@XStreamAlias("root")
public class TaskRoot {
    /**
     * <root ord=”task_add” sess=”1”>
     * <tsk>
     * </tsk>
     * </root>
     */
    @XStreamAsAttribute
    private String ord;
    @XStreamAsAttribute
    private String sess;
    private Tsk tsk;

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

    public Tsk getTsk() {
        return tsk;
    }

    public void setTsk(Tsk tsk) {
        this.tsk = tsk;
    }

    @Override
    public String toString() {
        return "TaskRoot{" +
                "ord='" + ord + '\'' +
                ", sess='" + sess + '\'' +
                ", tsk=" + tsk +
                '}';
    }

}
