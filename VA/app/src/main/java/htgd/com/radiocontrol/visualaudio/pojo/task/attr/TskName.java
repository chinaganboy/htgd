package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_name")
public class TskName {
    /**
     * <tsk_name val=”任务名[utf8编码]” />
     */
    @XStreamAsAttribute
    private String val;

    public TskName() {
    }

    public TskName(String val) {
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
        return "TskName{" +
                "val='" + val + '\'' +
                '}';
    }

}
