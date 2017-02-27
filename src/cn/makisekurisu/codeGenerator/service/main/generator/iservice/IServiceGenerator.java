package cn.makisekurisu.codeGenerator.service.main.generator.iservice;

import cn.makisekurisu.codeGenerator.bean.FieldInfo;
import cn.makisekurisu.codeGenerator.bean.ModelInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.CodeFileInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.IServiceFileInfo;
import cn.makisekurisu.codeGenerator.config.CodeGeneratorConfig;
import cn.makisekurisu.codeGenerator.config.ConfigLoader;
import cn.makisekurisu.codeGenerator.service.main.generator.AbstractCodeGenerator;
import cn.makisekurisu.util.StringUtil;
import cn.makisekurisu.util.TypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ym on 2017/2/27 0027.
 */
public class IServiceGenerator extends AbstractCodeGenerator {
    private static final String DEFAULT_ISERVICE_NAME_FORMAT = "I%sService";

    private static final String DEFAULT_FILE_NAME_FORMAT = DEFAULT_ISERVICE_NAME_FORMAT.concat(".java");

    @Override
    protected boolean whetherToGenerateCode(List<ModelInfo> modelInfos, ConfigLoader configLoader) {
        // 需要生成service接口
        return configLoader.getCodeGeneratorConfig().getServiceFlag() && modelInfos!= null && !modelInfos.isEmpty();
    }

    @Override
    protected List<? extends CodeFileInfo> createCodeFiles(List<ModelInfo> modelInfos, CodeGeneratorConfig generatorConfig) {
        System.out.println("创建service");
        List<IServiceFileInfo> iServiceFileInfos = new ArrayList<IServiceFileInfo>(modelInfos.size());

        for(ModelInfo modelInfo : modelInfos)
            iServiceFileInfos.add(toIServiceFileInfo(modelInfo, generatorConfig));

        return iServiceFileInfos;
    }

    private IServiceFileInfo toIServiceFileInfo(ModelInfo modelInfo, CodeGeneratorConfig generatorConfig) {
        IServiceFileInfo iServiceFileInfo = new IServiceFileInfo();

        iServiceFileInfo.setPackageName(generatorConfig.getCompleteServicePackageName());
        iServiceFileInfo.setiServiceName(String.format(DEFAULT_ISERVICE_NAME_FORMAT, modelInfo.getModelName()));
        iServiceFileInfo.setFileName(String.format(DEFAULT_FILE_NAME_FORMAT, modelInfo.getModelName()));
        iServiceFileInfo.setModelInfo(modelInfo);

        iServiceFileInfo.addImportInfo(StringUtil.concatStrs(modelInfo.getPackageName(), DOT_DEL, modelInfo.getModelName()));

        handlePrimaryKeys(iServiceFileInfo, modelInfo.getPrimaryKeys());

        return iServiceFileInfo;
    }

    // 判断model的主键类型有没有需要import
    private void handlePrimaryKeys(IServiceFileInfo mapperFileInfo, List<FieldInfo> primaryKeys) {
        for(FieldInfo primaryKey : primaryKeys) {
            if(!TypeUtil.isInLangPackage(primaryKey.getJavaType()))
                mapperFileInfo.addImportInfo(primaryKey.getJavaType().getTypeName());
        }
    }
}
