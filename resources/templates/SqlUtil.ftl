package ${packageName};

/**
 *
 * 处理sql语句相关
 */
public class SqlUtil {
    private SqlUtil() {}

    /**
     * 格式化字段
     *
     * @param parameters 字段
     * @return 格式化结果
     */
    public static String formatParameters(String... parameters) {
        if(parameters == null || parameters.length == 0)
            return null;
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(parameters[0]);
        for (int i = 1, length = parameters.length; i < length; i++) {
            stringBuilder.append("," + parameters[i]);
        }

        return stringBuilder.toString();
    }

    /**
     * 格式化字段
     *
     * @param parameters 字段 每个参数都是一个长度为2的数组，如new String[]{"数据库字段名", "别名"}
     * @return 格式化结果
     */
    public static String formatParametersForAlias(String[]... parameters) {
        if(parameters == null || parameters.length == 0)
            return null;
        StringBuilder stringBuilder = new StringBuilder();

        formatParameter(stringBuilder, parameters[0]);
        for (int i = 1, length = parameters.length; i < length; i++) {
            stringBuilder.append(",");
            formatParameter(stringBuilder, parameters[i]);
        }

        return stringBuilder.toString();
    }

    /**
     * 格式化字段
     *
     * @param parametersStr 拼接sql字段
     * @param parameter 每个参数都是一个长度为2的数组，如new String[]{"数据库字段名", "别名"}
     * @return 格式化结果
     * */
    private static void formatParameter(StringBuilder parametersStr, String[] parameter) {
        if(StringUtil.isNullOrEmpty(parameter[0]))
            return;

        parametersStr.append(parameter[0]);

        // 添加别名
        if(parameter.length > 1 && !StringUtil.isNullOrEmpty(parameter[1])) {
            parametersStr.append(" as ");
            parametersStr.append(parameter[1]);
        }

    }

    /**
     * 条件SQL参数填充
     * @param condition 条件
     * @param values 参数
     * @return 条件SQL
     */
    @SuppressWarnings("deprecation")
    public static String fillCondition(String condition, Object[] values) {
        if (condition == null) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder(condition);

        for (int i = 0, length = values.length; i < length; i++) {
            int index = stringBuilder.indexOf("?");
            Object value = values[i];
            String param;
            if (value instanceof String) {
                param = "'" + ((String) value).replaceAll("'", "''") + "'";
            } else if (value instanceof java.util.Date) {
                param = "'" + DateUtil.format((java.util.Date) value) + "'";
            } else if (value instanceof java.sql.Date) {
                param = "'" + ((java.sql.Date) value).toLocaleString() + "'";
            } else {
                param = value.toString();
            }
            stringBuilder.replace(index, index + 1, param);
        }

        return stringBuilder.toString();
    }

    public static int getOffset(int curPage, int limit) {
        return (curPage - 1) * limit;
    }
}
