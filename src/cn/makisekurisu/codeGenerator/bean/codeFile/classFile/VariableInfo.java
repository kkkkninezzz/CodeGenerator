package cn.makisekurisu.codeGenerator.bean.codeFile.classFile;

import java.util.List;

/**
 * Created by ym on 2017/2/21 0021.
 *
 * 记录类的变量，包括静态变量和非静态变量
 */
public class VariableInfo extends MemberInfo {

    /**
     * 非访问控制修饰符
     * */
    private String nonAccessControlModifier;

    /**
     * 变量的值
     * */
    private String value;

    public VariableInfo() {
    }

    public VariableInfo(String comment, String accessControlModifier, boolean isStatic, String type, String name, String nonAccessControlModifier, String value) {
        super(comment, accessControlModifier, isStatic, type, name);
        this.nonAccessControlModifier = nonAccessControlModifier;
        this.value = value;
    }

    public VariableInfo(String comment, String accessControlModifier, boolean isStatic, String type, String name, List<String> annotations, String nonAccessControlModifier, String value) {
        super(comment, accessControlModifier, isStatic, type, name, annotations);
        this.nonAccessControlModifier = nonAccessControlModifier;
        this.value = value;
    }

    public String getNonAccessControlModifier() {
        return nonAccessControlModifier;
    }

    public void setNonAccessControlModifier(String nonAccessControlModifier) {
        this.nonAccessControlModifier = nonAccessControlModifier;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
