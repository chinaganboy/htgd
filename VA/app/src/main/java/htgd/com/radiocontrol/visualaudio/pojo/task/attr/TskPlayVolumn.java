package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_play_volumn")
public class TskPlayVolumn {
    /**
     * <tsk_play_volumn val=”任务执行时输出音量大小” />
     */
    @XStreamAsAttribute
    private String val;

    public TskPlayVolumn() {
    }

    public TskPlayVolumn(String val) {
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
        return "TskPlayVolumn{" +
                "val='" + val + '\'' +
                '}';
    }

}
