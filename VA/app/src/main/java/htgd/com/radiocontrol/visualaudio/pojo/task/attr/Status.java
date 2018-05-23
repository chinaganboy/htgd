package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 *
 * @author Administrator
 * @date 2018/1/10
 */

@XStreamAlias("status")
public class Status {
    /**
     * <status val="0"/>
     */
    @XStreamAsAttribute
    private String val;

    public Status() {
    }

    public Status(String val) {
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
        return "Status{" +
                "val='" + val + '\'' +
                '}';
    }

}
