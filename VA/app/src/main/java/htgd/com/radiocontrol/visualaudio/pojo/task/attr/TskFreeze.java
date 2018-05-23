package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_freeze")
public class TskFreeze {
    /**
     * <tsk_freeze val=”0/1[是否冻结任务]” />
     */
    @XStreamAsAttribute
    private String val;

    public TskFreeze() {
    }

    public TskFreeze(String val) {
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
        return "TskFreeze{" +
                "val='" + val + '\'' +
                '}';
    }

}
