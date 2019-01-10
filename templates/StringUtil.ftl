package ${packageName};

import java.util.StringTokenizer;


/**
 *
 * 处理字符串相关
 */
public class StringUtil {
    private StringUtil() {}

    public static final String EMPTY_STRING = "";

    /**
     * 判断字符串是否为null或者""
     * */
    public static boolean isNullOrEmpty(String str) {
        return str == null || EMPTY_STRING.equals(str);
    }

    /**
     * 只要有一个为null或空时就返回true
     * */
    public static boolean isNullOrEmptyForMultiStr(String... strs) {
        for(String str : strs)
            if(isNullOrEmpty(str))
                return true;

        return false;
    }

    /**
     * 获取字符串包含某个字符的次数
     * */
    public static int getCountOfCharInString(String str, char targetCh) {
        int count = 0;

        for(int i = 0; i < str.length(); i++)
            if(str.charAt(i) == targetCh)
                count++;

        return count;
    }

    /**
     * 将字符串数组转换为字符串
     * */
    public static String arrayToString(String[] strs, String delimiter) {
        if(strs == null || strs.length == 0)
            return EMPTY_STRING;

        StringBuilder sb = new StringBuilder();
        sb.append(strs[0]);

        for(int i = 1; i < strs.length; i++) {
            sb.append(delimiter);
            sb.append(strs[i]);
        }


        return sb.toString();
    }

    /**
     * 拼接字符串
     * */
    public static String concatStrs(String startStr, String... strs) {
        StringBuilder sb = new StringBuilder();

        sb.append(startStr);

        for(String str : strs)
            sb.append(str);

        return sb.toString();
    }

    /**
     * 比较yyyy-MM-dd 时间格式类型的字符串的大小
     * date1与date2都为null，返回0
     * date1 = null，返回-1
     * date2 = null，返回1
     * 最后直接比较两个字符串大小
     * */
    public static int compareDateString(String date1, String date2) {
        if(date1 == null && date2 == null)
            return 0;

        if(date1 == null)
            return -1;

        if(date2 == null)
            return 1;

        return date1.compareTo(date2);
    }

    /**
     * 使用StringTokenizer分割字符串，不包含界符
     * */
    public static String[] splitString(String str, String delim) {
        if(isNullOrEmpty(str))
            return null;

        StringTokenizer tokens = new StringTokenizer(str, delim);

        String[] rs = new String[tokens.countTokens()];

        int i = 0;
        while(tokens.hasMoreTokens())
            rs[i++] = tokens.nextToken();

        return rs;
    }

    /**
     * 获取重复某个字符串的字符串
     * 如：base : "1" count : 1 return "11"
     * 		   base : "1" count : 2 return "111"
     * */
    public static String createRepetitiveString(String base, int count) {
        StringBuilder sb = new StringBuilder(base.length() * count);

        sb.append(base);
        for(int i = 0; i < count; i++)
            sb.append(base);

        return sb.toString();
    }
}