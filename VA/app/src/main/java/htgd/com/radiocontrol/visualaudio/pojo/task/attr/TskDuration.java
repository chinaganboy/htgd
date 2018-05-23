package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_duration")
public class TskDuration {
    /**
     * <tsk_duration val=”30[指定持续时间总秒数]” />
     */
    @XStreamAsAttribute
    private String val;

    public TskDuration() {
    }

    public TskDuration(String val) {
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
        return "TskDuration{" +
                "val='" + val + '\'' +
                '}';
    }

}
