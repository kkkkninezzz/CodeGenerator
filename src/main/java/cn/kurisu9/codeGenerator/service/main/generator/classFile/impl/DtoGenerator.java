package cn.kurisu9.codeGenerator.service.main.generator.classFile.impl;

import cn.kurisu9.codeGenerator.bean.FieldInfo;
import cn.kurisu9.codeGenerator.bean.ModelInfo;
import cn.kurisu9.codeGenerator.bean.codeFile.BeanUtilFileInfo;
import cn.kurisu9.codeGenerator.bean.codeFile.classFile.ClassFileInfo;
import cn.kurisu9.codeGenerator.bean.codeFile.classFile.MethodInfo;
import cn.kurisu9.codeGenerator.config.CodeGeneratorConfig;
import cn.kurisu9.codeGenerator.config.ConfigLoader;
import cn.kurisu9.codeGenerator.service.main.generator.classFile.JavaBeanGenerator;
import cn.kurisu9.util.StringUtil;
import cn.kurisu9.util.TypeUtil;

import java.util.List;

/**
 * Created by ym on 2017/2/22 0022.
 */
public class DtoGenerator extends JavaBeanGenerator {
    private static final String DEFAULT_DTO_NAME_FORMAT = "%sDTO";

    private static final String TO_MODEL_METHOD_BODY_FORMAT = "%s model = new %s();\n\t\t\tBeanUtil.convert(this, model);\n\t\t\treturn model;";

    @Override
    protected boolean whetherToGenerateCode(List<ModelInfo> modelInfos, ConfigLoader configLoader) {
        // 需要生成dto
        return configLoader.getCodeGeneratorConfig().getDtoFlag() && modelInfos!= null && !modelInfos.isEmpty();
    }

    protected ClassFileInfo toClassFileInfo(ModelInfo modelInfo, CodeGeneratorConfig config) {
        ClassFileInfo classFileInfo = new ClassFileInfo();

        classFileInfo.setPackageName(config.getCompleteDtoPackageName());
        classFileInfo.setAccessControlModifier(PUBLIC_ACM);
        classFileInfo.setClassName(formatDtoName(modelInfo.getModelName()));
        classFileInfo.setFileName(formatJavaFileName(classFileInfo.getClassName()));

        // 默认实现Serializable接口
        implementSerializable(classFileInfo);

        // 生成属性
        for(FieldInfo fieldInfo : modelInfo.getFieldInfos()) {
            // 如果是主键则不生成该属性
            if(fieldInfo.getIsPrimaryKey())
                continue;
            handleFieldInfo(classFileInfo, fieldInfo);
        }

        addToModelMethod(classFileInfo, modelInfo, config);

        return classFileInfo;
    }

    /**
     * 为dto层增加toModel方法
     * */
    private void addToModelMethod(ClassFileInfo classFileInfo, ModelInfo modelInfo, CodeGeneratorConfig config) {
        BeanUtilFileInfo beanUtilFileInfo = config.getDefaultGeneratedClasses().beanUtilFileInfo;
        // 添加转换工具
        String beanUtilFileInfoPackageName = StringUtil.concatStrs(beanUtilFileInfo.getPackageName(), DOT_DEL, BeanUtilFileInfo.CLASS_NAME);
        if(!TypeUtil.isInLangPackage(beanUtilFileInfoPackageName))
            classFileInfo.addImportInfo(beanUtilFileInfoPackageName);

        // 添加model的包
        String modelPackageName = StringUtil.concatStrs(modelInfo.getPackageName(), DOT_DEL, modelInfo.getModelName());
        if(!TypeUtil.isInLangPackage(modelPackageName))
            classFileInfo.addImportInfo(modelPackageName);

        MethodInfo toModelMethod = new MethodInfo();
        toModelMethod.setAccessControlModifier(PUBLIC_ACM);
        toModelMethod.setType(modelInfo.getModelName());
        toModelMethod.setName("toModel");
        toModelMethod.setMethodBody(String.format(TO_MODEL_METHOD_BODY_FORMAT, modelInfo.getModelName(), modelInfo.getModelName()));

        classFileInfo.addMethodInfo(toModelMethod);
    }

    public static String formatDtoName(String modelName) {
        return String.format(DEFAULT_DTO_NAME_FORMAT, modelName);
    }
}
