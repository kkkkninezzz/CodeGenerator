package cn.kurisu9.codeGenerator.bean.codeFile;

import cn.kurisu9.codeGenerator.bean.ModelInfo;
import cn.kurisu9.codeGenerator.bean.codeFile.classFile.BaseClassFileInfo;

/**
 * Created by ym on 2017/2/24 0024.
 */
public class MapperFileInfo extends BaseClassFileInfo {
    private static final String DEFAULT_TEMPLATE_NAME = "Mapper.ftl";

    private ModelInfo modelInfo;

    public MapperFileInfo() {
        setTemplateName(DEFAULT_TEMPLATE_NAME);
    }

    public ModelInfo getModelInfo() {
        return modelInfo;
    }

    public void setModelInfo(ModelInfo modelInfo) {
        this.modelInfo = modelInfo;
    }

}
