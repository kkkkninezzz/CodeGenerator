package cn.makisekurisu.codeGenerator.service.main.generator.mapper;

import cn.makisekurisu.codeGenerator.bean.FieldInfo;
import cn.makisekurisu.codeGenerator.bean.ModelInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.CodeFileInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.MapperFileInfo;
import cn.makisekurisu.codeGenerator.config.CodeGeneratorConfig;
import cn.makisekurisu.codeGenerator.config.ConfigLoader;
import cn.makisekurisu.codeGenerator.service.main.generator.AbstractCodeGenerator;
import cn.makisekurisu.util.StringUtil;
import cn.makisekurisu.util.TypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ym on 2017/2/24 0024.
 */
public class MapperGenerator extends AbstractCodeGenerator {
    private static final String DEFAULT_MAPPER_NAME_FORMAT = "%sMapper";

    private static final String DEFAULT_FILE_NAME_FORMAT = "%sMapper.java";

    @Override
    protected boolean whetherToGenerateCode(List<ModelInfo> modelInfos, ConfigLoader configLoader) {
        // 需要生成mapper
        return configLoader.getCodeGeneratorConfig().getMapperFlag() && modelInfos!= null && !modelInfos.isEmpty();
    }

    @Override
    protected List<? extends CodeFileInfo> createCodeFiles(List<ModelInfo> modelInfos, CodeGeneratorConfig generatorConfig) {
        List<MapperFileInfo> mapperFileInfos = new ArrayList<MapperFileInfo>();

        for(ModelInfo modelInfo : modelInfos)
            mapperFileInfos.add(toMapperFileInfo(modelInfo, generatorConfig));

        return mapperFileInfos;
    }

    private MapperFileInfo toMapperFileInfo(ModelInfo modelInfo, CodeGeneratorConfig generatorConfig) {
        MapperFileInfo mapperFileInfo = new MapperFileInfo();

        mapperFileInfo.setPackageName(generatorConfig.getCompleteMapperPackageName());
        mapperFileInfo.setMapperName(String.format(DEFAULT_MAPPER_NAME_FORMAT, modelInfo.getModelName()));
        mapperFileInfo.setFileName(String.format(DEFAULT_FILE_NAME_FORMAT, modelInfo.getModelName()));
        mapperFileInfo.setModelInfo(modelInfo);

        mapperFileInfo.addImportInfo(StringUtil.concatStrs(modelInfo.getPackageName(), DOT_DEL, modelInfo.getModelName()));

        handlePrimaryKeys(mapperFileInfo, modelInfo.getPrimaryKeys());

        return mapperFileInfo;
    }

    // 判断model的主键类型有没有需要import
    private void handlePrimaryKeys(MapperFileInfo mapperFileInfo, List<FieldInfo> primaryKeys) {
        for(FieldInfo primaryKey : primaryKeys) {
            if(!TypeUtil.isInLangPackage(primaryKey.getJavaType()))
                mapperFileInfo.addImportInfo(primaryKey.getJavaType().getTypeName());
        }
    }
}
