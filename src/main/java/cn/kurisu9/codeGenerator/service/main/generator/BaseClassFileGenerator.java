package cn.kurisu9.codeGenerator.service.main.generator;

import cn.kurisu9.codeGenerator.bean.FieldInfo;
import cn.kurisu9.codeGenerator.bean.codeFile.classFile.BaseClassFileInfo;
import cn.kurisu9.util.TypeUtil;

import java.util.List;

/**
 * Created by ym on 2017/2/28 0028.
 */
public abstract class BaseClassFileGenerator extends AbstractCodeGenerator {
    /**
     * 根据model的主键添加import信息
     * */
    // 判断model的主键类型有没有需要import
    protected void handlePrimaryKeys(BaseClassFileInfo baseClassFileInfo, List<FieldInfo> primaryKeys) {
        for(FieldInfo primaryKey : primaryKeys) {
            if(!TypeUtil.isInLangPackage(primaryKey.getJavaType()))
                baseClassFileInfo.addImportInfo(primaryKey.getJavaType().getTypeName());
        }
    }
}
