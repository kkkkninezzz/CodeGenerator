package cn.makisekurisu.codeGenerator.service.main;

import cn.makisekurisu.codeGenerator.service.main.generator.CodeGenerator;

/**
 * Created by ym on 2017/2/22 0022.
 *
 * 代码生成器服务
 */
public interface ICodeGeneratorService {
    /**
     * 添加生成器
     * */
    void addGenerator(CodeGenerator codeGenerator);

    /**
     * 移除代码生成器
     * */
    void removeGenerator(CodeGenerator codeGenerator);

    /**
     * 开始服务
     * */
    void start();
}
