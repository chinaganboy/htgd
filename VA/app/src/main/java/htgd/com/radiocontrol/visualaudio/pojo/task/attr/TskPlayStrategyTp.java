package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Created by Administrator on 2018/3/21.
 */
@XStreamAlias("tsk_play_strategy_tp")
public class TskPlayStrategyTp {
    /**
     * <tsk_play_strategy_tp val="0"/>
     */
    @XStreamAsAttribute
    private String val;

    public TskPlayStrategyTp() {
    }

    public TskPlayStrategyTp(String val) {
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
        return "TskPlayStrategyTp{" +
                "val='" + val + '\'' +
                '}';
    }

}
