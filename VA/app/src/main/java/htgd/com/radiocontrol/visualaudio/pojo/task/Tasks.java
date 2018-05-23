package htgd.com.radiocontrol.visualaudio.pojo.task;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tasks")
public class Tasks {
    /**
     * <tasks>
     * <tsk/>
     * <tsk/>
     * </tasks>
     */
    @XStreamImplicit(itemFieldName = "tsk")
    private List<Tsk> tsk;

    public List<Tsk> getTsk() {
        return tsk;
    }

    public void setTsk(List<Tsk> tsk) {
        this.tsk = tsk;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "tsk=" + tsk +
                '}';
    }

}
