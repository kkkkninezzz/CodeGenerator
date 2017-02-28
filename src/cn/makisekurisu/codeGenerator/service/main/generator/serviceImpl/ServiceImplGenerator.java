package cn.makisekurisu.codeGenerator.service.main.generator.serviceImpl;

import cn.makisekurisu.codeGenerator.bean.ModelInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.CodeFileInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.ServiceImplFileInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.SqlUtilFileInfo;
import cn.makisekurisu.codeGenerator.config.CodeGeneratorConfig;
import cn.makisekurisu.codeGenerator.config.ConfigLoader;
import cn.makisekurisu.codeGenerator.service.main.generator.BaseClassFileGenerator;
import cn.makisekurisu.codeGenerator.service.main.generator.iservice.IServiceGenerator;
import cn.makisekurisu.codeGenerator.service.main.generator.mapper.MapperGenerator;
import cn.makisekurisu.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ym on 2017/2/28 0028.
 */
public class ServiceImplGenerator extends BaseClassFileGenerator {
    private static final String DEFAULT_SERVICE_IMPL_NAME_FORMAT = "%sService";


    @Override
    protected boolean whetherToGenerateCode(List<ModelInfo> modelInfos, ConfigLoader configLoader) {
        // 需要生成ServiceImpl
        return configLoader.getCodeGeneratorConfig().getServiceImplFlag() && modelInfos!= null && !modelInfos.isEmpty();
    }

    @Override
    protected List<? extends CodeFileInfo> createCodeFiles(List<ModelInfo> modelInfos, CodeGeneratorConfig generatorConfig) {
        List<ServiceImplFileInfo> serviceImplFileInfos = new ArrayList<ServiceImplFileInfo>();

        for(ModelInfo modelInfo : modelInfos)
            serviceImplFileInfos.add(toServiceImplFileInfo(modelInfo, generatorConfig));

        return serviceImplFileInfos;
    }

    private ServiceImplFileInfo toServiceImplFileInfo(ModelInfo modelInfo, CodeGeneratorConfig generatorConfig) {
        ServiceImplFileInfo serviceImplFileInfo = new ServiceImplFileInfo();

        serviceImplFileInfo.setPackageName(generatorConfig.getCompleteServiceImplPackageName());
        serviceImplFileInfo.setClassName(formatServiceImplName(modelInfo.getModelName()));
        serviceImplFileInfo.setFileName(formatJavaFileName(serviceImplFileInfo.getClassName()));
        serviceImplFileInfo.setModelInfo(modelInfo);

        serviceImplFileInfo.addImportInfo(StringUtil.concatStrs(modelInfo.getPackageName(), DOT_DEL, modelInfo.getModelName()));

        addUtil(serviceImplFileInfo, generatorConfig);
        addMapper(serviceImplFileInfo, modelInfo, generatorConfig);
        addIService(serviceImplFileInfo, modelInfo, generatorConfig);

        handlePrimaryKeys(serviceImplFileInfo, modelInfo.getPrimaryKeys());

        return serviceImplFileInfo;
    }

    // 添加工具
    private void addUtil(ServiceImplFileInfo serviceImplFileInfo, CodeGeneratorConfig generatorConfig) {
        SqlUtilFileInfo sqlUtilFileInfo = generatorConfig.getDefaultGeneratedClasses().sqlUtilFileInfo;
        serviceImplFileInfo.addImportInfo(StringUtil.concatStrs(sqlUtilFileInfo.getPackageName(), DOT_DEL, SqlUtilFileInfo.CLASS_NAME));
    }

    // 添加mapper
    private void addMapper(ServiceImplFileInfo serviceImplFileInfo, ModelInfo modelInfo, CodeGeneratorConfig generatorConfig) {
        String mapperName = MapperGenerator.formatMapperName(modelInfo.getModelName());
        serviceImplFileInfo.setMapperName(mapperName);
        serviceImplFileInfo.addImportInfo(StringUtil.concatStrs(generatorConfig.getCompleteMapperPackageName(), DOT_DEL, mapperName));
    }

    // 添加service接口
    private void addIService(ServiceImplFileInfo serviceImplFileInfo, ModelInfo modelInfo, CodeGeneratorConfig generatorConfig) {
        String iServiceName = IServiceGenerator.formatIServiceName(modelInfo.getModelName());
        serviceImplFileInfo.setServiceInterfaceName(iServiceName);
        serviceImplFileInfo.addImportInfo(StringUtil.concatStrs(generatorConfig.getCompleteServicePackageName(), DOT_DEL, iServiceName));
    }

    public static String formatServiceImplName(String modelName) {
        return String.format(DEFAULT_SERVICE_IMPL_NAME_FORMAT, modelName);
    }
}
