package cn.makisekurisu.codeGenerator.service.main.generator.mapperXml;

import cn.makisekurisu.codeGenerator.bean.ModelInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.CodeFileInfo;
import cn.makisekurisu.codeGenerator.bean.codeFile.MapperXmlFileInfo;
import cn.makisekurisu.codeGenerator.config.CodeGeneratorConfig;
import cn.makisekurisu.codeGenerator.config.ConfigLoader;
import cn.makisekurisu.codeGenerator.service.main.generator.AbstractCodeGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ym on 2017/2/24 0024.
 */
public class MapperXmlGenerator extends AbstractCodeGenerator {
    private static final String DEFAULT_FILE_NAME_FORMAT = "%sMapper.xml";

    private static final String DEFAULT_NAMESPACE_FORMAT = "%s.%sMapper";

    @Override
    protected boolean whetherToGenerateCode(List<ModelInfo> modelInfos, ConfigLoader configLoader) {
        // 需要生成mapperXml
        return configLoader.getCodeGeneratorConfig().getMapperXmlflag() && modelInfos!= null && !modelInfos.isEmpty();
    }

    @Override
    protected List<? extends CodeFileInfo> createCodeFiles(List<ModelInfo> modelInfos, CodeGeneratorConfig generatorConfig) {
        List<MapperXmlFileInfo> mapperXmlFileInfos = new ArrayList<MapperXmlFileInfo>();

        for(ModelInfo modelInfo : modelInfos)
            mapperXmlFileInfos.add(toMapperXmlFileInfo(modelInfo, generatorConfig));

        return mapperXmlFileInfos;
    }

    private MapperXmlFileInfo toMapperXmlFileInfo(ModelInfo modelInfo, CodeGeneratorConfig generatorConfig) {
        MapperXmlFileInfo mapperXmlFileInfo = new MapperXmlFileInfo();

        mapperXmlFileInfo.setPackageName(generatorConfig.getCompleteMapperXmlPackageName());
        mapperXmlFileInfo.setFileName(String.format(DEFAULT_FILE_NAME_FORMAT, modelInfo.getModelName()));
        mapperXmlFileInfo.setModelInfo(modelInfo);

        // 设置mapper对应的dao所在的命名空间
        mapperXmlFileInfo.setNamespace(String.format(DEFAULT_NAMESPACE_FORMAT, generatorConfig.getCompleteMapperPackageName(), modelInfo.getModelName()));

        return mapperXmlFileInfo;
    }
}
