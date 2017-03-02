package ${packageName};

<#list importInfos as importInfo>
import ${importInfo};
</#list>

<#if comment?exists && comment != "">/**${comment}*/</#if>
<#list annotations as annotation>
${annotation}
</#list>
<#if accessControlModifier?exists>${accessControlModifier} </#if><#if nonAccessControlModifier?exists>${nonAccessControlModifier} </#if>class <#if className?exists>${className}</#if><#if extendsClassName?exists> extends ${extendsClassName}</#if><#if (interfaces?size>0)> implements<#list interfaces as interface> ${interface}<#if (interface?has_next)>,</#if></#list></#if> {
    <#list variableInfos as variableInfo>
        <#if (variableInfo.comment?exists) && (variableInfo.comment != "")>/**${variableInfo.comment}*/</#if>
        <#list variableInfo.annotations as annotation>
        ${annotation}
        </#list>
        <#if (variableInfo.accessControlModifier?exists)>${variableInfo.accessControlModifier} </#if><#if (variableInfo.isStatic)>static </#if><#if (variableInfo.nonAccessControlModifier?exists)>${variableInfo.nonAccessControlModifier} </#if>${variableInfo.type} ${variableInfo.name}<#if (variableInfo.value?exists)> = ${variableInfo.value}</#if>;
    </#list>
    <#list methodInfos as methodInfo>
        <#if (methodInfo.comment?exists) && (methodInfo.comment != "")>/**${methodInfo.comment}*/</#if>
        <#list methodInfo.annotations as annotation>
        ${annotation}
        </#list>
        <#if (methodInfo.accessControlModifier?exists)>${methodInfo.accessControlModifier} </#if><#if (methodInfo.isStatic)>static </#if><#list methodInfo.nonAccessControlModifiers as nonAccessControlModifier>${nonAccessControlModifier} </#list><#if (methodInfo.type?exists)>${methodInfo.type} </#if>${methodInfo.name}(<#list methodInfo.arguments as argument>${argument}<#if argument?has_next>, </#if></#list>)<#if (methodInfo.throwsExceptions?size>0)> throws<#list methodInfo.throwsExceptions as throwsException> ${throwsException}<#if throwsException?has_next>,</#if></#list></#if> {
            <#if (methodInfo.methodBody?exists)>${methodInfo.methodBody}</#if>
        }
    </#list>
    <#if customContent?exists>${customContent}</#if>
}