<#assign serviceName=(serviceInterfaceName?substring(1))?uncap_first />
package ${packageName};

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
<#if (modelInfo.primaryKeys?size==1)>
import org.springframework.web.bind.annotation.RequestParam;
</#if>

<#list importInfos as importInfo>
import ${importInfo};
</#list>


@RestController
@RequestMapping("/${controllerRequestMapping}")
public class ${className} {
    @Resource(name="${serviceName}")
    private ${serviceInterfaceName} ${serviceName};

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<String> list(
        @Validated
        LimitShowDTO limitShowDTO, BindingResult errors) {
        if(errors.hasErrors())
            return ResponseUtil.getResponseEntityWhenInvalidReqParams();

        Map<String, Object> result = new HashMap<String, Object>();

        List<Map<String, Object>> ${modelInfo.modelName?uncap_first}List = ${serviceName}.loadMaps(null, null, null, null, limitShowDTO.getCurPage(), limitShowDTO.getLimit());
        long count = ${serviceName}.count();

        result.put("rows", ${modelInfo.modelName?uncap_first}List);
        result.put("total", count);

        return ResponseUtil.getResEntityForGetAndJson(result);
    }

    @RequestMapping(value = "<#if (modelInfo.primaryKeys?size>0)>/<#list modelInfo.primaryKeys as primaryKey>{${primaryKey.fieldName}}<#if primaryKey?has_next>/</#if></#list></#if>", method = RequestMethod.GET)
    public ResponseEntity<String> show(<#list modelInfo.primaryKeys as primaryKey>@PathVariable("${primaryKey.fieldName}") ${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>) {
        Map<String, Object> ${modelInfo.modelName?uncap_first}Model = ${serviceName}.findMap(<#list modelInfo.primaryKeys as primaryKey>${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>);

        return ResponseUtil.getResEntityForGetAndJson(${modelInfo.modelName?uncap_first}Model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> create(
        @Validated
        ${dtoName} ${dtoName?uncap_first}, BindingResult errors) {
        if(errors.hasErrors())
            return ResponseUtil.getResponseEntityWhenInvalidReqParams();

        ${modelInfo.modelName} ${modelInfo.modelName?uncap_first}Model = ${dtoName?uncap_first}.toModel();

        return ResponseUtil.getResEntityForPPP(${serviceName}.save(${modelInfo.modelName?uncap_first}Model));
    }

    @RequestMapping(value = "<#if (modelInfo.primaryKeys?size>0)>/<#list modelInfo.primaryKeys as primaryKey>{${primaryKey.fieldName}}<#if primaryKey?has_next>/</#if></#list></#if>", method = RequestMethod.PUT)
    public ResponseEntity<String> update(<#if (modelInfo.primaryKeys?size>0)><#list modelInfo.primaryKeys as primaryKey>@PathVariable("${primaryKey.fieldName}") ${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>,</#if>
        @Validated
        ${dtoName} ${dtoName?uncap_first}, BindingResult errors) {

        ${modelInfo.modelName} ${modelInfo.modelName?uncap_first}Model = ${dtoName?uncap_first}.toModel();
        <#list modelInfo.primaryKeys as primaryKey>
        ${modelInfo.modelName?uncap_first}Model.set${primaryKey.fieldName?cap_first}(${primaryKey.fieldName});
        </#list>
        return ResponseUtil.getResEntityForPPP(${serviceName}.update(${modelInfo.modelName?uncap_first}Model));
    }

    @RequestMapping(value = "<#if (modelInfo.primaryKeys?size>0)>/<#list modelInfo.primaryKeys as primaryKey>{${primaryKey.fieldName}}<#if primaryKey?has_next>/</#if></#list></#if>", method = RequestMethod.DELETE)
    public ResponseEntity<String> destory(<#list modelInfo.primaryKeys as primaryKey>@PathVariable("${primaryKey.fieldName}") ${primaryKey.javaType.simpleName} ${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>) {
        return ResponseUtil.getResEntityForDel(${serviceName}.delete(<#list modelInfo.primaryKeys as primaryKey>${primaryKey.fieldName}<#if primaryKey?has_next>, </#if></#list>));
    }

    <#-- 如果是单主键则生成批量删除 -->
    <#if (modelInfo.primaryKeys?size==1)>
    @RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletes(@RequestParam("primaryKeys") <#list modelInfo.primaryKeys as primaryKey>${primaryKey.javaType.simpleName}[] primaryKeys</#list>) {
        return ResponseUtil.getResEntityForDel(${serviceName}.batchDelete(primaryKeys));
    }
    </#if>
}
