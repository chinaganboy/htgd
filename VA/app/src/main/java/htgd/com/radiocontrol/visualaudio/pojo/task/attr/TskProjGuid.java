package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2018/1/11
 */
@XStreamAlias("tsk_proj_guid")
public class TskProjGuid {
    /**
     * <tsk_proj_guid val=""/>
     */
    @XStreamAsAttribute
    private String val;

    public TskProjGuid() {
    }

    public TskProjGuid(String val) {
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
        return "TskProjGuid{" +
                "val='" + val + '\'' +
                '}';
    }

}
