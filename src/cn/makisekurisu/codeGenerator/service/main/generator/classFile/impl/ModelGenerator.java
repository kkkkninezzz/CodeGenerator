package cn.makisekurisu.codeGenerator.service.main.generator.classFile.impl;

import cn.makisekurisu.codeGenerator.bean.FieldInfo;
import cn.makisekurisu.codeGenerator.bean.ModelInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.classFile.ClassFileInfo;
import cn.makisekurisu.codeGenerator.config.CodeGeneratorConfig;
import cn.makisekurisu.codeGenerator.config.ConfigLoader;
import cn.makisekurisu.codeGenerator.service.main.generator.classFile.JavaBeanGenerator;
import cn.makisekurisu.util.StringUtil;

import java.util.List;

/**
 * Created by ym on 2017/2/22 0022.
 *
 * model的代码生成器
 */
public class ModelGenerator extends JavaBeanGenerator {

    @Override
    protected boolean whetherToGenerateCode(List<ModelInfo> modelInfos, ConfigLoader configLoader) {
        // 需要生成model
        return configLoader.getCodeGeneratorConfig().getModelFlag() && modelInfos!= null && !modelInfos.isEmpty();
    }

    @Override
    protected ClassFileInfo toClassFileInfo(ModelInfo modelInfo, CodeGeneratorConfig config) {
        ClassFileInfo classFileInfo = new ClassFileInfo();

        classFileInfo.setPackageName(modelInfo.getPackageName());
        if(StringUtil.isNullOrEmpty(modelInfo.getComment()))
            classFileInfo.setComment(modelInfo.getComment());
        classFileInfo.setAccessControlModifier(PUBLIC_ACM);
        classFileInfo.setClassName(modelInfo.getModelName());
        classFileInfo.setFileName(formatJavaFileName(classFileInfo.getClassName()));

        // 默认实现Serializable接口
        implementSerializable(classFileInfo);

        // 生成属性
        for(FieldInfo fieldInfo : modelInfo.getFieldInfos()) {
            handleFieldInfo(classFileInfo, fieldInfo);
        }


        return classFileInfo;
    }


}
