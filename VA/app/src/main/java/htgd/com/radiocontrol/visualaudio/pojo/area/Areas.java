package htgd.com.radiocontrol.visualaudio.pojo.area;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("areas")
public class Areas {

    @XStreamImplicit(itemFieldName = "area")
    private List<Area> area;

    public List<Area> getArea() {
        return area;
    }

    public void setArea(List<Area> area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Areas{" +
                "area=" + area +
                '}';
    }

}
