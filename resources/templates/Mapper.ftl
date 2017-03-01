package ${packageName};

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

<#list importInfos as importInfo>
import ${importInfo};
</#list>

public interface ${className} {
    int deleteByPrimaryKey(<#list modelInfo.primaryKeys as primaryKey>@Param("${primaryKey.fieldName}") ${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>);

    <#-- 如果是单主键则生成批量删除 -->
    <#if (modelInfo.primaryKeys?size==1)>
    int deleteByPrimaryKeys(@Param("primaryKeys")<#list modelInfo.primaryKeys as primaryKey> ${primaryKey.javaType.simpleName}[] primaryKeys</#list>);
    </#if>

    int insert(${modelInfo.modelName} record);

    int insertSelective(${modelInfo.modelName} record);

    ${modelInfo.modelName} selectByPrimaryKey(<#list modelInfo.primaryKeys as primaryKey>@Param("${primaryKey.fieldName}") ${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>);

    int updateByPrimaryKeySelective(${modelInfo.modelName} record);

    int updateByPrimaryKey(${modelInfo.modelName} record);

    List<${modelInfo.modelName}> loadModels(@Param("parameters") String parameters, @Param("condition") String condition, @Param("order") String order, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit);

    List<Map<String, Object>> loadMaps(@Param("parameters") String parameters, @Param("condition") String condition, @Param("order") String order, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit);

    long count(@Param("parameters") String parameters, @Param("condition") String condition, @Param("isDistinct") boolean isDistinct);

    ${modelInfo.modelName} findModel(<#if (modelInfo.primaryKeys?size>0)><#list modelInfo.primaryKeys as primaryKey>@Param("${primaryKey.fieldName}") ${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>, </#if>@Param("parameters") String parameters);

    Map<String, Object> findMap(<#if (modelInfo.primaryKeys?size>0)><#list modelInfo.primaryKeys as primaryKey>@Param("${primaryKey.fieldName}") ${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>, </#if>@Param("parameters") String parameters);
}
