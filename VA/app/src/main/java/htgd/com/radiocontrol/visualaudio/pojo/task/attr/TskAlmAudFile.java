package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_alm_aud_file")
public class TskAlmAudFile {
    /**
     * <tsk_alm_aud_file val=”报警声音文件全路径” />
     */
    @XStreamAsAttribute
    private String val;

    public TskAlmAudFile() {
    }

    public TskAlmAudFile(String val) {
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
        return "TskAlmAudFile{" +
                "val='" + val + '\'' +
                '}';
    }

}
