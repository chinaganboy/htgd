package htgd.com.radiocontrol.visualaudio.pojo.usr;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("usrs")
public class Usrs {

    @XStreamImplicit(itemFieldName = "usr")
    private List<Usr> usr;

    public List<Usr> getUsr() {
        return usr;
    }

    public void setUsr(List<Usr> usr) {
        this.usr = usr;
    }

    @Override
    public String toString() {
        return "Usrs{" +
                "usr=" + usr +
                '}';
    }

}
