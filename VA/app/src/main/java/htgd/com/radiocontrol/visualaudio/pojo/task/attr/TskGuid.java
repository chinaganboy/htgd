package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_guid")
public class TskGuid {
    /**
     * <tsk_guid val=”{151196D6-33D7-4426-9E3C-E374FE12AE51}” />
     */
    @XStreamAsAttribute
    private String val;

    public TskGuid() {
    }

    public TskGuid(String val) {
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
        return "TskGuid{" +
                "val='" + val + '\'' +
                '}';
    }

}
