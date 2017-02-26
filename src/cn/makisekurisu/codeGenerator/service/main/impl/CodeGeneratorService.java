package cn.makisekurisu.codeGenerator.service.main.impl;

import cn.makisekurisu.codeGenerator.bean.ModelInfo;
import cn.makisekurisu.codeGenerator.config.ConfigLoader;
import cn.makisekurisu.codeGenerator.config.DataBaseConfig;
import cn.makisekurisu.codeGenerator.service.database.IDataBaseService;
import cn.makisekurisu.codeGenerator.service.database.impl.DataBaseService;
import cn.makisekurisu.codeGenerator.service.main.ICodeGeneratorService;
import cn.makisekurisu.codeGenerator.service.main.generator.CodeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ym on 2017/2/26 0026.
 */
public class CodeGeneratorService implements ICodeGeneratorService {

    private List<CodeGenerator> codeGenerators = new ArrayList<CodeGenerator>();

    private IDataBaseService dataBaseService = new DataBaseService();

    private ExecutorService executorService = Executors.newFixedThreadPool(3);

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
        executorService.shutdown();
    }

    private void generate(List<ModelInfo> modelInfos, ConfigLoader loader) {
        for(CodeGenerator codeGenerator : codeGenerators) {
            executorService.execute(() -> {
                codeGenerator.generateCode(modelInfos, loader);
            });
        }
    }
}
