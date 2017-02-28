package cn.makisekurisu.codeGenerator.bean.codeFile;

/**
 * Created by ym on 2017/2/28 0028.
 */
public class ResponseUtilFileInfo extends CodeFileInfo {
    /**
     * 类名
     * */
    public static final String CLASS_NAME = "ResponseUtil";

    private static final String DEFAULT_FILE_NAME = CLASS_NAME.concat(".java");

    private static final String DEFAULT_TEMPLATE_NAME = CLASS_NAME.concat(".ftl");

    public ResponseUtilFileInfo() {
        setFileName(DEFAULT_FILE_NAME);
        setTemplateName(DEFAULT_TEMPLATE_NAME);
    }
}
