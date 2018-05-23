package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_stop_time")
public class TskStopTime {
    /**
     * <tsk_stop_time val=”2017-08-10 16:00:00[任务有效截止时间]” />
     */
    @XStreamAsAttribute
    private String val;

    public TskStopTime() {
    }

    public TskStopTime(String val) {
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
        return "TskStopTime{" +
                "val='" + val + '\'' +
                '}';
    }

}
