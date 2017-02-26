package cn.makisekurisu.codeGenerator.service.main.generator.classFile;

import cn.makisekurisu.codeGenerator.bean.codeFile.classFile.ClassFileInfo;
import cn.makisekurisu.codeGenerator.config.CodeGeneratorConfig;
import cn.makisekurisu.codeGenerator.service.main.generator.AbstractCodeGenerator;

import java.io.Serializable;

/**
 * Created by ym on 2017/2/23 0023.
 */
public abstract class AbstractClassFileGenerator extends AbstractCodeGenerator {

    /**
     * 为classFileInfo实现Serializable接口
     * */
    protected void implementSerializable(ClassFileInfo classFileInfo) {
        // 实现Serializable接口
        Class<?> serializableClass = Serializable.class;
        classFileInfo.addImportInfo(serializableClass.getTypeName());
        classFileInfo.addInterface(serializableClass.getSimpleName());
        classFileInfo.addVariableInfo(DEFAULT_SERIALIZABLE_IMPLEMENT_VAR);
    }
}
