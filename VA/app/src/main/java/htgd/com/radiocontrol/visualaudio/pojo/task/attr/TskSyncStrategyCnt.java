package htgd.com.radiocontrol.visualaudio.pojo.task.attr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Created by Administrator on 2018/3/21.
 */
@XStreamAlias("tsk_sync_strategy_cnt")
public class TskSyncStrategyCnt {
    /**
     * <tsk_sync_strategy_cnt val="5"/>
     */
    @XStreamAsAttribute
    private String val;

    public TskSyncStrategyCnt() {
    }

    public TskSyncStrategyCnt(String val) {
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
        return "TskSyncStrategyCnt{" +
                "val='" + val + '\'' +
                '}';
    }

}
