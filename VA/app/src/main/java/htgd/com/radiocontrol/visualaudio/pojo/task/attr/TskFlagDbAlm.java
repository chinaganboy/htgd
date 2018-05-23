package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_flag_db_alm")
public class TskFlagDbAlm {
    /**
     * <tsk_flag_db_alm val=”0/1[是否启用喧哗报警]” />
     */
    @XStreamAsAttribute
    private String val;

    public TskFlagDbAlm() {
    }

    public TskFlagDbAlm(String val) {
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
        return "TskFlagDbAlm{" +
                "val='" + val + '\'' +
                '}';
    }

}
