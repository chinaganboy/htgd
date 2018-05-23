package htgd.com.radiocontrol.visualaudio.pojo;

/**
 * 任务日期时间属性选择
 *
 * @author Administrator
 * @date 2018/1/9
 */

public class DateTimeProperty {

    private String mTaskType;
    private String mStartDateTime;
    private boolean mDurationSelect;
    private String mDuration;
    private boolean mEndDateSelect;
    private String mEndDateTime;
    private String mWeekSelect;

    public String getTaskType() {
        return mTaskType;
    }

    public void setTaskType(String taskType) {
        mTaskType = taskType;
    }

    public String getStartDateTime() {
        return mStartDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        mStartDateTime = startDateTime;
    }

    public boolean isDurationSelect() {
        return mDurationSelect;
    }

    public void setDurationSelect(boolean durationSelect) {
        mDurationSelect = durationSelect;
    }

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String duration) {
        mDuration = duration;
    }

    public boolean isEndDateSelect() {
        return mEndDateSelect;
    }

    public void setEndDateSelect(boolean endDateSelect) {
        mEndDateSelect = endDateSelect;
    }

    public String getEndDateTime() {
        return mEndDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        mEndDateTime = endDateTime;
    }

    public String getWeekSelect() {
        return mWeekSelect;
    }

    public void setWeekSelect(String weekSelect) {
        mWeekSelect = weekSelect;
    }

    @Override
    public String toString() {
        return "DateTimeProperty{" +
                "mTaskType='" + mTaskType + '\'' +
                ", mStartDateTime='" + mStartDateTime + '\'' +
                ", mDurationSelect=" + mDurationSelect +
                ", mDuration='" + mDuration + '\'' +
                ", mEndDateSelect=" + mEndDateSelect +
                ", mEndDateTime='" + mEndDateTime + '\'' +
                ", mWeekSelect='" + mWeekSelect + '\'' +
                '}';
    }

}
