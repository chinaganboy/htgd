package htgd.com.radiocontrol.visualaudio.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtils {


    private static int year;
    private static int month;
    private static int day;


    //获取日期时间
    public static String getDate() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDateFormat.format(new java.util.Date());
        return date.toString();
    }

    //获取时间
    public static String getDateTime() {
        Calendar c = Calendar.getInstance();
        String hour = String.valueOf(c.get(Calendar.HOUR));
        String mins = String.valueOf(c.get(Calendar.MINUTE));
        String seconds = String.valueOf(c.get(Calendar.SECOND));
        StringBuffer sbBuffer = new StringBuffer();
        if (hour.length() != 2) {
            hour = 0 + hour;
        }
        if (mins.length() != 2) {
            mins = 0 + mins;
        }
        if (seconds.length() != 2) {
            seconds = 0 + seconds;
        }
        sbBuffer.append(hour + ":" +
                mins + ":" + seconds);
        return sbBuffer.toString();
    }

    public static String getWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);//指定年份
        calendar.set(Calendar.MONTH, month - 1);
        int daysCountOfMonth = calendar.getActualMaximum(Calendar.DATE);//获取指定年份中指定月份有几天

//获取指定年份月份中指定某天是星期几
        calendar.set(Calendar.DAY_OF_MONTH, day);  //指定日
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        StringBuffer sbBuffer = new StringBuffer();
        switch (dayOfWeek) {
            case 1:
                sbBuffer.append("星期日");
                break;
            case 2:
                sbBuffer.append("星期一");
                break;
            case 3:
                sbBuffer.append("星期二");
                break;
            case 4:
                sbBuffer.append("星期三");
                break;
            case 5:
                sbBuffer.append("星期四");
                break;
            case 6:
                sbBuffer.append("星期五");
                break;
            case 7:
                sbBuffer.append("星期六");
                break;
        }

        return sbBuffer.toString();
    }
}
