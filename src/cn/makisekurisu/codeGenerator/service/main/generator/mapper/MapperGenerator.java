package cn.makisekurisu.codeGenerator.service.main.generator.mapper;

import cn.makisekurisu.codeGenerator.bean.ModelInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.CodeFileInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.MapperFileInfo;
import cn.makisekurisu.codeGenerator.config.CodeGeneratorConfig;
import cn.makisekurisu.codeGenerator.config.ConfigLoader;
import cn.makisekurisu.codeGenerator.service.main.generator.BaseClassFileGenerator;
import cn.makisekurisu.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ym on 2017/2/24 0024.
 */
public class MapperGenerator extends BaseClassFileGenerator {
    private static final String DEFAULT_MAPPER_NAME_FORMAT = "%sMapper";

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
        mapperFileInfo.setClassName(formatMapperName(modelInfo.getModelName()));
        mapperFileInfo.setFileName(formatJavaFileName(mapperFileInfo.getClassName()));
        mapperFileInfo.setModelInfo(modelInfo);

        mapperFileInfo.addImportInfo(StringUtil.concatStrs(modelInfo.getPackageName(), DOT_DEL, modelInfo.getModelName()));

        handlePrimaryKeys(mapperFileInfo, modelInfo.getPrimaryKeys());

        return mapperFileInfo;
    }

    public static String formatMapperName(String modelName) {
        return String.format(DEFAULT_MAPPER_NAME_FORMAT, modelName);
    }
}
