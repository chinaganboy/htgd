package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_db_alm_level")
public class TskDbAlmLevel {
    /**
     * <tsk_db_alm_level val=”50[喧哗报警分贝值]” />
     */
    @XStreamAsAttribute
    private String val;

    public TskDbAlmLevel() {
    }

    public TskDbAlmLevel(String val) {
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
        return "TskDbAlmLevel{" +
                "val='" + val + '\'' +
                '}';
    }

}
