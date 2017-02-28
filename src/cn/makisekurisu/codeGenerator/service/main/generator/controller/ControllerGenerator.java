package cn.makisekurisu.codeGenerator.service.main.generator.controller;

import cn.makisekurisu.codeGenerator.bean.FieldInfo;
import cn.makisekurisu.codeGenerator.bean.ModelInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.CodeFileInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.ControllerFileInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.LimitShowDTOFileInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.ResponseUtilFileInfo;
import cn.makisekurisu.codeGenerator.config.CodeGeneratorConfig;
import cn.makisekurisu.codeGenerator.config.ConfigLoader;
import cn.makisekurisu.codeGenerator.service.main.generator.BaseClassFileGenerator;
import cn.makisekurisu.codeGenerator.service.main.generator.classFile.impl.DtoGenerator;
import cn.makisekurisu.codeGenerator.service.main.generator.iservice.IServiceGenerator;
import cn.makisekurisu.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ym on 2017/2/28 0028.
 */
public class ControllerGenerator extends BaseClassFileGenerator {
    private static final String DEFAULT_CONTROLLER_NAME_FORMAT = "%sController";

    @Override
    protected boolean whetherToGenerateCode(List<ModelInfo> modelInfos, ConfigLoader configLoader) {
        // 需要生成controller
        return configLoader.getCodeGeneratorConfig().getControllerFlag() && modelInfos!= null && !modelInfos.isEmpty();
    }

    @Override
    protected List<? extends CodeFileInfo> createCodeFiles(List<ModelInfo> modelInfos, CodeGeneratorConfig generatorConfig) {
        List<ControllerFileInfo> controllerFileInfos = new ArrayList<ControllerFileInfo>();

        for(ModelInfo modelInfo : modelInfos)
            controllerFileInfos.add(toControllerFileInfo(modelInfo, generatorConfig));

        return controllerFileInfos;
    }

    private ControllerFileInfo toControllerFileInfo(ModelInfo modelInfo, CodeGeneratorConfig generatorConfig) {
        ControllerFileInfo controllerFileInfo = new ControllerFileInfo();

        controllerFileInfo.setPackageName(generatorConfig.getCompleteControllerPackageName());
        controllerFileInfo.setClassName(formatControllerName(modelInfo.getModelName()));
        controllerFileInfo.setFileName(formatJavaFileName(controllerFileInfo.getClassName()));
        controllerFileInfo.setModelInfo(modelInfo);

        controllerFileInfo.addImportInfo(StringUtil.concatStrs(modelInfo.getPackageName(), DOT_DEL, modelInfo.getModelName()));

        setControllerRequestMapping(controllerFileInfo, modelInfo, generatorConfig);
        addUtil(controllerFileInfo, generatorConfig);
        addDto(controllerFileInfo, modelInfo, generatorConfig);
        addIService(controllerFileInfo, modelInfo, generatorConfig);

        handlePrimaryKeys(controllerFileInfo, modelInfo.getPrimaryKeys());
        return controllerFileInfo;
    }

    // 设置controllerRequestMapping
    private void setControllerRequestMapping(ControllerFileInfo controllerFileInfo, ModelInfo modelInfo, CodeGeneratorConfig generatorConfig) {
        String controllerRequestMapping = StringUtil.isNullOrEmpty(generatorConfig.getControllerRequestMappingPrefix()) ? "" : generatorConfig.getControllerRequestMappingPrefix().concat("/");
        controllerFileInfo.setControllerRequestMapping(controllerRequestMapping.concat(StringUtil.toLowercaseForFirstLetter(modelInfo.getModelName())));
    }

    // 添加工具
    private void addUtil(ControllerFileInfo controllerFileInfo, CodeGeneratorConfig generatorConfig) {
        ResponseUtilFileInfo responseUtilFileInfo = generatorConfig.getDefaultGeneratedClasses().responseUtilFileInfo;
        controllerFileInfo.addImportInfo(StringUtil.concatStrs(responseUtilFileInfo.getPackageName(), DOT_DEL, ResponseUtilFileInfo.CLASS_NAME));
    }

    // 添加dto
    private void addDto(ControllerFileInfo controllerFileInfo, ModelInfo modelInfo, CodeGeneratorConfig generatorConfig) {
        String modelDtoName = DtoGenerator.formatDtoName(modelInfo.getModelName());
        controllerFileInfo.setDtoName(modelDtoName);
        controllerFileInfo.addImportInfo(StringUtil.concatStrs(generatorConfig.getCompleteDtoPackageName(), DOT_DEL, modelDtoName));

        LimitShowDTOFileInfo limitShowDTOFileInfo = generatorConfig.getDefaultGeneratedClasses().limitShowDTOFileInfo;
        controllerFileInfo.addImportInfo(StringUtil.concatStrs(limitShowDTOFileInfo.getPackageName(), DOT_DEL, LimitShowDTOFileInfo.CLASS_NAME));
    }

    // 添加service接口
    private void addIService(ControllerFileInfo controllerFileInfo, ModelInfo modelInfo, CodeGeneratorConfig generatorConfig) {
        String iServiceName = IServiceGenerator.formatIServiceName(modelInfo.getModelName());
        controllerFileInfo.setServiceInterfaceName(iServiceName);
        controllerFileInfo.addImportInfo(StringUtil.concatStrs(generatorConfig.getCompleteServicePackageName(), DOT_DEL, iServiceName));
    }

    public static String formatControllerName(String modelName) {
        return String.format(DEFAULT_CONTROLLER_NAME_FORMAT, modelName);
    }
}
