<#macro mapperEl value>${r"#{"}${value}}</#macro>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE ${r"mapper"} PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${namespace}" >
    <resultMap id="BaseResultMap" type="${modelInfo.packageName}.${modelInfo.modelName}" >
    <#list modelInfo.fieldInfos as fieldInfo>
    <#if (fieldInfo.isPrimaryKey)>
        <id column="${fieldInfo.dbFieldName}" property="${fieldInfo.fieldName}" jdbcType="${fieldInfo.dbType}" />
    <#else>
        <result column="${fieldInfo.dbFieldName}" property="${fieldInfo.fieldName}" jdbcType="${fieldInfo.dbType}" />
    </#if>
    </#list>
    </resultMap>

    <sql id="Base_Column_List" >
    <#list modelInfo.fieldInfos as fieldInfo>
        ${fieldInfo.dbFieldName}<#if fieldInfo?has_next>,</#if>
    </#list>
    </sql>

    <select id="selectByPrimaryKey" resultMap="BaseResultMap" >
        select
        <include refid="Base_Column_List" />
        from ${modelInfo.dbTableName}
        <#if (modelInfo.primaryKeys?size>0)>
        where
        <#list modelInfo.primaryKeys as primaryKey>
        ${primaryKey.dbFieldName} = <@mapperEl primaryKey.fieldName + "," + "jdbcType=" + primaryKey.dbType />
            <#if primaryKey?has_next> and </#if>
        </#list>
        </#if>
    </select>

    <delete id="deleteByPrimaryKey" >
        delete from ${modelInfo.dbTableName}
        <#if (modelInfo.primaryKeys?size>0)>
        where
        <#list modelInfo.primaryKeys as primaryKey>
        ${primaryKey.dbFieldName} = <@mapperEl primaryKey.fieldName + "," + "jdbcType=" + primaryKey.dbType />
        <#if primaryKey?has_next> and </#if>
        </#list>
        </#if>
    </delete>

    <#-- 如果是单主键则生成批量删除 -->
    <#if (modelInfo.primaryKeys?size==1)>
    <delete id="deleteByPrimaryKeys" >
        delete from ${modelInfo.dbTableName}
        <#list modelInfo.primaryKeys as primaryKey>
        where <foreach collection="primaryKeys" item = "primaryKey" separator=" or " >${primaryKey.dbFieldName} = <@mapperEl "primaryKey," + "jdbcType=" + primaryKey.dbType /></foreach>
        </#list>
    </delete>
    </#if>

    <insert id="insert" parameterType="${modelInfo.packageName}.${modelInfo.modelName}" >
        insert into ${modelInfo.dbTableName} (
        <#list modelInfo.fieldInfos as fieldInfo>
            ${fieldInfo.dbFieldName}<#if fieldInfo?has_next>,</#if>
        </#list>
        )
        values (
        <#list modelInfo.fieldInfos as fieldInfo>
            <@mapperEl fieldInfo.fieldName + "," + "jdbcType=" + fieldInfo.dbType /><#if fieldInfo?has_next>,</#if>
        </#list>
        )
    </insert>

    <insert id="insertSelective" parameterType="${modelInfo.packageName}.${modelInfo.modelName}" >
        insert into ${modelInfo.dbTableName}
        <trim prefix="(" suffix=")" suffixOverrides="," >
        <#list modelInfo.fieldInfos as fieldInfo>
            <if test="${fieldInfo.fieldName} != null" >
                ${fieldInfo.dbFieldName},
            </if>
        </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides="," >
        <#list modelInfo.fieldInfos as fieldInfo>
            <if test="${fieldInfo.fieldName} != null" >
                <@mapperEl fieldInfo.fieldName + "," + "jdbcType=" + fieldInfo.dbType />,
            </if>
        </#list>
        </trim>
    </insert>

    <update id="updateByPrimaryKeySelective" parameterType="${modelInfo.packageName}.${modelInfo.modelName}" >
        update ${modelInfo.dbTableName}
        <set >
            <#list modelInfo.fieldInfos as fieldInfo>
            <if test="${fieldInfo.fieldName} != null" >
                ${fieldInfo.dbFieldName} = <@mapperEl fieldInfo.fieldName + "," + "jdbcType=" + fieldInfo.dbType />,
            </if>
            </#list>
        </set>
        <#if (modelInfo.primaryKeys?size>0)>
            where
            <#list modelInfo.primaryKeys as primaryKey>
            ${primaryKey.dbFieldName} = <@mapperEl primaryKey.fieldName + "," + "jdbcType=" + primaryKey.dbType />
                <#if primaryKey?has_next> and </#if>
            </#list>
        </#if>
    </update>

    <update id="updateByPrimaryKey" parameterType="${modelInfo.packageName}.${modelInfo.modelName}" >
        update ${modelInfo.dbTableName}
        set
        <trim suffixOverrides="," >
            <#list modelInfo.fieldInfos as fieldInfo>
            <#if (!fieldInfo.isPrimaryKey) >
            ${fieldInfo.dbFieldName} = <@mapperEl fieldInfo.fieldName + "," + "jdbcType=" + fieldInfo.dbType />,
            </#if>
            </#list>
        </trim>
        <#if (modelInfo.primaryKeys?size>0)>
        where
        <#list modelInfo.primaryKeys as primaryKey>
        ${primaryKey.dbFieldName} = <@mapperEl primaryKey.fieldName + "," + "jdbcType=" + primaryKey.dbType />
            <#if primaryKey?has_next> and </#if>
        </#list>
        </#if>
    </update>

    <select id="loadModels" resultMap="BaseResultMap">
        select
        <if test="parameters != null">
            ${r"${parameters}"}
        </if>
        <if test="parameters == null">
            *
        </if>
        from ${modelInfo.dbTableName}
        <if test="condition != null">
            where ${r"${condition}"}
        </if>
        <if test="order != null and sort != null">
            order by ${r"${order}"} ${r"${sort}"}
        </if>
        <if test="offset != -1 and limit != -1">
            limit ${r"#{offset,jdbcType=INTEGER}"}, ${r"#{limit,jdbcType=INTEGER}"}
        </if>
    </select>

    <select id="loadMaps" resultType="java.util.HashMap">
        select
        <if test="parameters != null">
         ${r"${parameters}"}
        </if>
        <if test="parameters == null">
            *
        </if>
        from ${modelInfo.dbTableName}
        <if test="condition != null">
            where ${r"${condition}"}
        </if>
        <if test="order != null and sort != null">
            order by ${r"${order}"} ${r"${sort}"}
        </if>
        <if test="offset != -1 and limit != -1">
            limit ${r"#{offset,jdbcType=INTEGER}"}, ${r"#{limit,jdbcType=INTEGER}"}
        </if>
    </select>

    <select id="count" resultType="long">
        select count(
        <if test="isDistinct == true">
            distinct
        </if>
        <if test="parameters != null">
            ${r"${parameters}"}
        </if>
        <if test="parameters == null">
            *
        </if>
        ) from ${modelInfo.dbTableName}
        <if test="condition != null">
            where ${r"${condition}"}
        </if>
    </select>

    <select id="findModel" resultMap="BaseResultMap" >
        select
        <if test="parameters != null">
            ${r"${parameters}"}
        </if>
        <if test="parameters == null">
            *
        </if> from ${modelInfo.dbTableName}
        <#if (modelInfo.primaryKeys?size>0)>
        where
        <#list modelInfo.primaryKeys as primaryKey>
        ${primaryKey.dbFieldName} = <@mapperEl primaryKey.fieldName + "," + "jdbcType=" + primaryKey.dbType />
            <#if primaryKey?has_next> and </#if>
        </#list>
    </#if>
    </select>

    <select id="findMap" resultType="java.util.HashMap" >
        select
        <if test="parameters != null">
            ${r"${parameters}"}
        </if>
        <if test="parameters == null">
            *
        </if> from ${modelInfo.dbTableName}
        <#if (modelInfo.primaryKeys?size>0)>
        where
        <#list modelInfo.primaryKeys as primaryKey>
        ${primaryKey.dbFieldName} = <@mapperEl primaryKey.fieldName + "," + "jdbcType=" + primaryKey.dbType />
            <#if primaryKey?has_next> and </#if>
        </#list>
    </#if>
    </select>
</mapper>