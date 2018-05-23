package htgd.com.radiocontrol.visualaudio.pojo.prog;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.io.Serializable;

/**
 * @author Administrator
 * @date 2018/1/10
 */

@XStreamAlias("prog")
public class Prog implements Serializable {
    /**
     * <prog path="F:\CloudMusic" name="32000.mp3" ftm="00:04:51"/>
     */
    @XStreamAsAttribute
    private String path;
    @XStreamAsAttribute
    private String name;
    @XStreamAsAttribute
    private String ftm;
    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public boolean isChoose;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFtm() {
        return ftm;
    }

    public void setFtm(String ftm) {
        this.ftm = ftm;
    }

    @Override
    public String toString() {
        return "Prog{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", ftm='" + ftm + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prog prog = (Prog) o;

        if (!path.equals(prog.path)) return false;
        if (!name.equals(prog.name)) return false;
        return ftm.equals(prog.ftm);
    }

    @Override
    public int hashCode() {
        int result = path.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + ftm.hashCode();
        return result;
    }

}
