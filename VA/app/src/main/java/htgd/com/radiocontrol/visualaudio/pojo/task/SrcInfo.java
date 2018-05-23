package htgd.com.radiocontrol.visualaudio.pojo.task;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("src_info")
public class SrcInfo {
    /**
     * <src_info val=”根据tsk_src_type值，可能是文件全路径、声卡标识、用户ID” />
     */
    @XStreamAsAttribute
    private String val;

    public SrcInfo() {
    }

    public SrcInfo(String val) {

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
        return "SrcInfo{" +
                "val='" + val + '\'' +
                '}';
    }

}
