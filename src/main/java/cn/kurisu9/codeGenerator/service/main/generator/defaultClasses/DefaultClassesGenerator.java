package cn.kurisu9.codeGenerator.service.main.generator.defaultClasses;

import cn.kurisu9.codeGenerator.bean.ModelInfo;
import cn.kurisu9.codeGenerator.bean.codeFile.CodeFileInfo;
import cn.kurisu9.codeGenerator.config.CodeGeneratorConfig;
import cn.kurisu9.codeGenerator.config.ConfigLoader;
import cn.kurisu9.codeGenerator.service.main.generator.AbstractCodeGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ym on 2017/2/26 0026.
 */
public class DefaultClassesGenerator extends AbstractCodeGenerator {
    @Override
    protected boolean whetherToGenerateCode(List<ModelInfo> modelInfos, ConfigLoader configLoader) {
        return configLoader.getCodeGeneratorConfig().getUtilFlag();
    }

    @Override
    protected List<? extends CodeFileInfo> createCodeFiles(List<ModelInfo> modelInfos, CodeGeneratorConfig generatorConfig) {
        int size = generatorConfig.getDefaultGeneratedClasses().utilClasses.size() + generatorConfig.getDefaultGeneratedClasses().dtoClasses.size();
        List<CodeFileInfo> codeFileInfos = new ArrayList<CodeFileInfo>(size);

        codeFileInfos.addAll(generatorConfig.getDefaultGeneratedClasses().utilClasses);
        codeFileInfos.addAll(generatorConfig.getDefaultGeneratedClasses().dtoClasses);

        return codeFileInfos;
    }
}
