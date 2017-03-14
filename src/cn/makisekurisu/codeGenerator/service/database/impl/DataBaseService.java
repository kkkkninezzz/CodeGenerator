package cn.makisekurisu.codeGenerator.service.database.impl;

import cn.makisekurisu.codeGenerator.bean.FieldInfo;
import cn.makisekurisu.codeGenerator.bean.ModelInfo;
import cn.makisekurisu.codeGenerator.config.DataBaseConfig;
import cn.makisekurisu.codeGenerator.config.CodeGeneratorConfig;
import cn.makisekurisu.codeGenerator.service.database.IDataBaseService;
import cn.makisekurisu.util.StringUtil;
import cn.makisekurisu.util.TypeUtil;
import org.apache.ibatis.type.JdbcType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ym on 2017/2/19 0019.
 */
public class DataBaseService implements IDataBaseService {
    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Override
    public List<ModelInfo> loadModelInfos(DataBaseConfig dataBaseConfig, CodeGeneratorConfig codeGeneratorConfig) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection(
                dataBaseConfig.getDriverClassName(), dataBaseConfig.getUrl(), dataBaseConfig.getUsername(), dataBaseConfig.getPassword());

        Connection connection = null;
        ResultSet tableRs = null;
        List<ModelInfo> modelInfos = null;
        try {
            logger.info("-----------获取数据库表开始----------");

            logger.info("驱动类: {}", dataBaseConfig.getDriverClassName());
            logger.info("数据库url: {}", dataBaseConfig.getUrl());
            logger.info("数据库用户名: {}", dataBaseConfig.getUsername());
            logger.info("数据库密码: {}", dataBaseConfig.getPassword());

            logger.info("TableCatalog: {}", dataBaseConfig.getTableCatalog());
            logger.info("SchemaPattern: {}", dataBaseConfig.getSchemaPattern());
            logger.info("TableNamePattern: {}", dataBaseConfig.getTableNamePattern());
            logger.info("TableTypes: {}", dataBaseConfig.getTableTypes());

            connection = dataBaseConnection.getConnection();
            // 获取此连接对象所连接的数据库的元数据
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            // 根据配置获取到表
            tableRs = databaseMetaData.getTables(dataBaseConfig.getTableCatalog(), dataBaseConfig.getSchemaPattern(),
                    dataBaseConfig.getTableNamePattern(), dataBaseConfig.getTableTypeArray());


            modelInfos = new ArrayList<ModelInfo>();
            String modelPackageName = codeGeneratorConfig.getCompleteModelPackageName();

            int tableCount = 0;
            logger.info("开始处理数据库表: ");
            while(tableRs.next()) {
                tableCount++;

                String tableName = tableRs.getString(TABLE_NAME);

                logger.info("表名: {}", tableName);

                if(!codeGeneratorConfig.isTableNeedGenerate(tableName)) {
                    logger.info("{}表不需要生成代码", tableName);
                    continue;
                }

                ModelInfo modelInfo = new ModelInfo();
                modelInfo.setPackageName(modelPackageName);
                modelInfo.setDbTableName(tableName);
                modelInfo.setModelName(convertToModelName(tableName));
                modelInfo.setComment(tableRs.getString(REMARKS));

                // 默认处理该表的所有列
                List<FieldInfo> fieldInfos = handleColumns(databaseMetaData.getColumns(null, dataBaseConfig.getSchemaPattern(), tableName, "%"));
                modelInfo.setFieldInfos(fieldInfos);

                // 处理该表的主键字段
                handlePrimaryKeys(databaseMetaData.getPrimaryKeys(null, dataBaseConfig.getSchemaPattern(), tableName), modelInfo);

                modelInfos.add(modelInfo);
            }


            logger.info(String.format("共获取%d张表，实际处理%d张表", tableCount, modelInfos.size()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBaseConnection.closeAll(tableRs, null, connection);
        }

        logger.info("-----------获取数据库表结束----------");

        return modelInfos;
    }

    /**
     * 将表名转换为实体名
     * 表名多个单词以下划线隔开
     * 实体名使用驼峰规则
     * */
    private String convertToModelName(String tableName) {
        String[] words = StringUtil.splitString(tableName.toLowerCase(), "_");
        StringBuilder entityName = new StringBuilder();
        for(int i = 0; i < words.length; i++) {
            entityName.append(StringUtil.toUppercaseForFirstLetter(words[i]));
        }

        return entityName.toString();
    }

    /**
     * 将数据库字段名转换为实体的成员变量名
     * 数据库字段名多个单词以下划线隔开
     * 实体名使用驼峰规则
     * */
    private String convertToModelMemberName(String columnName) {
        String[] words = StringUtil.splitString(columnName.toLowerCase(), "_");
        StringBuilder memberName = new StringBuilder(words[0]);
        for(int i = 1; i < words.length; i++) {
            memberName.append(StringUtil.toUppercaseForFirstLetter(words[i]));
        }

        return memberName.toString();
    }

    /**
     * 处理主键
     * */
    private void handlePrimaryKeys(ResultSet primaryKeySet, ModelInfo modelInfo) throws SQLException {
        while (primaryKeySet.next()) {
            String columnName = primaryKeySet.getString(COLUMN_NAME);

            for(FieldInfo info : modelInfo.getFieldInfos())
                if(info.getDbFieldName().equals(columnName)) {
                    info.setIsPrimaryKey(true);
                    modelInfo.addPrimaryKey(info);
                    break;
                }
        }
    }

    /**
     * 处理列
     * */
    private List<FieldInfo> handleColumns(ResultSet columnRS) throws SQLException {
        List<FieldInfo> fieldInfos = new ArrayList<FieldInfo>();

        while (columnRS.next()) {
            FieldInfo fieldInfo = new FieldInfo();

            String columnName = columnRS.getString(COLUMN_NAME);
            fieldInfo.setDbFieldName(columnName);
            fieldInfo.setFieldName(convertToModelMemberName(columnName));

            JdbcType jdbcType = JdbcType.forCode(columnRS.getInt(DATA_TYPE));
            fieldInfo.setDbType(jdbcType.toString());
            fieldInfo.setJavaType(TypeUtil.getJavaTypeByJdbcType(jdbcType));

            fieldInfo.setIsNotNull(columnRS.getInt(NULLABLE) == DatabaseMetaData.columnNoNulls);

            fieldInfo.setComment(columnRS.getString(REMARKS));

            fieldInfos.add(fieldInfo);
        }

        return fieldInfos;
    }

    private static class DataBaseConnection {
        private String driverName;    //	驱动名
        private String url;    //	ַ数据库地址
        private String userName;    //	一般为root
        private String password;        //	密码

        public DataBaseConnection(String driverName, String url, String userName, String password) {
            this.driverName = driverName;
            this.url = url;
            this.userName = userName;
            this.password = password;

            init();
        }

        private void init() {
            try {
                Class.forName(driverName);	//加载驱动
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        public Connection getConnection() throws SQLException {
            return DriverManager.getConnection(url, userName, password);
        }

        public void closeAll(ResultSet rs, Statement st, Connection conn) {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (st != null)
                        st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (conn != null)
                            conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
