package ${packageName};

import java.util.List;
import java.util.Map;

<#list importInfos as importInfo>
import ${importInfo};
</#list>

public interface ${className} {
    boolean save(${modelInfo.modelName} ${modelInfo.modelName?uncap_first});

    boolean update(${modelInfo.modelName} ${modelInfo.modelName?uncap_first});

    boolean delete(<#list modelInfo.primaryKeys as primaryKey>${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>);

    <#-- 如果是单主键则生成批量删除 -->
    <#if (modelInfo.primaryKeys?size==1)>
    boolean batchDelete(<#list modelInfo.primaryKeys as primaryKey>${primaryKey.javaType.simpleName}[] primaryKeys</#list>);
    </#if>

    ${modelInfo.modelName} findModel(<#list modelInfo.primaryKeys as primaryKey>${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>);

    ${modelInfo.modelName} findModel(<#if (modelInfo.primaryKeys?size>0)><#list modelInfo.primaryKeys as primaryKey>${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>, </#if>String[] parameters);

    Map<String, Object> findMap(<#list modelInfo.primaryKeys as primaryKey>${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>);

    Map<String, Object> findMap(<#if (modelInfo.primaryKeys?size>0)><#list modelInfo.primaryKeys as primaryKey>${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>, </#if>String[][] parameters);

    List<${modelInfo.modelName}> loadModels();

    List<${modelInfo.modelName}> loadModels(String condition, Object[] values, String order, String sort, int curPage, int limit);

    List<${modelInfo.modelName}> loadModels(String[] parameters, String condition, Object[] values, String order, String sort, int curPage, int limit);

    List<Map<String, Object>> loadMaps();

    List<Map<String, Object>> loadMaps(String condition, Object[] values, String order, String sort, int curPage, int limit);

    List<Map<String, Object>> loadMaps(String[][] parameters, String condition, Object[] values, String order, String sort, int curPage, int limit);

    long count();

    long count(String condition, Object[] values);

    long count(String[] parameters, boolean isDistinct);

    long count(String[] parameters, String condition, Object[] values, boolean isDistinct);
}
