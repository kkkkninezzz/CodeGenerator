package cn.makisekurisu.codeGenerator.bean.codeFile;

import cn.makisekurisu.codeGenerator.bean.ModelInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.classFile.BaseClassFileInfo;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ym on 2017/2/27 0027.
 */
public class IServiceFileInfo extends BaseClassFileInfo {
    private static final String DEFAULT_TEMPLATE_NAME = "IService.ftl";

    private ModelInfo modelInfo;

    public IServiceFileInfo() {
        setTemplateName(DEFAULT_TEMPLATE_NAME);
    }

    public ModelInfo getModelInfo() {
        return modelInfo;
    }

    public void setModelInfo(ModelInfo modelInfo) {
        this.modelInfo = modelInfo;
    }
}
