package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_file_sec")
public class TskFileSec {
    /**
     * <tsk_file_sec val=”50[文件总时长秒数]”/>
     */
    @XStreamAsAttribute
    private String val;

    public TskFileSec() {
    }

    public TskFileSec(String val) {
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
        return "TskFileSec{" +
                "val='" + val + '\'' +
                '}';
    }

}
