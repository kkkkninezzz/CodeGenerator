package cn.makisekurisu.codeGenerator.service.main.generator;

import cn.makisekurisu.codeGenerator.bean.ModelInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.classFile.VariableInfo;
import cn.makisekurisu.codeGenerator.config.ConfigLoader;

import java.util.List;

/**
 * Created by ym on 2017/2/22 0022.
 *
 * 代码生成器接口
 */
public interface CodeGenerator {

    /**
     * public权限控制符
     * */
    String PUBLIC_ACM = "public";

    /**
     * private权限控制符
     * */
    String PRIVATE_ACM = "private";

    /**
     * final控制符
     * */
    String FINAL_NACM = "final";

    /**
     * void返回值
     * */
    String VOID_RETURN_TYPE = "void";

    /**
     * return关键字
     * */
    String RETURN_KEYWORD = "return";

    /**
     * this关键字
     * */
    String THIS_KEYWORD = "";

    /**
     * .分隔符
     * */
    String DOT_DEL = ".";

    /**
     * =分隔符
     * */
    String EQUALS_DEL = "=";

    /**
     * 空格
     * */
    String SPACE_WORD = " ";

    /**
     * 序列化接口默认实现的变量 private static final long serialVersionUID = 1L
     * */
    VariableInfo DEFAULT_SERIALIZABLE_IMPLEMENT_VAR = new VariableInfo(null, PRIVATE_ACM, true, long.class.getSimpleName(), "serialVersionUID", FINAL_NACM, "1L");

    /**
     * 参数模板
     * */
    String ARGUMENT_FORMAT = "%s %s";

    /**
     * setter方法模板
     * */
    String SETTER_FORMAT = "this.%s = %s;";

    /**
     * getter方法模板
     * */
    String GETTER_FORMAT = "return this.%s;";

    /**
     * java文件名模板
     * */
    String JAVA_FILE_NAME_FORMAT = "%s.java";

    void generateCode(List<ModelInfo> modelInfos, ConfigLoader configLoader);

}
