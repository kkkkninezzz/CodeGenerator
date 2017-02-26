package cn.makisekurisu.codeGenerator.config;

import cn.makisekurisu.codeGenerator.bean.codeFile.BeanUtilFileInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.CodeFileInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ym on 2017/2/23 0023.
 *
 * 默认生成的一些类
 */
public class DefaultGeneratedClasses {

    public final BeanUtilFileInfo beanUtilFileInfo = new BeanUtilFileInfo();

    public final List<CodeFileInfo> utilClasses = new ArrayList<CodeFileInfo>();

    public DefaultGeneratedClasses() {
        utilClasses.add(beanUtilFileInfo);
    }
}
