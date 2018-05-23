package htgd.com.radiocontrol.visualaudio.pojo.task;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("dsts")
public class Dsts {

    @XStreamImplicit(itemFieldName = "usr_guid")
    private List<UsrGuid> usr_guid;

    public List<UsrGuid> getUsr_guid() {
        return usr_guid;
    }

    public void setUsr_guid(List<UsrGuid> usr_guid) {
        this.usr_guid = usr_guid;
    }

    @Override
    public String toString() {
        return "Dsts{" +
                "usr_guid=" + usr_guid +
                '}';
    }

}
