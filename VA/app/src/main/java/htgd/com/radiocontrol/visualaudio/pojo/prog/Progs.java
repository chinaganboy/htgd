package htgd.com.radiocontrol.visualaudio.pojo.prog;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * @author Administrator
 * @date 2018/1/10
 */
@XStreamAlias("progs")
public class Progs {

    @XStreamImplicit(itemFieldName = "prog")
    private List<Prog> prog;

    public List<Prog> getProg() {
        return prog;
    }

    public void setProg(List<Prog> prog) {
        this.prog = prog;
    }

    @Override
    public String toString() {
        return "Progs{" +
                "prog=" + prog +
                '}';
    }

}
