package cn.makisekurisu.codeGenerator.service.main.generator.iservice;

import cn.makisekurisu.codeGenerator.bean.FieldInfo;
import cn.makisekurisu.codeGenerator.bean.ModelInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.CodeFileInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.IServiceFileInfo;
import cn.makisekurisu.codeGenerator.config.CodeGeneratorConfig;
import cn.makisekurisu.codeGenerator.config.ConfigLoader;
import cn.makisekurisu.codeGenerator.service.main.generator.BaseClassFileGenerator;
import cn.makisekurisu.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ym on 2017/2/27 0027.
 */
public class IServiceGenerator extends BaseClassFileGenerator {
    private static final String DEFAULT_ISERVICE_NAME_FORMAT = "I%sService";

    @Override
    protected boolean whetherToGenerateCode(List<ModelInfo> modelInfos, ConfigLoader configLoader) {
        // 需要生成service接口
        return configLoader.getCodeGeneratorConfig().getServiceFlag() && modelInfos!= null && !modelInfos.isEmpty();
    }

    @Override
    protected List<? extends CodeFileInfo> createCodeFiles(List<ModelInfo> modelInfos, CodeGeneratorConfig generatorConfig) {
        List<IServiceFileInfo> iServiceFileInfos = new ArrayList<IServiceFileInfo>(modelInfos.size());

        for(ModelInfo modelInfo : modelInfos)
            iServiceFileInfos.add(toIServiceFileInfo(modelInfo, generatorConfig));

        return iServiceFileInfos;
    }

    private IServiceFileInfo toIServiceFileInfo(ModelInfo modelInfo, CodeGeneratorConfig generatorConfig) {
        IServiceFileInfo iServiceFileInfo = new IServiceFileInfo();

        iServiceFileInfo.setPackageName(generatorConfig.getCompleteServicePackageName());
        iServiceFileInfo.setClassName(formatIServiceName(modelInfo.getModelName()));
        iServiceFileInfo.setFileName(formatJavaFileName(iServiceFileInfo.getClassName()));
        iServiceFileInfo.setModelInfo(modelInfo);

        iServiceFileInfo.addImportInfo(StringUtil.concatStrs(modelInfo.getPackageName(), DOT_DEL, modelInfo.getModelName()));

        handlePrimaryKeys(iServiceFileInfo, modelInfo.getPrimaryKeys());

        return iServiceFileInfo;
    }

    public static String formatIServiceName(String modelName) {
        return String.format(DEFAULT_ISERVICE_NAME_FORMAT, modelName);
    }

}
