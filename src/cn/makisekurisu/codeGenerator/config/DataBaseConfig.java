package cn.makisekurisu.codeGenerator.config;

import cn.makisekurisu.util.StringUtil;

/**
 * Created by ym on 2017/2/19 0019.
 *
 * 数据库相关配置
 *
 * 该实体用于获取数据库相关链接以及生成jdbc.properties
 */
public class DataBaseConfig {
    private String driverClassName;

    private String url;

    private String username;

    private String password;

    private String initialSize = "0";

    private String maxActive = "20";

    private String maxIdle = "20";

    private String minIdle = "1";

    private String maxWait = "60000";

    private String tableCatalog;

    private String schemaPattern;

    private String tableNamePattern;

    private String tableTypes;

    private String[] tableTypeArray;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(String initialSize) {
        this.initialSize = initialSize;
    }

    public String getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(String maxActive) {
        this.maxActive = maxActive;
    }

    public String getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(String maxIdle) {
        this.maxIdle = maxIdle;
    }

    public String getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(String minIdle) {
        this.minIdle = minIdle;
    }

    public String getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(String maxWait) {
        this.maxWait = maxWait;
    }

    public String getTableCatalog() {
        return tableCatalog;
    }

    public void setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog;
    }

    public String getSchemaPattern() {
        return schemaPattern;
    }

    public void setSchemaPattern(String schemaPattern) {
        this.schemaPattern = schemaPattern;
    }

    public String getTableNamePattern() {
        return tableNamePattern;
    }

    public void setTableNamePattern(String tableNamePattern) {
        this.tableNamePattern = tableNamePattern;
    }

    public String getTableTypes() {
        return tableTypes;
    }

    public void setTableTypes(String tableTypes) {
        this.tableTypes = tableTypes;

        if(!StringUtil.isNullOrEmpty(tableTypes))
            this.tableTypeArray = StringUtil.splitString(tableTypes, ",");
    }

    public String[] getTableTypeArray() {
        return tableTypeArray;
    }
}
