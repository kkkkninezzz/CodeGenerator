package cn.kurisu9.util;

import org.apache.ibatis.type.JdbcType;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Clob;
import java.sql.Ref;
import java.sql.Struct;
import java.util.*;
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

    /**
     * 基础类型
     * */
    private static Map<JdbcType, Class<?>> javaTypes = new HashMap<>();

    /**
     * 包装类型
     * */
    private static Map<JdbcType, Class<?>> javaWrapperTypes = new HashMap<>();

    /**
     * 用于匹配是否是lang包下的类
     * */
    private static Pattern isInLangPattern = Pattern.compile("^(java\\.lang\\.)\\w*");

    /**
     * 基本类型
     * */
    private static Set<Class<?>> baseTypes = new HashSet<>();


    static {
        javaWrapperTypes.put(JdbcType.CHAR, String.class);
        javaWrapperTypes.put(JdbcType.VARCHAR, String.class);
        javaWrapperTypes.put(JdbcType.LONGNVARCHAR, String.class);
        javaWrapperTypes.put(JdbcType.LONGVARCHAR, String.class);

        javaWrapperTypes.put(JdbcType.NUMERIC, BigDecimal.class);
        javaWrapperTypes.put(JdbcType.DECIMAL, BigDecimal.class);

        javaWrapperTypes.put(JdbcType.BIT, Boolean.class);
        javaWrapperTypes.put(JdbcType.BOOLEAN, Boolean.class);

        javaWrapperTypes.put(JdbcType.TINYINT, Byte.class);

        javaWrapperTypes.put(JdbcType.SMALLINT, Short.class);

        javaWrapperTypes.put(JdbcType.INTEGER, Integer.class);

        javaWrapperTypes.put(JdbcType.BIGINT, Long.class);

        javaWrapperTypes.put(JdbcType.REAL, Float.class);

        javaWrapperTypes.put(JdbcType.FLOAT, Double.class);
        javaWrapperTypes.put(JdbcType.DOUBLE, Double.class);

        javaWrapperTypes.put(JdbcType.BINARY, byte[].class);
        javaWrapperTypes.put(JdbcType.VARBINARY, byte[].class);
        javaWrapperTypes.put(JdbcType.LONGVARBINARY, byte[].class);
        javaWrapperTypes.put(JdbcType.BLOB, byte[].class);

        javaWrapperTypes.put(JdbcType.DATE, Date.class);
        javaWrapperTypes.put(JdbcType.TIME, Date.class);
        javaWrapperTypes.put(JdbcType.TIMESTAMP, Date.class);

        javaWrapperTypes.put(JdbcType.CLOB, Clob.class);

        javaWrapperTypes.put(JdbcType.ARRAY, Array.class);

        javaWrapperTypes.put(JdbcType.DISTINCT, null);

        javaWrapperTypes.put(JdbcType.STRUCT, Struct.class);

        javaWrapperTypes.put(JdbcType.REF, Ref.class);

        javaWrapperTypes.put(JdbcType.DATALINK, URL.class);

    }

    static {
        javaTypes.put(JdbcType.CHAR, String.class);
        javaTypes.put(JdbcType.VARCHAR, String.class);
        javaTypes.put(JdbcType.LONGNVARCHAR, String.class);
        javaTypes.put(JdbcType.LONGVARCHAR, String.class);

        javaTypes.put(JdbcType.NUMERIC, double.class);
        javaTypes.put(JdbcType.DECIMAL, double.class);

        javaTypes.put(JdbcType.BIT, boolean.class);
        javaTypes.put(JdbcType.BOOLEAN, boolean.class);

        javaTypes.put(JdbcType.TINYINT, byte.class);

        javaTypes.put(JdbcType.SMALLINT, short.class);

        javaTypes.put(JdbcType.INTEGER, int.class);

        javaTypes.put(JdbcType.BIGINT, long.class);

        javaTypes.put(JdbcType.REAL, float.class);

        javaTypes.put(JdbcType.FLOAT, double.class);
        javaTypes.put(JdbcType.DOUBLE, double.class);

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

    static {
        baseTypes.add(String.class);
        baseTypes.add(int.class);
        baseTypes.add(short.class);
        baseTypes.add(byte.class);
        baseTypes.add(long.class);
        baseTypes.add(boolean.class);
        baseTypes.add(float.class);
        baseTypes.add(double.class);
    }

    /**
     * 获取mybatis的jdbcType对应的java类型
     *
     * @param jdbcType jdbc的类型
     * @param isWrapped 是否获取包装类型
     * */
    public static Class<?> getJavaTypeByJdbcType(JdbcType jdbcType, boolean isWrapped) {
        if (isWrapped) {
            return javaWrapperTypes.get(jdbcType);
        }

        return javaTypes.get(jdbcType);
    }

    /**
     * 判断class时候是lang包下
     * */
    public static boolean isInLangPackage(Class<?> javaType) {
        if(javaType == null)
            throw new IllegalArgumentException("参数不能为null");
        if (baseTypes.contains(javaType)) {
            return true;
        }
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
