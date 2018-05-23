package htgd.com.radiocontrol.visualaudio.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 应用内事件
 *
 * @author Administrator
 * @date 2018/4/9
 */

public class AppEvent implements Parcelable {

    /**
     * 会话ID
     */
    private int mSessId;
    /**
     * 类型
     */
    private String mType;
    /**
     * 格式
     */
    private String mKey;
    /**
     * 数据
     */
    private String mValue;

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public int getSessId() {
        return mSessId;
    }

    public void setSessId(int sessId) {
        mSessId = sessId;
    }

    @Override
    public String toString() {
        return "AppEvent{" +
                "mSessId=" + mSessId +
                ", mType='" + mType + '\'' +
                ", mKey='" + mKey + '\'' +
                ", mValue='" + mValue + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mSessId);
        dest.writeString(this.mType);
        dest.writeString(this.mKey);
        dest.writeString(this.mValue);
    }

    public AppEvent() {
    }

    public AppEvent(int sessId, String type, String key, String value) {
        mSessId = sessId;
        mType = type;
        mKey = key;
        mValue = value;
    }

    protected AppEvent(Parcel in) {
        this.mSessId = in.readInt();
        this.mType = in.readString();
        this.mKey = in.readString();
        this.mValue = in.readString();
    }

    public static final Parcelable.Creator<AppEvent> CREATOR = new Parcelable.Creator<AppEvent>() {
        @Override
        public AppEvent createFromParcel(Parcel source) {
            return new AppEvent(source);
        }

        @Override
        public AppEvent[] newArray(int size) {
            return new AppEvent[size];
        }
    };

}
