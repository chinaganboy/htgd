package htgd.com.radiocontrol.visualaudio.pojo.task;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("usr_guid")
public class UsrGuid {
    /**
     * <usr_guid val=”” />
     */
    @XStreamAsAttribute
    private String val;

    public UsrGuid() {
    }

    public UsrGuid(String val) {
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
        return "UsrGuid{" +
                "val='" + val + '\'' +
                '}';
    }

}
