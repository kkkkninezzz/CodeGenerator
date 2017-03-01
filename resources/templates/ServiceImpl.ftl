package ${packageName};

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

<#list importInfos as importInfo>
import ${importInfo};
</#list>

@Service
public class ${className} implements ${serviceInterfaceName} {
    @Resource(name="${mapperName?uncap_first}")
    private ${mapperName} ${mapperName?uncap_first};

    @Override
    public boolean save(${modelInfo.modelName} ${modelInfo.modelName?uncap_first}) {
        return ${mapperName?uncap_first}.insert(${modelInfo.modelName?uncap_first}) > 0;
    }

    @Override
    public boolean update(${modelInfo.modelName} ${modelInfo.modelName?uncap_first}) {
        return ${mapperName?uncap_first}.updateByPrimaryKeySelective(${modelInfo.modelName?uncap_first}) >= 0;
    }

    @Override
    public boolean delete(<#list modelInfo.primaryKeys as primaryKey>${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>) {
        return ${mapperName?uncap_first}.deleteByPrimaryKey(<#list modelInfo.primaryKeys as primaryKey>${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>) > 0;
    }

    <#-- 如果是单主键则生成批量删除 -->
    <#if (modelInfo.primaryKeys?size==1)>
    @Override
    public boolean batchDelete(<#list modelInfo.primaryKeys as primaryKey>${primaryKey.javaType.simpleName}[] primaryKeys</#list>) {
        return ${mapperName?uncap_first}.deleteByPrimaryKeys(primaryKeys) > 0;
    }
    </#if>

    @Override
    public ${modelInfo.modelName} findModel(<#list modelInfo.primaryKeys as primaryKey>${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>) {
        return ${mapperName?uncap_first}.selectByPrimaryKey(<#list modelInfo.primaryKeys as primaryKey>${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>);
    }

    @Override
    public ${modelInfo.modelName} findModel(<#if (modelInfo.primaryKeys?size>0)><#list modelInfo.primaryKeys as primaryKey>${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>, </#if>String[] parameters) {
        return ${mapperName?uncap_first}.findModel(<#if (modelInfo.primaryKeys?size>0)><#list modelInfo.primaryKeys as primaryKey>${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>, </#if>SqlUtil.formatParameters(parameters));
    }

    public Map<String, Object> findMap(<#list modelInfo.primaryKeys as primaryKey>${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>) {
        String[][] parameters = {<#list modelInfo.fieldInfos as fieldInfo>{"${fieldInfo.dbFieldName}", "${fieldInfo.fieldName}"}<#if fieldInfo?has_next>, </#if></#list>};
        return this.findMap(<#if (modelInfo.primaryKeys?size>0)><#list modelInfo.primaryKeys as primaryKey>${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>, </#if>parameters);
    }

    public Map<String, Object> findMap(<#if (modelInfo.primaryKeys?size>0)><#list modelInfo.primaryKeys as primaryKey>${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>, </#if>String[][] parameters) {
        return ${mapperName?uncap_first}.findMap(<#if (modelInfo.primaryKeys?size>0)><#list modelInfo.primaryKeys as primaryKey>${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>, </#if>SqlUtil.formatParametersForAlias(parameters));
    }

    @Override
    public List<${modelInfo.modelName}> loadModels() {
        return this.loadModels(null, null, null, null, -1, -1);
    }

    @Override
    public List<${modelInfo.modelName}> loadModels(String condition, Object[] values, String order, String sort, int curPage, int limit) {
        return this.loadModels(null, condition, values, order, sort, curPage, limit);
    }

    @Override
    public List<${modelInfo.modelName}> loadModels(String[] parameters, String condition, Object[] values, String order, String sort, int curPage, int limit) {
        return ${mapperName?uncap_first}.loadModels(SqlUtil.formatParameters(parameters), SqlUtil.fillCondition(condition, values), order, sort, SqlUtil.getOffset(curPage, limit), limit);
    }

    @Override
    public List<Map<String, Object>> loadMaps() {
        String[][] parameters = {<#list modelInfo.fieldInfos as fieldInfo>{"${fieldInfo.dbFieldName}", "${fieldInfo.fieldName}"}<#if fieldInfo?has_next>, </#if></#list>};
        return this.loadMaps(parameters, null, null, null, null, -1, -1);
    }

    @Override
    public List<Map<String, Object>> loadMaps(String condition, Object[] values, String order, String sort, int curPage, int limit) {
        String[][] parameters = {<#list modelInfo.fieldInfos as fieldInfo>{"${fieldInfo.dbFieldName}", "${fieldInfo.fieldName}"}<#if fieldInfo?has_next>, </#if></#list>};
        return this.loadMaps(parameters, condition, values, order, sort, curPage, limit);
    }

    @Override
    public List<Map<String, Object>> loadMaps(String[][] parameters, String condition, Object[] values, String order, String sort, int curPage, int limit) {
        return ${mapperName?uncap_first}.loadMaps(SqlUtil.formatParametersForAlias(parameters), SqlUtil.fillCondition(condition, values), order, sort, SqlUtil.getOffset(curPage, limit), limit);
    }

    @Override
    public long count() {
        return this.count(null, false);
    }

    @Override
    public long count(String condition, Object[] values) {
        return this.count(null, condition, values, false);
    }

    @Override
    public long count(String[] parameters, boolean isDistinct) {
        return this.count(parameters, null, null, isDistinct);
    }

    @Override
    public long count(String[] parameters, String condition, Object[] values, boolean isDistinct) {
        return ${mapperName?uncap_first}.count(SqlUtil.formatParameters(parameters), SqlUtil.fillCondition(condition, values), isDistinct);
    }

}
