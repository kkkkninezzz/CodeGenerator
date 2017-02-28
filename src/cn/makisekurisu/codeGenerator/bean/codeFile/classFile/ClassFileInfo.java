package cn.makisekurisu.codeGenerator.bean.codeFile.classFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ym on 2017/2/21 0021.
 *
 * 类文件使用的信息
 *
 * 该模板用于在单个文件中生成单个类
 */
public class ClassFileInfo extends BaseClassFileInfo {
    private static final String DEFAULT_TEMPLATE_NAME = "classFile.ftl";

    /**
     * 类注释
     * */
    private String comment;

    /**
     * 访问控制修饰符
     * */
    private String accessControlModifier;

    /**
     * 非访问控制修饰符
     * */
    private String nonAccessControlModifier;

    /**
     * 继承的类
     * */
    private String extendsClassName;

    /**
     * 实现的接口数组
     * */
    private List<String> interfaces = new ArrayList<String>();

    /**
     * 变量列表
     * */
    private List<VariableInfo> variableInfos = new ArrayList<VariableInfo>();

    /**
     * 方法列表
     * */
    private List<MethodInfo> methodInfos = new ArrayList<MethodInfo>();

    /**
     * 注解
     * */
    private List<String> annotations = new ArrayList<String>();

    /**
     * 自定义的输出内容
     * */
    private String customContent;

    public ClassFileInfo() {
        setTemplateName(DEFAULT_TEMPLATE_NAME);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAccessControlModifier() {
        return accessControlModifier;
    }

    public void setAccessControlModifier(String accessControlModifier) {
        this.accessControlModifier = accessControlModifier;
    }

    public String getNonAccessControlModifier() {
        return nonAccessControlModifier;
    }

    public void setNonAccessControlModifier(String nonAccessControlModifier) {
        this.nonAccessControlModifier = nonAccessControlModifier;
    }

    public String getExtendsClassName() {
        return extendsClassName;
    }

    public void setExtendsClassName(String extendsClassName) {
        this.extendsClassName = extendsClassName;
    }

    public List<String> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<String> interfaces) {
        this.interfaces = interfaces;
    }

    public String getCustomContent() {
        return customContent;
    }

    public void setCustomContent(String customContent) {
        this.customContent = customContent;
    }

    public List<VariableInfo> getVariableInfos() {
        return variableInfos;
    }

    public void setVariableInfos(List<VariableInfo> variableInfos) {
        this.variableInfos = variableInfos;
    }

    public List<MethodInfo> getMethodInfos() {
        return methodInfos;
    }

    public void setMethodInfos(List<MethodInfo> methodInfos) {
        this.methodInfos = methodInfos;
    }

    public void addVariableInfo(VariableInfo variableInfo) {
        variableInfos.add(variableInfo);
    }

    public void removeVariableInfo(VariableInfo variableInfo) {
        variableInfos.remove(variableInfo);
    }

    public void addMethodInfo(MethodInfo methodInfo) {
        methodInfos.add(methodInfo);
    }

    public void removeMethodInfo(MethodInfo methodInfo) {
        methodInfos.remove(methodInfo);
    }

    public void addInterface(String intf) {
        interfaces.add(intf);
    }

    public void removeInterface(String intf) {
        interfaces.remove(intf);
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<String> annotations) {
        this.annotations = annotations;
    }

    public void addAnnotation(String annotation) {
        this.annotations.add(annotation);
    }

    public void removeAnnotation(String annotation) {
        this.annotations.remove(annotation);
    }
}
