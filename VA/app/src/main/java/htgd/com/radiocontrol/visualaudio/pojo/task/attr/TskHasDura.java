package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_has_dura")
public class TskHasDura {
    /**
     * <tsk_has_dura val=”0/1[是否指定持续时间]” />
     */
    @XStreamAsAttribute
    private String val;

    public TskHasDura() {
    }

    public TskHasDura(String val) {
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
        return "TskHasDura{" +
                "val='" + val + '\'' +
                '}';
    }

}
