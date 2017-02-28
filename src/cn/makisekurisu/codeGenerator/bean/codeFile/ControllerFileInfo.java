package cn.makisekurisu.codeGenerator.bean.codeFile;

import cn.makisekurisu.codeGenerator.bean.ModelInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.classFile.BaseClassFileInfo;

/**
 * Created by ym on 2017/2/28 0028.
 */
public class ControllerFileInfo extends BaseClassFileInfo {
    private static final String DEFAULT_TEMPLATE_NAME = "Controller.ftl";

    private String controllerRequestMapping;

    private String serviceInterfaceName;

    private ModelInfo modelInfo;

    private String dtoName;

    public ControllerFileInfo() {
        setTemplateName(DEFAULT_TEMPLATE_NAME);
    }

    public String getControllerRequestMapping() {
        return controllerRequestMapping;
    }

    public void setControllerRequestMapping(String controllerRequestMapping) {
        this.controllerRequestMapping = controllerRequestMapping;
    }

    public String getServiceInterfaceName() {
        return serviceInterfaceName;
    }

    public void setServiceInterfaceName(String serviceInterfaceName) {
        this.serviceInterfaceName = serviceInterfaceName;
    }

    public ModelInfo getModelInfo() {
        return modelInfo;
    }

    public void setModelInfo(ModelInfo modelInfo) {
        this.modelInfo = modelInfo;
    }

    public String getDtoName() {
        return dtoName;
    }

    public void setDtoName(String dtoName) {
        this.dtoName = dtoName;
    }
}
