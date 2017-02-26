package cn.makisekurisu.codeGenerator.config;

import cn.makisekurisu.util.EntityUtil;

import java.io.*;
import java.util.Properties;

/**
 * Created by ym on 2017/2/19 0019.
 *
 * 配置加载器
 */
public class ConfigLoader {
    private static final String CONFIG_FILE_PATH = "resources/config.properties";

    /**
     * 生成器所使用的全部配置
     * */
    private Properties configs;

    /**
     * jdbc配置
     * */
    private DataBaseConfig dataBaseConfig;

    /**
     * 生成代码的配置
     * */
    private CodeGeneratorConfig codeGeneratorConfig;

    public void loadConfig() throws IOException {
        configs = new Properties();
        InputStream inputStream = new BufferedInputStream(new FileInputStream(CONFIG_FILE_PATH));
        configs.load(inputStream);
        inputStream.close();

        buildSubConfigs();
    }

    private void buildSubConfigs() {
        // 加载jdbc配置
        dataBaseConfig = new DataBaseConfig();
        EntityUtil.initEntity(configs, dataBaseConfig);

        // 加载生成代码的配置
        codeGeneratorConfig = new CodeGeneratorConfig();
        EntityUtil.initEntity(configs, codeGeneratorConfig);
    }

    public DataBaseConfig getDataBaseConfig() {
        return dataBaseConfig;
    }

    public CodeGeneratorConfig getCodeGeneratorConfig() {
        return codeGeneratorConfig;
    }
}
