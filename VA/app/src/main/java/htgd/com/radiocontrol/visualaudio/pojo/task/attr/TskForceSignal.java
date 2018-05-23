package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_force_signal")
public class TskForceSignal {
    /**
     * <tsk_force_signal val=”0/1[是否同步控制强切信号]” />
     */
    @XStreamAsAttribute
    private String val;

    public TskForceSignal() {
    }

    public TskForceSignal(String val) {
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
        return "TskForceSignal{" +
                "val='" + val + '\'' +
                '}';
    }

}
