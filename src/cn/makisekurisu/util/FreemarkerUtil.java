package cn.makisekurisu.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by ym on 2017/2/22 0022.
 */
public class FreemarkerUtil {
    private static final String TEMPLATE_DIR = "resources/templates";

    private static final Configuration configuration;

    static {
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        init();
    }

    private static void init() {
        try {
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_DIR));
            configuration.setDefaultEncoding("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("加载freemarker模板失败");
        }
    }

    public static boolean processTemplate(String templateName, Object dataModel, Writer out) {
        try {
            Template template = configuration.getTemplate(templateName);
            template.process(dataModel, out);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        return false;
    }
}
