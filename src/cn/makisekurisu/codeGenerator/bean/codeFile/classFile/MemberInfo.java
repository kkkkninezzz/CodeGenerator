package cn.makisekurisu.codeGenerator.bean.codeFile.classFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ym on 2017/2/21 0021.
 */
public class MemberInfo {
    /**
     * 注释
     * */
    private String comment;

    /**
     * 访问控制修饰符
     * */
    private String accessControlModifier;

    /**
     * 是否是静态
     * */
    private boolean isStatic;

    /**
     * 类型
     * */
    private String type;

    /**
     * 成员名
     * */
    private String name;

    /**
     * 注解
     * */
    private List<String> annotations = new ArrayList<String>();

    public MemberInfo() {
    }

    public MemberInfo(String comment, String accessControlModifier, boolean isStatic, String type, String name) {
        this.comment = comment;
        this.accessControlModifier = accessControlModifier;
        this.isStatic = isStatic;
        this.type = type;
        this.name = name;
    }

    public MemberInfo(String comment, String accessControlModifier, boolean isStatic, String type, String name, List<String> annotations) {
        this.comment = comment;
        this.accessControlModifier = accessControlModifier;
        this.isStatic = isStatic;
        this.type = type;
        this.name = name;
        this.annotations = annotations;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public final String getAccessControlModifier() {
        return accessControlModifier;
    }

    public void setAccessControlModifier(String accessControlModifier) {
        this.accessControlModifier = accessControlModifier;
    }

    public boolean getIsStatic() {
        return isStatic;
    }

    public void setIsStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
