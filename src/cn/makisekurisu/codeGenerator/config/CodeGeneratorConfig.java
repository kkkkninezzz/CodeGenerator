package cn.makisekurisu.codeGenerator.config;

import cn.makisekurisu.codeGenerator.bean.codeFile.CodeFileInfo;
import cn.makisekurisu.util.StringUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ym on 2017/2/19 0019.
 *
 * 生成代码的相关配置
 */
public class CodeGeneratorConfig {
    public static final String PACKAGE_DELIMITER = ".";

    /**
     * 需要生成代码的表 如果值为null或者空字符串，则认为是全部，每张表用,隔开
     * */
    private String needGenerateForTables;

    private Set<String> tableSet = new HashSet<String>();

    /**
     * 代码的基础包名
     * */
    private String basePackageName;

    /**
     * 代码保存的路径
     * */
    private String savePath;

    /**
     * 源代码保存的路径
     * */
    private String savePathForSourceCode;

    /**
     * 是否创建model层
     * */
    private boolean modelFlag;

    /**
     * model层的包名
     * */
    private String modelPackageName;

    /**
     * 是否创建dto层
     * */
    private boolean dtoFlag;

    /**
     * dto层的包名
     * */
    private String dtoPackageName;

    /**
     * 是否生成mapper
     * */
    private boolean mapperFlag;

    /**
     * mapper的包名
     * */
    private String mapperPackageName;

    /**
     * 是否生成mapper.xml
     * */
    private boolean mapperXmlflag;

    /**
     * mapper.xml的包名
     * */
    private String mapperXmlPackageName;

    /**
     * 是否生成service接口层
     * */
    private boolean serviceFlag;

    /**
     * service的包名
     * */
    private String servicePackageName;

    /**
     * 是否生成service实现类层
     * */
    private boolean serviceImplFlag;

    /**
     * service实现类的包名
     * */
    private String serviceImplPackageName;

    /**
     * 是否生成controller层
     * */
    private boolean controllerFlag;

    /**
     * controller的包名
     * */
    private String controllerPackageName;

    /**
     * controllerRequestMapping使用的前缀名
     * */
    private String controllerRequestMappingPrefix;

    /**
     * 是否生成util
     * */
    private boolean utilFlag;

    /**
     * util的包名
     * */
    private String utilPackageName;

    /**
     * 默认生成的类
     * */
    private DefaultGeneratedClasses defaultGeneratedClasses = new DefaultGeneratedClasses();

    public CodeGeneratorConfig() {}


    public String getNeedGenerateForTables() {
        return needGenerateForTables;
    }

    public void setNeedGenerateForTables(String needGenerateForTables) {
        this.needGenerateForTables = needGenerateForTables;
        initTableSet();
    }

    private void initTableSet() {
        if(!StringUtil.isNullOrEmpty(needGenerateForTables)) {
            String[] tables = StringUtil.splitString(needGenerateForTables, ",");
            tableSet = new HashSet<String>(tables.length);
            for(String tableName : tables)
                tableSet.add(tableName.toLowerCase());
        }
    }

    public String getBasePackageName() {
        return basePackageName;
    }

    public void setBasePackageName(String basePackageName) {
        this.basePackageName = basePackageName;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;

        // TODO
        this.savePathForSourceCode = savePath + "/src";
    }

    public boolean getModelFlag() {
        return modelFlag;
    }

    public void setModelFlag(boolean modelFlag) {
        this.modelFlag = modelFlag;
    }

    public String getModelPackageName() {
        return modelPackageName;
    }

    public void setModelPackageName(String modelPackageName) {
        this.modelPackageName = modelPackageName;
    }

    public boolean getDtoFlag() {
        return dtoFlag;
    }

    public void setDtoFlag(boolean dtoFlag) {
        this.dtoFlag = dtoFlag;
    }

    public String getDtoPackageName() {
        return dtoPackageName;
    }

    public void setDtoPackageName(String dtoPackageName) {
        this.dtoPackageName = dtoPackageName;

        setDefaultGeneratedClassesForDto();
    }

    public boolean getMapperFlag() {
        return mapperFlag;
    }

    public void setMapperFlag(boolean mapperFlag) {
        this.mapperFlag = mapperFlag;
    }

    public String getMapperPackageName() {
        return mapperPackageName;
    }

    public void setMapperPackageName(String mapperPackageName) {
        this.mapperPackageName = mapperPackageName;
    }

    public boolean getMapperXmlflag() {
        return mapperXmlflag;
    }

    public void setMapperXmlflag(boolean mapperXmlflag) {
        this.mapperXmlflag = mapperXmlflag;
    }

    public String getMapperXmlPackageName() {
        return mapperXmlPackageName;
    }

    public void setMapperXmlPackageName(String mapperXmlPackageName) {
        this.mapperXmlPackageName = mapperXmlPackageName;
    }

    public boolean getServiceFlag() {
        return serviceFlag;
    }

    public void setServiceFlag(boolean serviceFlag) {
        this.serviceFlag = serviceFlag;
    }

    public String getServicePackageName() {
        return servicePackageName;
    }

