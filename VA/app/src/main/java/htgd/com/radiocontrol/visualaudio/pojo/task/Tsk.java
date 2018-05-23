package htgd.com.radiocontrol.visualaudio.pojo.task;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author Administrator
 * @date 2017/11/24
 */

@XStreamAlias("tsk")
public class Tsk {
    /**
     * <tsk>
     * <row>
     * </row>
     * <srcs>
     * </srcs>
     * <dsts>
     * </dsts>
     * </tsk>
     */
    private Row row;
    private Srcs srcs;
    private Dsts dsts;

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public Srcs getSrcs() {
        return srcs;
    }

    public void setSrcs(Srcs srcs) {
        this.srcs = srcs;
    }

    public Dsts getDsts() {
        return dsts;
    }

    public void setDsts(Dsts dsts) {
        this.dsts = dsts;
    }

    @Override
    public String toString() {
        return "Tsk{" +
                "row=" + row +
                ", srcs=" + srcs +
                ", dsts=" + dsts +
                '}';
    }

}

