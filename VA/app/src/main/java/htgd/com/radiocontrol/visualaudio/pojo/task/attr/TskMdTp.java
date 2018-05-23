package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2018/1/11
 */

@XStreamAlias("tsk_md_tp")
public class TskMdTp {
    /**
     * <tsk_md_tp val="0"/>
     */
    @XStreamAsAttribute
    private String val;

    public TskMdTp() {
    }

    public TskMdTp(String val) {

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
        return "TskMdTp{" +
                "val='" + val + '\'' +
                '}';
    }

}
