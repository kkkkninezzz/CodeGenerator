package cn.makisekurisu.codeGenerator.bean.codeFile;

/**
 * Created by ym on 2017/2/28 0028.
 */
public class LimitShowDTOFileInfo extends CodeFileInfo {
    /**
     * 类名
     * */
    public static final String CLASS_NAME = "LimitShowDTO";

    private static final String DEFAULT_FILE_NAME = CLASS_NAME.concat(".java");

    private static final String DEFAULT_TEMPLATE_NAME = CLASS_NAME.concat(".ftl");

    public LimitShowDTOFileInfo() {
        setFileName(DEFAULT_FILE_NAME);
        setTemplateName(DEFAULT_TEMPLATE_NAME);
    }
}
