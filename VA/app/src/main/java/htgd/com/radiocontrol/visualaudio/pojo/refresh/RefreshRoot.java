package htgd.com.radiocontrol.visualaudio.pojo.refresh;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import htgd.com.radiocontrol.visualaudio.pojo.prog.Progs;

/**
 * 刷新节目库数据
 *
 * @author Administrator
 * @date 2018/3/23
 */
@XStreamAlias("root")
public class RefreshRoot {
    /**
     * <root ord="prog_refresh" sess="0" ret="ok">
     * <progs>
     * <prog path="C:\Users\AVSZ\Desktop\.......... (3) (1)" name="" ftm="00:00:00"/>
     * <prog path="C:\Users\AVSZ\Desktop\.......... (3) (1)\1" name="" ftm="00:00:00"/>
     * </progs>
     * </root>
     */
    @XStreamAsAttribute
    private String ord;
    @XStreamAsAttribute
    private String sess;
    @XStreamAsAttribute
    private String ret;
    private Progs progs;

    public String getOrd() {
        return ord;
    }

    public void setOrd(String ord) {
        this.ord = ord;
    }

    public String getSess() {
        return sess;
    }

    public void setSess(String sess) {
        this.sess = sess;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public Progs getProgs() {
        return progs;
    }

    public void setProgs(Progs progs) {
        this.progs = progs;
    }

    @Override
    public String toString() {
        return "RefreshRoot{" +
                "ord='" + ord + '\'' +
                ", sess='" + sess + '\'' +
                ", ret='" + ret + '\'' +
                ", progs=" + progs +
                '}';
    }

}
