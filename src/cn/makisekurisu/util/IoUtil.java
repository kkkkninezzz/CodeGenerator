package cn.makisekurisu.util;

import java.io.Closeable;
import java.io.File;

/**
 * Created by ym on 2017/2/22 0022.
 */
public class IoUtil {
    private IoUtil() {
    }

    /**
     * 获取文件后缀名
     * */
    public static String getFileSuffix(String filePath) {
        File f = new File(filePath);
        return getFileSuffix(f);
    }

    /**
     * 获取文件后缀名
     * */
    public static String getFileSuffix(File filePath) {
        if (filePath == null || !filePath.exists())
            return "";
        String fileName = filePath.getName();

        if (fileName.equals("") || fileName.indexOf(".") < 0
                || fileName.indexOf(".") == fileName.length())
            return "";

        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 创建父目录，如果目录存在则返回false，创建成功返回true否则返回false
     * */
    public static boolean createParentDirectory(File filePath) {
        if(filePath == null)
            return false;

        return filePath.getParentFile().mkdirs();
    }

    /**
     * 创建父目录，如果目录存在则返回false，创建成功返回true否则返回false
     * */
    public static boolean createParentDirectory(String filePath) {
        if(filePath == null || filePath.equals(""))
            return false;

        File f = new File(filePath);

        return f.getParentFile().mkdirs();
    }

    /**
     * 关闭对象，如果该对象正常关闭或者为null，返回true。否则返回false。
     * */
    public static boolean close(Closeable closeable) {
        if(closeable != null) {
            try{
                closeable.close();
            }catch(Exception e) {
                return false;
            }
        }

        return true;
    }

}
