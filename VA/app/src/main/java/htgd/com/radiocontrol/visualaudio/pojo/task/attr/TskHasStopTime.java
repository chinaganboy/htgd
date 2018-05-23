package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_has_stop_time")
public class TskHasStopTime {
    /**
     * <tsk_has_stop_time val=”0/1[是否指定任务有效截止时间]” />
     */
    @XStreamAsAttribute
    private String val;

    public TskHasStopTime() {
    }

    public TskHasStopTime(String val) {
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
        return "TskHasStopTime{" +
                "val='" + val + '\'' +
                '}';
    }

}