    public void setServicePackageName(String servicePackageName) {
        this.servicePackageName = servicePackageName;
    }

    public boolean getServiceImplFlag() {
        return serviceImplFlag;
    }

    public void setServiceImplFlag(boolean serviceImplFlag) {
        this.serviceImplFlag = serviceImplFlag;
    }

    public String getServiceImplPackageName() {
        return serviceImplPackageName;
    }

    public void setServiceImplPackageName(String serviceImplPackageName) {
        this.serviceImplPackageName = serviceImplPackageName;
    }

    public boolean getControllerFlag() {
        return controllerFlag;
    }

    public void setControllerFlag(boolean controllerFlag) {
        this.controllerFlag = controllerFlag;
    }

    public String getControllerPackageName() {
        return controllerPackageName;
    }

    public void setControllerPackageName(String controllerPackageName) {
        this.controllerPackageName = controllerPackageName;
    }

    public String getSavePathForSourceCode() {
        return savePathForSourceCode;
    }

    public void setSavePathForSourceCode(String savePathForSourceCode) {
        this.savePathForSourceCode = savePathForSourceCode;
    }

    public boolean getUtilFlag() {
        return utilFlag;
    }

    public void setUtilFlag(boolean utilFlag) {
        this.utilFlag = utilFlag;
    }

    public String getUtilPackageName() {
        return utilPackageName;
    }

    public void setUtilPackageName(String utilPackageName) {
        this.utilPackageName = utilPackageName;
        setDefaultGeneratedClassesForUtil();
    }

    public String getControllerRequestMappingPrefix() {
        return controllerRequestMappingPrefix;
    }

    public void setControllerRequestMappingPrefix(String controllerRequestMappingPrefix) {
        this.controllerRequestMappingPrefix = controllerRequestMappingPrefix;
    }

    public String getCompleteModelPackageName() {
        if(StringUtil.isNullOrEmptyForMultiStr(basePackageName, modelPackageName))
            return modelPackageName;

        return StringUtil.concatStrs(basePackageName, PACKAGE_DELIMITER, modelPackageName);
    }

    public String getCompleteDtoPackageName() {
        if(StringUtil.isNullOrEmptyForMultiStr(basePackageName, dtoPackageName))
            return dtoPackageName;

        return StringUtil.concatStrs(basePackageName, PACKAGE_DELIMITER, dtoPackageName);
    }

    public String getCompleteMapperPackageName() {
        if(StringUtil.isNullOrEmptyForMultiStr(basePackageName, mapperPackageName))
            return mapperPackageName;

        return StringUtil.concatStrs(basePackageName, PACKAGE_DELIMITER, mapperPackageName);
    }

    public String getCompleteMapperXmlPackageName() {
        if(StringUtil.isNullOrEmptyForMultiStr(basePackageName, mapperXmlPackageName))
            return mapperXmlPackageName;

        return StringUtil.concatStrs(basePackageName, PACKAGE_DELIMITER, mapperXmlPackageName);
    }

    public String getCompleteServicePackageName() {
        if(StringUtil.isNullOrEmptyForMultiStr(basePackageName, servicePackageName))
            return servicePackageName;

        return StringUtil.concatStrs(basePackageName, PACKAGE_DELIMITER, servicePackageName);
    }

    public String getCompleteServiceImplPackageName() {
        if(StringUtil.isNullOrEmptyForMultiStr(basePackageName, serviceImplPackageName))
            return serviceImplPackageName;

        return StringUtil.concatStrs(basePackageName, PACKAGE_DELIMITER, serviceImplPackageName);
    }

    public String getCompleteControllerPackageName() {
        if(StringUtil.isNullOrEmptyForMultiStr(basePackageName, controllerPackageName))
            return controllerPackageName;

        return StringUtil.concatStrs(basePackageName, PACKAGE_DELIMITER, controllerPackageName);
    }

    public String getCompleteUtilPackageName() {
        if(StringUtil.isNullOrEmptyForMultiStr(basePackageName, utilPackageName))
            return utilPackageName;

        return StringUtil.concatStrs(basePackageName, PACKAGE_DELIMITER, utilPackageName);
    }

    /**
     * 判断该表是否在需要生成的限定中
     * */
    public boolean isTableNeedGenerate(String tableName) {
        if(tableSet.isEmpty())
            return true;

        return tableSet.contains(tableName.toLowerCase());
    }

    /**
     * 配置生成util的默认类
     * */
    private void setDefaultGeneratedClassesForUtil() {
        for(CodeFileInfo utilCodeFileInfo : defaultGeneratedClasses.utilClasses) {
            utilCodeFileInfo.setPackageName(getCompleteUtilPackageName());
        }
    }

    /**
     * 配置生成dto的默认类
     * */
    private void setDefaultGeneratedClassesForDto() {
        for(CodeFileInfo dtoCodeFileInfo : defaultGeneratedClasses.dtoClasses) {
            dtoCodeFileInfo.setPackageName(getCompleteDtoPackageName());
        }
    }

    public DefaultGeneratedClasses getDefaultGeneratedClasses() {
        return defaultGeneratedClasses;
    }
}
