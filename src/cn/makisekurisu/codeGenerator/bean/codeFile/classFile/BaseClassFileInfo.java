package cn.makisekurisu.codeGenerator.bean.codeFile.classFile;

import cn.makisekurisu.codeGenerator.bean.codeFile.CodeFileInfo;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ym on 2017/2/28 0028.
 *
 * 基础的类文件信息
 */
public class BaseClassFileInfo extends CodeFileInfo {
    /**
     * import信息
     * */
    private Set<String> importInfos = new HashSet<String>();

    /**
     * 类名
     * */
    private String className;

    public Set<String> getImportInfos() {
        return importInfos;
    }

    public void setImportInfos(Set<String> importInfos) {
        this.importInfos = importInfos;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void addImportInfo(String importInfo) {
        importInfos.add(importInfo);
    }

    public void removeImportInfo(String importInfo) {
        importInfos.remove(importInfo);
    }
}
