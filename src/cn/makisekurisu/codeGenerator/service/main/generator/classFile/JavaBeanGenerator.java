package cn.makisekurisu.codeGenerator.service.main.generator.classFile;

import cn.makisekurisu.codeGenerator.bean.FieldInfo;
import cn.makisekurisu.codeGenerator.bean.ModelInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.classFile.ClassFileInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.classFile.MethodInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.classFile.VariableInfo;
import cn.makisekurisu.codeGenerator.config.CodeGeneratorConfig;
import cn.makisekurisu.util.StringUtil;
import cn.makisekurisu.util.TypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ym on 2017/2/22 0022.
 */
public abstract class JavaBeanGenerator extends AbstractClassFileGenerator {
    /**
     * 生成bean的属性
     * */
    protected VariableInfo generateBeanProperty(FieldInfo fieldInfo) {
        VariableInfo variableInfo = new VariableInfo();

        if(!StringUtil.isNullOrEmpty(fieldInfo.getComment()))
            variableInfo.setComment(fieldInfo.getComment());

        variableInfo.setAccessControlModifier(PRIVATE_ACM);
        variableInfo.setType(fieldInfo.getJavaType().getSimpleName());
        variableInfo.setName(fieldInfo.getFieldName());

        return variableInfo;
    }

    /**
     * 生成bean的setter方法
     * */
    protected MethodInfo generateBeanSetterMehthod(VariableInfo variableInfo) {
        MethodInfo setter = new MethodInfo();

        setter.setAccessControlModifier(PUBLIC_ACM);
        setter.setType(VOID_RETURN_TYPE);
        setter.setName("set".concat(StringUtil.toUppercaseForFirstLetter(variableInfo.getName())));
        setter.addArgument(String.format(ARGUMENT_FORMAT, variableInfo.getType(), variableInfo.getName()));
        setter.setMethodBody(String.format(SETTER_FORMAT, variableInfo.getName(), variableInfo.getName()));

        return setter;
    }

    /**
     * 生成bean的getter方法
     * */
    protected MethodInfo generateBeanGetterMehthod(VariableInfo variableInfo) {
        MethodInfo getter = new MethodInfo();

        getter.setAccessControlModifier(PUBLIC_ACM);
        getter.setType(variableInfo.getType());
        getter.setName("get".concat(StringUtil.toUppercaseForFirstLetter(variableInfo.getName())));
        getter.setMethodBody(String.format(GETTER_FORMAT, variableInfo.getName()));

        return getter;
    }


    protected List<? extends ClassFileInfo> createCodeFiles(List<ModelInfo> modelInfos, CodeGeneratorConfig config) {
        List<ClassFileInfo> classFileInfos = new ArrayList<ClassFileInfo>();

        for(ModelInfo modelInfo : modelInfos) {
            classFileInfos.add(toClassFileInfo(modelInfo, config));
        }

        return classFileInfos;
    }



    protected abstract ClassFileInfo toClassFileInfo(ModelInfo modelInfo, CodeGeneratorConfig config);


    protected void handleFieldInfo(ClassFileInfo classFileInfo, FieldInfo fieldInfo) {
        Class<?> fieldType = fieldInfo.getJavaType();
        if(!TypeUtil.isInLangPackage(fieldType))
            classFileInfo.addImportInfo(fieldType.getTypeName());

        VariableInfo variableInfo = generateBeanProperty(fieldInfo);
        classFileInfo.addVariableInfo(variableInfo);
        classFileInfo.addMethodInfo(generateBeanGetterMehthod(variableInfo));
        classFileInfo.addMethodInfo(generateBeanSetterMehthod(variableInfo));
    }

}
