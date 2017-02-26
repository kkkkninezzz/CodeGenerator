package cn.makisekurisu.codeGenerator.bean.codeFile.classFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ym on 2017/2/21 0021.
 *
 * 方法的信息，包括静态方法和非静态方法
 */
public class MethodInfo extends MemberInfo {
    /**
     * 非访问控制修饰符
     * */
    private List<String> nonAccessControlModifiers = new ArrayList<String>();

    /**
     * 方法体
     * */
    private String methodBody;

    /**
     * 参数列表
     * */
    private List<String> arguments = new ArrayList<String>();

    /**
     * 抛出异常列表
     * */
    private Set<String> throwsExceptions = new HashSet<String>();

    public MethodInfo() {
    }

    public MethodInfo(String comment, String accessControlModifier, boolean isStatic, String type, String name, String methodBody) {
        super(comment, accessControlModifier, isStatic, type, name);
        this.methodBody = methodBody;
    }

    public MethodInfo(String comment, String accessControlModifier, boolean isStatic, String type, String name, List<String> annotations, List<String> nonAccessControlModifiers, String methodBody, List<String> arguments, Set<String> throwsExceptions) {
        super(comment, accessControlModifier, isStatic, type, name, annotations);
        this.nonAccessControlModifiers = nonAccessControlModifiers;
        this.methodBody = methodBody;
        this.arguments = arguments;
        this.throwsExceptions = throwsExceptions;
    }

    public List<String> getNonAccessControlModifiers() {
        return nonAccessControlModifiers;
    }

    public void setNonAccessControlModifiers(List<String> nonAccessControlModifiers) {
        this.nonAccessControlModifiers = nonAccessControlModifiers;
    }

    public String getMethodBody() {
        return methodBody;
    }

    public void setMethodBody(String methodBody) {
        this.methodBody = methodBody;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    public Set<String> getThrowsExceptions() {
        return throwsExceptions;
    }

    public void setThrowsExceptions(Set<String> throwsExceptions) {
        this.throwsExceptions = throwsExceptions;
    }

    public void addNonAccessControlModifier(String nonAccessControlModifier) {
        nonAccessControlModifiers.add(nonAccessControlModifier);
    }

    public void removeNonAccessControlModifier(String nonAccessControlModifier) {
        nonAccessControlModifiers.remove(nonAccessControlModifier);
    }

    public void addArgument(String argument) {
        this.arguments.add(argument);
    }

    public void removeArgument(String argument) {
        this.arguments.remove(argument);
    }

    public void addThrowsException(String throwsException) {
        this.throwsExceptions.add(throwsException);
    }

    public void removeThrowsException(String throwsException) {
        this.throwsExceptions.remove(throwsException);
    }
}
