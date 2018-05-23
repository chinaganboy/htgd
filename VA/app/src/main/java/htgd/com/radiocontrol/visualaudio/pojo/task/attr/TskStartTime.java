package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_start_time")
public class TskStartTime {
    /**
     * <tsk_start_time val=”2017-07-31 14:00:00[任务开始时间]” />
     */
    @XStreamAsAttribute
    private String val;

    public TskStartTime() {
    }

    public TskStartTime(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "TskStartTime{" +
                "val='" + val + '\'' +
                '}';
    }

}
