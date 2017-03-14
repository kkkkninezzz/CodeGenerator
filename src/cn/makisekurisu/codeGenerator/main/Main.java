package cn.makisekurisu.codeGenerator.main;

import cn.makisekurisu.codeGenerator.service.main.ICodeGeneratorService;
import cn.makisekurisu.codeGenerator.service.main.generator.classFile.impl.DtoGenerator;
import cn.makisekurisu.codeGenerator.service.main.generator.classFile.impl.ModelGenerator;
import cn.makisekurisu.codeGenerator.service.main.generator.controller.ControllerGenerator;
import cn.makisekurisu.codeGenerator.service.main.generator.iservice.IServiceGenerator;
import cn.makisekurisu.codeGenerator.service.main.generator.mapper.MapperGenerator;
import cn.makisekurisu.codeGenerator.service.main.generator.mapperXml.MapperXmlGenerator;
import cn.makisekurisu.codeGenerator.service.main.generator.serviceImpl.ServiceImplGenerator;
import cn.makisekurisu.codeGenerator.service.main.generator.defaultClasses.DefaultClassesGenerator;
import cn.makisekurisu.codeGenerator.service.main.impl.CodeGeneratorService;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by ym on 2017/2/26 0026.
 */
public class Main {
    private static final String LOG4J2_XML_PATH = "resources/log4j2.xml";

    public static void main(String[] args) {
        ICodeGeneratorService codeGeneratorService = new CodeGeneratorService();
        addGenerators(codeGeneratorService);

        codeGeneratorService.start();
    }

    /**
     * 添加代码生成器
     * */
    private static void addGenerators(ICodeGeneratorService codeGeneratorService) {
        codeGeneratorService.addGenerator(new ModelGenerator());
        codeGeneratorService.addGenerator(new DtoGenerator());
        codeGeneratorService.addGenerator(new MapperGenerator());
        codeGeneratorService.addGenerator(new MapperXmlGenerator());
        codeGeneratorService.addGenerator(new DefaultClassesGenerator());
        codeGeneratorService.addGenerator(new IServiceGenerator());
        codeGeneratorService.addGenerator(new ServiceImplGenerator());
        codeGeneratorService.addGenerator(new ControllerGenerator());
    }

    /**
     * 初始化log4j
     * */
    private static void initLog4j() throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(LOG4J2_XML_PATH));
        ConfigurationSource source = new ConfigurationSource(in);
        Configurator.initialize(null, source);
    }
}
