package cn.kurisu9.codeGenerator.bean.codeFile;

import cn.kurisu9.codeGenerator.bean.ModelInfo;
import cn.kurisu9.codeGenerator.bean.codeFile.classFile.BaseClassFileInfo;

/**
 * Created by ym on 2017/2/28 0028.
 */
public class ServiceImplFileInfo extends BaseClassFileInfo {
    private static final String DEFAULT_TEMPLATE_NAME = "ServiceImpl.ftl";

    private String serviceInterfaceName;

    private String mapperName;

    private ModelInfo modelInfo;

    public ServiceImplFileInfo() {
        setTemplateName(DEFAULT_TEMPLATE_NAME);
    }

    public ModelInfo getModelInfo() {
        return modelInfo;
    }

    public void setModelInfo(ModelInfo modelInfo) {
        this.modelInfo = modelInfo;
    }

    public String getServiceInterfaceName() {
        return serviceInterfaceName;
    }

    public void setServiceInterfaceName(String serviceInterfaceName) {
        this.serviceInterfaceName = serviceInterfaceName;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }
}
