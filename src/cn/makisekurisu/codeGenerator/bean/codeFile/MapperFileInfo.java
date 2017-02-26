package cn.makisekurisu.codeGenerator.bean.codeFile;

import cn.makisekurisu.codeGenerator.bean.ModelInfo;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ym on 2017/2/24 0024.
 */
public class MapperFileInfo extends CodeFileInfo {
    private static final String DEFAULT_TEMPLATE_NAME = "Mapper.ftl";

    /**
     * import信息
     * */
    private Set<String> importInfos = new HashSet<String>();

    private String mapperName;

    private ModelInfo modelInfo;

    public MapperFileInfo() {
        setTemplateName(DEFAULT_TEMPLATE_NAME);
    }

    public Set<String> getImportInfos() {
        return importInfos;
    }

    public void setImportInfos(Set<String> importInfos) {
        this.importInfos = importInfos;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
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
