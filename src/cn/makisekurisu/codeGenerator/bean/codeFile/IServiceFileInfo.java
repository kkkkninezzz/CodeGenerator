package cn.makisekurisu.codeGenerator.bean.codeFile;

import cn.makisekurisu.codeGenerator.bean.ModelInfo;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ym on 2017/2/27 0027.
 */
public class IServiceFileInfo extends CodeFileInfo {
    private static final String DEFAULT_TEMPLATE_NAME = "IService.ftl";

    /**
     * import信息
     * */
    private Set<String> importInfos = new HashSet<String>();

    private String iServiceName;

    private ModelInfo modelInfo;

    public IServiceFileInfo() {
        setTemplateName(DEFAULT_TEMPLATE_NAME);
    }

    public Set<String> getImportInfos() {
        return importInfos;
    }

    public void setImportInfos(Set<String> importInfos) {
        this.importInfos = importInfos;
    }

    public String getiServiceName() {
        return iServiceName;
    }

    public void setiServiceName(String iServiceName) {
        this.iServiceName = iServiceName;
    }

    public ModelInfo getModelInfo() {
        return modelInfo;
    }

    public void setModelInfo(ModelInfo modelInfo) {
        this.modelInfo = modelInfo;
    }

    public void addImportInfo(String importInfo) {
        importInfos.add(importInfo);
    }

    public void removeImportInfo(String importInfo) {
        importInfos.remove(importInfo);
    }
}
