package cn.makisekurisu.codeGenerator.service.main.generator;

import cn.makisekurisu.codeGenerator.bean.ModelInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.CodeFileInfo;
import cn.makisekurisu.codeGenerator.config.CodeGeneratorConfig;
import cn.makisekurisu.codeGenerator.config.ConfigLoader;
import cn.makisekurisu.util.FreemarkerUtil;
import cn.makisekurisu.util.IoUtil;
import cn.makisekurisu.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by ym on 2017/2/23 0023.
 */
public abstract class AbstractCodeGenerator implements CodeGenerator {
    protected static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

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

        logger.info(String.format("预计生成文件总数: %d", codeFileInfos.size()));
        logger.info(String.format("输出目录: %s", basePath));

        int successCount = 0;
        for(int i = 0; i < codeFileInfos.size(); i++) {
            CodeFileInfo codeFileInfo = codeFileInfos.get(i);
            String outputPath = basePath.concat(generateOutputPath(codeFileInfo));
            File file = new File(outputPath);
            IoUtil.createParentDirectory(file);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                FreemarkerUtil.processTemplate(codeFileInfo.getTemplateName(), codeFileInfo, writer);
                writer.close();

                logger.info(String.format("生成%s(%d/%d): 成功", codeFileInfo.getFileName(), (i + 1), codeFileInfos.size()));
                successCount++;
            } catch (IOException e) {
                e.printStackTrace();
                logger.info(String.format("生成%s(%d/%d): 失败", codeFileInfo.getFileName(), (i + 1), codeFileInfos.size()));
            }
        }

        logger.info(String.format("生成结束，预计生成%d个文件，实际生成%d个文件", codeFileInfos.size(), successCount));
        logger.info("------------------------分割线-------------------------------");
    }

    @Override
    public void generateCode(List<ModelInfo> modelInfos, ConfigLoader configLoader) {
        if(!whetherToGenerateCode(modelInfos, configLoader))
            return;
        logger.info(String.format("-----------------%s----------------", this.getClass().getSimpleName()));
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
