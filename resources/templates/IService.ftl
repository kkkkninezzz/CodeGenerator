package ${packageName};

import java.util.List;
import java.util.Map;

<#list importInfos as importInfo>
import ${importInfo};
</#list>

public interface ${iServiceName} {
    void save(${modelInfo.modelName} ${modelInfo.modelName?uncap_first});

    void update(${modelInfo.modelName} ${modelInfo.modelName?uncap_first});

    void delete(${modelInfo.modelName} ${modelInfo.modelName?uncap_first});

    void delete(<#list modelInfo.primaryKeys as primaryKey>${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>);

    ${modelInfo.modelName} findModel(<#list modelInfo.primaryKeys as primaryKey>${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>);

    ${modelInfo.modelName} findModdel(<#list modelInfo.primaryKeys as primaryKey>${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>, String[] parameters);

    Map<String, Object> findMap(<#list modelInfo.primaryKeys as primaryKey>${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>);

    Map<String, Object> findMap(<#list modelInfo.primaryKeys as primaryKey>${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>, String[][] parameters);

    List<${modelInfo.modelName}> loadModels();

    List<${modelInfo.modelName}> loadModels(String[] parameters);

    List<${modelInfo.modelName}> loadModels(String condition, Object[] values);

    List<${modelInfo.modelName}> loadModels(String[] parameters, String condition, Object[] values);

    List<${modelInfo.modelName}> loadModels(int curPage, int limit);

    List<${modelInfo.modelName}> loadModels(String[] parameters, int curPage, int limit);

    List<${modelInfo.modelName}> loadModels(String condition, Object[] values, int curPage, int limit);

    List<${modelInfo.modelName}> loadModels(String[] parameters, String condition, Object[] values, int curPage, int limit);

    List<${modelInfo.modelName}> loadModels(String condition, Object[] values, String order, String sort);

    List<${modelInfo.modelName}> loadModels(String[] parameters, String condition, Object[] values, String order, String sort);

    List<${modelInfo.modelName}> loadModels(String order, String sort, int curPage, int limit);

    List<${modelInfo.modelName}> loadModels(String[] parameters, String order, String sort, int curPage, int limit);

    List<${modelInfo.modelName}> loadModels(String condition, Object[] values, String order, String sort, int curPage, int limit);

    List<${modelInfo.modelName}> loadModels(String[] parameters, String condition, Object[] values, String order, String sort, int curPage, int limit);

    List<Map<String, Object>> loadMaps();

    List<Map<String, Object>> loadMaps(String[][] parameters);

    List<Map<String, Object>> loadMaps(String condition, Object[] values);

    List<Map<String, Object>> loadMaps(String[][] parameters, String condition, Object[] values);

    List<Map<String, Object>> loadMaps(int curPage, int limit);

    List<Map<String, Object>> loadMaps(String[][] parameters, int curPage, int limit);

    List<Map<String, Object>> loadMaps(String condition, Object[] values, int curPage, int limit);

    List<Map<String, Object>> loadMaps(String[][] parameters, String condition, Object[] values, int curPage, int limit);

    List<Map<String, Object>> loadMaps(String condition, Object[] values, String order, String sort);

    List<Map<String, Object>> loadMaps(String[][] parameters, String condition, Object[] values, String order, String sort);

    List<Map<String, Object>> loadMaps(String order, String sort, int curPage, int limit);

    List<Map<String, Object>> loadMaps(String[][] parameters, String order, String sort, int curPage, int limit);

    List<Map<String, Object>> loadMaps(String condition, Object[] values, String order, String sort, int curPage, int limit);

    List<Map<String, Object>> loadMaps(String[][] parameters, String condition, Object[] values, String order, String sort, int curPage, int limit);

    long count();

    long count(String condition, Object[] values);

    long count(String[] parameters, boolean isDistinct);

    long count(String[] parameters, String condition, Object[] values, boolean isDistinct);
}
