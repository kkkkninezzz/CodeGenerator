package ${packageName};

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * 处理跟时间相关
 */
public class DateUtil {
    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 1秒的毫秒数
     * */
    public static final long MILLISECONDS_OF_ONE_SECOND = 1000L;

    /**
     * 1分钟的毫秒数
     * */
    public static final long MILLISECONDS_OF_ONE_MINUTES = 60000L;

    /**
     * 1小时的毫秒数
     * */
    public static final long MILLISECONDS_OF_ONE_HOUR = 3600000L;

    /**
     * 1天的毫秒数
     * */
    public static final long MILLISECONDS_OF_ONE_DAY = 86400000L;

    /**
     * 将毫秒转换为日期对象
     * */


    /**
     * 将 yyyy-MM-dd HH:mm:ss 的字符串转换为Date，失败返回null
     * */
    public static Date parse(String dateStr) {
        try {
            return DateFormatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将 Date 对象转换为 yyyy-MM-dd HH:mm:ss型字符串
     * */
    public static String format(Date date) {
        return DateFormatter.format(date);
    }

    /**
     * 根据毫秒数获取Date对象
     * */
    public synchronized static Date parse(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    /**
     * 将毫秒数转换为默认的字符串格式
     * */
    public static String format(long millis) {
        return format(parse(millis));
    }

    private static class DateFormatter {
        private static ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>() {
            @Override
            protected DateFormat initialValue() {
                return new SimpleDateFormat(DATE_FORMAT_PATTERN);
            }
        };

        public static Date parse(String dateStr) throws ParseException {
            return dateFormat.get().parse(dateStr);
        }

        public static String format(Date date) {
            return dateFormat.get().format(date);
        }
    }
}
