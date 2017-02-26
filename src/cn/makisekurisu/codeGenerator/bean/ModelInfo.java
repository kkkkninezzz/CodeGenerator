package cn.makisekurisu.codeGenerator.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ym on 2017/2/19 0019.
 */
public class ModelInfo {
    /**
     * 包名
     * */
    private String packageName;

    /**
     * 数据库表名
     * */
    private String dbTableName;

    /**
     * 实体名
     * */
    private String modelName;

    /**
     * 表注释
     * */
    private String comment;

    /**
     * 字段
     * */
    private List<FieldInfo> fieldInfos;

    /**
     * 主键列
     * */
    private List<FieldInfo> primaryKeys = new ArrayList<FieldInfo>();

    public ModelInfo() {}

    public ModelInfo(String packageName, String dbTableName, String modelName, String comment, List<FieldInfo> fieldInfos, List<FieldInfo> primaryKeys) {
        this.packageName = packageName;
        this.dbTableName = dbTableName;
        this.modelName = modelName;
        this.comment = comment;
        this.fieldInfos = fieldInfos;
        this.primaryKeys = primaryKeys;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDbTableName() {
        return dbTableName;
    }

    public void setDbTableName(String dbTableName) {
        this.dbTableName = dbTableName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<FieldInfo> getFieldInfos() {
        return fieldInfos;
    }

    public void setFieldInfos(List<FieldInfo> fieldInfos) {
        this.fieldInfos = fieldInfos;
    }

    public List<FieldInfo> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(List<FieldInfo> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public void addPrimaryKey(FieldInfo primaryKey) {
        this.primaryKeys.add(primaryKey);
    }

    public void removePrimaryKey(FieldInfo primaryKey) {
        this.primaryKeys.remove(primaryKey);
    }
}
