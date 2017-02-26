package cn.makisekurisu.util;

import org.apache.ibatis.type.JdbcType;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Clob;
import java.sql.Ref;
import java.sql.Struct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ym on 2017/2/21 0021.
 *
 * type相关的工具类
 * 例如jdbcType和JavaType
 */
public class TypeUtil {
    private TypeUtil() {}

    private static Map<JdbcType, Class<?>> javaTypes = new HashMap<JdbcType, Class<?>>();

    /**
     * 用于匹配是否是lang包下的类
     * */
    private static Pattern isInLangPattern = Pattern.compile("^(java\\.lang\\.)\\w*");

    static {
        javaTypes.put(JdbcType.CHAR, String.class);
        javaTypes.put(JdbcType.VARCHAR, String.class);
        javaTypes.put(JdbcType.LONGNVARCHAR, String.class);
        javaTypes.put(JdbcType.LONGVARCHAR, String.class);

        javaTypes.put(JdbcType.NUMERIC, BigDecimal.class);
        javaTypes.put(JdbcType.DECIMAL, BigDecimal.class);

        javaTypes.put(JdbcType.BIT, Boolean.class);
        javaTypes.put(JdbcType.BOOLEAN, Boolean.class);

        javaTypes.put(JdbcType.TINYINT, Byte.class);

        javaTypes.put(JdbcType.SMALLINT, Short.class);

        javaTypes.put(JdbcType.INTEGER, Integer.class);

        javaTypes.put(JdbcType.BIGINT, Long.class);

        javaTypes.put(JdbcType.REAL, Float.class);

        javaTypes.put(JdbcType.FLOAT, Double.class);
        javaTypes.put(JdbcType.DOUBLE, Double.class);

        javaTypes.put(JdbcType.BINARY, byte[].class);
        javaTypes.put(JdbcType.VARBINARY, byte[].class);
        javaTypes.put(JdbcType.LONGVARBINARY, byte[].class);
        javaTypes.put(JdbcType.BLOB, byte[].class);

        javaTypes.put(JdbcType.DATE, Date.class);
        javaTypes.put(JdbcType.TIME, Date.class);
        javaTypes.put(JdbcType.TIMESTAMP, Date.class);

        javaTypes.put(JdbcType.CLOB, Clob.class);

        javaTypes.put(JdbcType.ARRAY, Array.class);

        javaTypes.put(JdbcType.DISTINCT, null);

        javaTypes.put(JdbcType.STRUCT, Struct.class);

        javaTypes.put(JdbcType.REF, Ref.class);

        javaTypes.put(JdbcType.DATALINK, URL.class);

    }

    /**
     * 获取mybatis的jdbcType对应的java类型
     * */
    public static Class<?> getJavaTypeByJdbcType(JdbcType jdbcType) {
        return javaTypes.get(jdbcType);
    }

    /**
     * 判断class时候是lang包下
     * */
    public static boolean isInLangPackage(Class<?> javaType) {
        if(javaType == null)
            throw new IllegalArgumentException("参数不能为null");

        Matcher matcher = isInLangPattern.matcher(javaType.getTypeName());
        return matcher.find();
    }

    /**
     * 判断class时候是lang包下
     * */
    public static boolean isInLangPackage(String javaType) {
        if(javaType == null)
            throw new IllegalArgumentException("参数不能为null");

        Matcher matcher = isInLangPattern.matcher(javaType);
        return matcher.find();
    }

}
