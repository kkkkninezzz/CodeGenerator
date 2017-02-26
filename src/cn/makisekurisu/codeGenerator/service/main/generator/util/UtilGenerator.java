package cn.makisekurisu.codeGenerator.service.main.generator.util;

import cn.makisekurisu.codeGenerator.bean.ModelInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.CodeFileInfo;
import cn.makisekurisu.codeGenerator.config.CodeGeneratorConfig;
import cn.makisekurisu.codeGenerator.config.ConfigLoader;
import cn.makisekurisu.codeGenerator.service.main.generator.AbstractCodeGenerator;

import java.util.List;

/**
 * Created by ym on 2017/2/26 0026.
 */
public class UtilGenerator extends AbstractCodeGenerator {
    @Override
    protected boolean whetherToGenerateCode(List<ModelInfo> modelInfos, ConfigLoader configLoader) {
        return configLoader.getCodeGeneratorConfig().getUtilFlag();
    }

    @Override
    protected List<? extends CodeFileInfo> createCodeFiles(List<ModelInfo> modelInfos, CodeGeneratorConfig generatorConfig) {
        return generatorConfig.getDefaultGeneratedClasses().utilClasses;
    }
}
