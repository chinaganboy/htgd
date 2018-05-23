package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 *
 * @author Administrator
 * @date 2018/1/10
 */

@XStreamAlias("tsk_offline")
public class TskOffline {
    /**
     *  <tsk_offline val="0/1[是否脱机任务]"/>
     */
    @XStreamAsAttribute
    private String val;

    public TskOffline() {
    }

    public TskOffline(String val) {
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
        return "TskOffline{" +
                "val='" + val + '\'' +
                '}';
    }

}
