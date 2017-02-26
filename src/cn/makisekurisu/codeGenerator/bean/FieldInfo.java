package cn.makisekurisu.codeGenerator.bean;

/**
 * Created by ym on 2017/2/19 0019.
 *
 * 字段信息
 */
public class FieldInfo {
    /**
     * 数据库中的字段名
     * */
    private String dbFieldName;

    /**
     * 实体的字段名
     * */
    private String fieldName;

    /**
     * 数据库中的类型
     * */
    private String dbType;

    /**
     * 对应的java类型
     * */
    private Class<?> javaType;

    /**
     * 是否是主键
     * */
    private boolean isPrimaryKey;

    /**
     * 是否不为null
     * */
    private boolean isNotNull;

    /**
     * 字段注释
     * */
    private String comment;

    public FieldInfo() {}

    public FieldInfo(String dbFieldName, String fieldName, String dbType, Class<?> javaType, boolean isPrimaryKey, boolean isNotNull, String comment) {
        this.dbFieldName = dbFieldName;
        this.fieldName = fieldName;
        this.dbType = dbType;
        this.javaType = javaType;
        this.isPrimaryKey = isPrimaryKey;
        this.isNotNull = isNotNull;
        this.comment = comment;
    }

    public String getDbFieldName() {
        return dbFieldName;
    }

    public void setDbFieldName(String dbFieldName) {
        this.dbFieldName = dbFieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public void setJavaType(Class<?> javaType) {
        this.javaType = javaType;
    }

    public boolean getIsPrimaryKey() {
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public boolean getIsNotNull() {
        return isNotNull;
    }

    public void setIsNotNull(boolean notNull) {
        isNotNull = notNull;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
