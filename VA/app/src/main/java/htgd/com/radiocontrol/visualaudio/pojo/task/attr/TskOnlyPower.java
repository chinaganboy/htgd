package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_only_power")
public class TskOnlyPower {
    /**
     * <tsk_only_power val=”0/1[是否仅控制功放电源]” />
     */
    @XStreamAsAttribute
    private String val;

    public TskOnlyPower() {
    }

    public TskOnlyPower(String val) {
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
        return "TskOnlyPower{" +
                "val='" + val + '\'' +
                '}';
    }

}
