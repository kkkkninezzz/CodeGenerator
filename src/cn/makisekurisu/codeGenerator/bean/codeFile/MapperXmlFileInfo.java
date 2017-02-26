package cn.makisekurisu.codeGenerator.bean.codeFile;

import cn.makisekurisu.codeGenerator.bean.ModelInfo;

/**
 * Created by ym on 2017/2/24 0024.
 */
public class MapperXmlFileInfo extends CodeFileInfo {
    private static final String DEFAULT_TEMPLATE_NAME = "MapperXml.ftl";

    private ModelInfo modelInfo;

    private String namespace;

    public MapperXmlFileInfo() {
        setTemplateName(DEFAULT_TEMPLATE_NAME);
    }

    public ModelInfo getModelInfo() {
        return modelInfo;
    }

    public void setModelInfo(ModelInfo modelInfo) {
        this.modelInfo = modelInfo;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
