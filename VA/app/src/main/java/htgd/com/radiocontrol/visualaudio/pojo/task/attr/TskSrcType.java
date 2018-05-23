package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_src_type")
public class TskSrcType {
    /**
     * <tsk_src_type val=” 0[空源] 1[文件]2[指定声卡]3[指定IP终端或用户]” />
     */
    @XStreamAsAttribute
    private String val;

    public TskSrcType() {
    }

    public TskSrcType(String val) {
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
        return "TskSrcType{" +
                "val='" + val + '\'' +
                '}';
    }

}
