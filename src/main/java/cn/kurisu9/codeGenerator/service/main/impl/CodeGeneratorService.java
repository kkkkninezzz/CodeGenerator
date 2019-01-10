package cn.kurisu9.codeGenerator.service.main.impl;

import cn.kurisu9.codeGenerator.bean.ModelInfo;
import cn.kurisu9.codeGenerator.config.ConfigLoader;
import cn.kurisu9.codeGenerator.config.DataBaseConfig;
import cn.kurisu9.codeGenerator.service.database.IDataBaseService;
import cn.kurisu9.codeGenerator.service.database.impl.DataBaseService;
import cn.kurisu9.codeGenerator.service.main.ICodeGeneratorService;
import cn.kurisu9.codeGenerator.service.main.generator.CodeGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
*/

/**
 * Created by ym on 2017/2/26 0026.
 */
public class CodeGeneratorService implements ICodeGeneratorService {
    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    private List<CodeGenerator> codeGenerators = new ArrayList<CodeGenerator>();

    private IDataBaseService dataBaseService = new DataBaseService();

   // private ExecutorService executorService = Executors.newFixedThreadPool(1);

    public CodeGeneratorService() {
    }

    public CodeGeneratorService(List<CodeGenerator> codeGenerators) {
        this.codeGenerators = codeGenerators;
    }

    @Override
    public void addGenerator(CodeGenerator codeGenerator) {
        if(codeGenerator != null)
            codeGenerators.add(codeGenerator);
    }

    @Override
    public void removeGenerator(CodeGenerator codeGenerator) {
        codeGenerators.remove(codeGenerator);
    }

    @Override
    public void start() {
        logger.info("---------------代码生成开始---------------");
        long startTime = System.currentTimeMillis();

        ConfigLoader loader = new ConfigLoader();
        try {
            loader.loadConfig();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("配置加载失败");
        }
        DataBaseConfig dataBaseConfig = loader.getDataBaseConfig();

        List<ModelInfo> modelInfos = dataBaseService.loadModelInfos(dataBaseConfig, loader.getCodeGeneratorConfig());

        generate(modelInfos, loader);
       // executorService.shutdown();
        logger.info(String.format("---------------代码生成结束，总共耗时%dms---------------", (System.currentTimeMillis() - startTime)));
    }

    private void generate(List<ModelInfo> modelInfos, ConfigLoader loader) {
        for(CodeGenerator codeGenerator : codeGenerators) {
            codeGenerator.generateCode(modelInfos, loader);
        }
    }
}
