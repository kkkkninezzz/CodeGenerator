package cn.makisekurisu.codeGenerator.bean.codeFile;

/**
 * Created by ym on 2017/2/22 0022.
 */
public class CodeFileInfo {
    /**
     * 包名
     * */
    protected String packageName;

    /**
     * 文件名
     * */
    protected String fileName;

    /**
     * 使用的模板名称
     * */
    protected String templateName;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
