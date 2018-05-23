package htgd.com.radiocontrol.visualaudio.pojo.area;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("area")
public class Area {
    /**
     * <area name="未分区" id="{00000000-0000-0000-0000-000000000000}"/>
     */
    @XStreamAsAttribute
    private String name;
    @XStreamAsAttribute
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Area{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

}
