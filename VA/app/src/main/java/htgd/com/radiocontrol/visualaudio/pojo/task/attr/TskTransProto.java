package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk_trans_proto")
public class TskTransProto {
    /**
     * <tsk_trans_proto val=”1[tcp]2[udp]3[udp组播]4[rtp]5[rtp组播]” />
     */
    @XStreamAsAttribute
    private String val;

    public TskTransProto() {
    }

    public TskTransProto(String val) {
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
        return "TskTransProto{" +
                "val='" + val + '\'' +
                '}';
    }

}
