package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_job_type")
public class TskJobType {
    /**
     * <tsk_job_type val=”0[每天任务]1[一次任务]2[手动任务]7[每周任务]” />
     */
    @XStreamAsAttribute
    private String val;

    public TskJobType() {
    }

    public TskJobType(String val) {
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
        return "TskJobType{" +
                "val='" + val + '\'' +
                '}';
    }

}
