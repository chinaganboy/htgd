package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_flag_random")
public class TskFlagRandom {
    /**
     * <tsk_flag_random val=”0/1[是否随机标识]” />
     */
    @XStreamAsAttribute
    private String val;

    public TskFlagRandom() {
    }

    public TskFlagRandom(String val) {
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
        return "TskFlagRandom{" +
                "val='" + val + '\'' +
                '}';
    }

}
