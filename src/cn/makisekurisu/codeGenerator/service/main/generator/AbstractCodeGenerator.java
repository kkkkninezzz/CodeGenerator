package cn.makisekurisu.codeGenerator.service.main.generator;

import cn.makisekurisu.codeGenerator.bean.ModelInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.CodeFileInfo;
import cn.makisekurisu.codeGenerator.config.CodeGeneratorConfig;
import cn.makisekurisu.codeGenerator.config.ConfigLoader;
import cn.makisekurisu.util.FreemarkerUtil;
import cn.makisekurisu.util.IoUtil;
import cn.makisekurisu.util.StringUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by ym on 2017/2/23 0023.
 */
public abstract class AbstractCodeGenerator implements CodeGenerator {
    /**
     * 根据包名生成输出路径
     * */
    protected String generateOutputPath(CodeFileInfo codeFileInfo) {
        String[] packageNames = StringUtil.splitString(codeFileInfo.getPackageName(), ".");

        StringBuilder outputPath = new StringBuilder();
        for(String packageName : packageNames) {
            outputPath.append(packageName);
            outputPath.append("/");
        }
        outputPath.append(codeFileInfo.getFileName());

        return outputPath.toString();
    }

    /**
     * 输出文件
     * */
    protected void outputFiles(List<ModelInfo> modelInfos, ConfigLoader configLoader) {
        CodeGeneratorConfig generatorConfig = configLoader.getCodeGeneratorConfig();
        List<? extends CodeFileInfo> codeFileInfos = createCodeFiles(modelInfos, generatorConfig);
        String basePath = getBasePath(generatorConfig).concat("/");
        for(CodeFileInfo codeFileInfo : codeFileInfos) {
            String outputPath = basePath.concat(generateOutputPath(codeFileInfo));
            File file = new File(outputPath);
            IoUtil.createParentDirectory(file);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                FreemarkerUtil.processTemplate(codeFileInfo.getTemplateName(), codeFileInfo, writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void generateCode(List<ModelInfo> modelInfos, ConfigLoader configLoader) {
        if(!whetherToGenerateCode(modelInfos, configLoader))
            return;

        outputFiles(modelInfos, configLoader);
    }

    /**
     * 获取保存的基础路径
     * */
    protected String getBasePath(CodeGeneratorConfig generatorConfig) {
        return generatorConfig.getSavePathForSourceCode();
    }

    /**
     * 是否要生成代码
     * */
    protected abstract boolean whetherToGenerateCode(List<ModelInfo> modelInfos, ConfigLoader configLoader);

    /**
     * 创建代码生成器文件
     * */
    protected abstract List<? extends CodeFileInfo> createCodeFiles(List<ModelInfo> modelInfos, CodeGeneratorConfig generatorConfig);

    /**
     * 格式化文件名
     * */
    public static String formatJavaFileName(String className) {
        return String.format(JAVA_FILE_NAME_FORMAT, className);
    }
}
