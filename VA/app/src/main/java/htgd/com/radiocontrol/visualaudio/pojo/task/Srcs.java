package htgd.com.radiocontrol.visualaudio.pojo.task;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("srcs")
public class Srcs {

    @XStreamImplicit(itemFieldName = "src_info")
    private List<SrcInfo> src_info;

    public List<SrcInfo> getSrc_info() {
        return src_info;
    }

    public void setSrc_info(List<SrcInfo> src_info) {
        this.src_info = src_info;
    }

    @Override
    public String toString() {
        return "Srcs{" +
                "src_info=" + src_info +
                '}';
    }

}
