package htgd.com.radiocontrol.visualaudio.utils;

import android.text.TextUtils;
import android.util.Log;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import htgd.com.radiocontrol.visualaudio.pojo.DateTimeProperty;

/**
 * 日期时间工具类
 *
 * @author Administrator
 * @date 2018/1/9
 */

public class DateUtils {

    private static final String TAG = DateUtils.class.getSimpleName();

    public static final String DATE_TIME_MILLIS_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 按指定形式格式化日期时间实例
     *
     * @param date   日期时间实例
     * @param format 指定的形式
     * @return 字符串形式的结果
     */
    public static String parseDate2Str(Date date, String format) {
        if (date != null && !TextUtils.isEmpty(format)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.format(date);
        }
        return "";
    }

    /**
     * 按指定形式格式化字符串
     *
     * @param str    待格式化字符串
     * @param format 指定形式
     * @return 日期时间实例
     */
    public static Date parseStr2Date(String str, String format) {
        if (!TextUtils.isEmpty(format) && !TextUtils.isEmpty(format)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            try {
                return simpleDateFormat.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
                return new Date();
            }
        }
        return new Date();
    }

    /**
     * 格式化指定字符串为秒数值
     *
     * @param duration 字符串，形如“00：00:30”
     * @return 字符串值，形式“30”
     */
    public static String parseStr2Second(String duration) {
        if (!TextUtils.isEmpty(duration)) {
            String[] tmp = duration.split(":");
            if (tmp.length == 3) {
                try {
                    long hours = Long.parseLong(tmp[0]) * 60L * 60L;
                    long minutes = Long.parseLong(tmp[1]) * 60L;
                    long seconds = Long.parseLong(tmp[2]);
                    Log.d(TAG, "duration: " + duration + ", result: " + String.valueOf((int) (hours
                            + minutes + seconds)));
                    return String.valueOf((int) (hours + minutes + seconds));
                } catch (Exception e) {
                    e.printStackTrace();
                    return "0";
                }
            }
            return "0";
        }
        return "0";
    }

    /**
     * 格式化指定毫秒值字符串为字符串（默认值为00:00:30）
     *
     * @param secondStr 毫秒值字符串
     * @return 字符串结果，形式“00:00:30”
     */
    public static String parseSecond2Str(String secondStr) {
        if (!TextUtils.isEmpty(secondStr)) {
            try {
                long seconds = Long.parseLong(secondStr);
                if (seconds > 0L) {
                    String hour = String.valueOf((int) (seconds / 60L / 60L));
                    if (hour.length() == 1) {
                        hour = "0" + hour;
                    }
                    String minute = String.valueOf((int) (seconds % 3600L / 60L));
                    if (minute.length() == 1) {
                        minute = "0" + minute;
                    }
                    String second = String.valueOf((int) (seconds % 60L));
                    if (second.length() == 1) {
                        second = "0" + second;
                    }
                    Log.d(TAG, "seconds: " + secondStr + ", result: [" + hour + ":" + minute + ":" +
                            second + "]");
                    return hour + ":" + minute + ":" + second;
                }
                return "00:00:30";
            } catch (Exception e) {
                e.printStackTrace();
                return "00:00:30";
            }
        }
        return "00:00:30";
    }

    /**
     * 格式化任务定时属性显示
     *
     * @param dateTimeProperty 定时设置实例
     * @param isShowType       是否显示任务类型
     * @return 字符串结果
     */
    public static String parseDateTimeProperty2Str(DateTimeProperty dateTimeProperty, boolean
            isShowType) {
        if (dateTimeProperty != null) {
            StringBuilder sb = new StringBuilder();
            String[] startDateTime = dateTimeProperty.getStartDateTime().split(" ");
            sb.append(startDateTime[0]);
            if (!dateTimeProperty.isEndDateSelect()) {
                sb.append("起");
            } else {
                sb.append("至").append(dateTimeProperty.getEndDateTime
                        ().split(" ")[0]);
            }
            sb.append(", ");
            if (isShowType) {
                switch (dateTimeProperty.getTaskType()) {
                    case "0":
                        sb.append("每天").append(", ");
                        break;
                    case "1":
                        sb.append("一次").append(", ");
                        break;
                    case "7":
                        sb.append(parseWeeksStr2Format(dateTimeProperty.getWeekSelect())).append
                                (", ");
                        break;
                    default:
                        break;
                }
            }
            sb.append(parseTime2Format(startDateTime[1], true)).append("执行");
            if (dateTimeProperty.isDurationSelect()) {
                sb.append(", ").append("持续");
                sb.append(parseTime2Format(parseSecond2Str(dateTimeProperty.getDuration()), false));
            }
            sb.append(".");
            Log.d(TAG, "result: " + sb.toString());
            return sb.toString();
        }
        return "";
    }

    /**
     * 格式化形式“0000000”的字符串为“每周日一二三四五六”
     *
     * @param weeksStr 形如“0000000”的字符串
     * @return 形如“每周日一二三四五六”的字符串
     */
    public static String parseWeeksStr2Format(String weeksStr) {
        if (!TextUtils.isEmpty(weeksStr) && weeksStr.length() == 7) {
            StringBuilder sb = new StringBuilder();
            sb.append("每周");
            byte[] weeks = weeksStr.getBytes();
            for (int i = 0; i < weeks.length; i++) {
                if (weeks[i] == '1') {
                    switch (i) {
                        case 0:
                            sb.append("日");
                            break;
                        case 1:
                            sb.append("一");
                            break;
                        case 2:
                            sb.append("二");
                            break;
                        case 3:
                            sb.append("三");
                            break;
                        case 4:
                            sb.append("四");
                            break;
                        case 5:
                            sb.append("五");
                            break;
                        case 6:
                            sb.append("六");
                            break;
                        default:
                            break;
                    }
                }
            }
            return sb.toString();
        }
        return "";
    }

    /**
     * 格式化时间显示：“00:00:30”为“30秒”
     *
     * @param time          形式“00:00:30”的字符串
     * @param isEmptyEnable 是否需要空值
     * @return 形式“3秒”、“30秒”或“00时00分30秒”的结果字符串
     */
    public static String parseTime2Format(String time, boolean isEmptyEnable) {
        if (!TextUtils.isEmpty(time)) {
            String[] times = time.split(":");
            if (times.length == 3) {
                StringBuilder sb = new StringBuilder();
                if (isEmptyEnable) {
                    sb.append(times[0]).append("时").append(times[1]).append("分").append(times[2])
                            .append("秒");
                } else {
                    if (!"00".equalsIgnoreCase(times[0])) {
                        if (times[0].indexOf("0") == 0) {
                            times[0] = times[0].substring(1);
                        }
                        sb.append(times[0]).append("时");
                    }
                    if (!"00".equalsIgnoreCase(times[1])) {
                        if (times[1].indexOf("0") == 0) {
                            times[1] = times[1].substring(1);
                        }
                        sb.append(times[1]).append("分");
                    }
                    if (!"00".equalsIgnoreCase(times[2])) {
                        if (times[2].indexOf("0") == 0) {
                            times[2] = times[2].substring(1);
                        }
                        sb.append(times[2]).append("秒");
                    }
                }
                Log.d(TAG, "parse " + time + " to " + sb.toString());
                return sb.toString();
            }
            if (isEmptyEnable) {
                return "00时00分00秒" ;
            } else {
                return "";
            }
        }
        if (isEmptyEnable) {
            return "00时00分00秒" ;
        } else {
            return "";
        }
    }

}
