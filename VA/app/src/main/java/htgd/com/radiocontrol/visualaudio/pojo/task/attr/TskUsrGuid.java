package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_usr_guid")
public class TskUsrGuid {
    /**
     * <tsk_usr_guid val=” {B7BEE137-901C-46C2-A7F8-DBA588A04774}” />
     */
    @XStreamAsAttribute
    private String val;

    public TskUsrGuid() {
    }

    public TskUsrGuid(String val) {
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
        return "TskUsrGuid{" +
                "val='" + val + '\'' +
                '}';
    }

}
