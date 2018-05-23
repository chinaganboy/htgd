package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_week_data")
public class TskWeekData {
    /**
     * <tsk_week_data val=” 0110011 [从周日到周六,1表示生效，0表示失效]” />
     */
    @XStreamAsAttribute
    private String val;

    public TskWeekData() {
    }

    public TskWeekData(String val) {
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
        return "TskWeekData{" +
                "val='" + val + '\'' +
                '}';
    }

}
