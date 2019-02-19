<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

    <select id="get" resultType="${entity}">
        select <#list table.fields as field>b.${field.name},</#list><#list table.commonFields as field>b.${field.name}<#if field_has_next>,</#if></#list>
        from ${table.name} b
        where b.id =${r"#{id}"}
    </select>

    <select id="getAll" resultType="${entity}">
        select <#list table.fields as field>b.${field.name}<#if field_has_next>,</#if></#list>
        from ${table.name} b
        <where>
        <#list table.fields as field>
            <if test="${field.propertyName} != null  and ${field.propertyName} != '' ">
                and b.${field.name}= ${r"#{"}${field.propertyName}${r"}"}
            </if>
        <#if field.propertyType == "Date">
            <if test="${field.propertyName}Begin != null ">
                <![CDATA[and b.${field.name}  >= ${r"#{"}${field.propertyName}Begin${r"}"}]]>
            </if>
            <if test="${field.propertyName}End != null  ">
                <![CDATA[and b.${field.name}  <= ${r"#{"}${field.propertyName}End${r"}"}]]>
            </if>
        </#if>
        </#list>
        </where>
        <if test="orderByInfo != null and orderByInfo.length > 0">
            <foreach collection="orderByInfo" item="order" open="order by "
                     separator="," close="  ">
                ${r"${order}"}
            </foreach>
        </if>
    </select>

    <select id="getAllByPage" resultType="${entity}">
        select <#list table.fields as field>b.${field.name},</#list><#list table.commonFields as field>b.${field.name}<#if field_has_next>,</#if></#list>
        from ${table.name} b
        <where>
        <#list table.fields as field>
          <if test="${field.propertyName} != null  and ${field.propertyName} != '' ">
              and b.${field.name}= ${r"#{"}${field.propertyName}${r"}"}
          </if>
        <#if field.propertyType == "Date">
            <if test="${field.propertyName}Begin != null ">
                <![CDATA[and b.${field.name}  >= ${r"#{"}${field.propertyName}Begin${r"}"}]]>
            </if>
            <if test="${field.propertyName}End != null ">
                <![CDATA[and b.${field.name}  <= ${r"#{"}${field.propertyName}End${r"}"}]]>
            </if>
        </#if>
        </#list>
        </where>
        <if test="orderByInfo != null and orderByInfo.length > 0">
            <foreach collection="orderByInfo" item="order" open="order by "
                     separator="," close="  ">
                ${r"${order}"}
            </foreach>
        </if>
    </select>

    <insert id="insert" parameterType="${entity}">
        INSERT INTO ${table.name} (<#list table.fields as field>${field.name},</#list><#list table.commonFields as field>${field.name}<#if field_has_next>,</#if></#list>)
        VALUES (<#list table.fields as field>${r"#{"}${field.propertyName}${r"}"},</#list><#list table.commonFields as field><#if field.name == "create_date" || field.name == "update_date">NOW()<#elseif field.name == "update_user">${r"#{createUser}"}<#else>${r"#{"}${field.propertyName}${r"}"}</#if><#if field_has_next>,</#if></#list>);
    </insert>

    <update id="update" parameterType="${entity}">
        update ${table.name}
        <set>
            <#list table.fields as field>
            <if test="${field.propertyName} != null  and ${field.propertyName} != '' ">
                ${field.name}= ${r"#{"}${field.propertyName}${r"}"},
            </if>
            </#list>
            <if test="updateUser != null and updateUser != ''">
                update_user=${r"#{updateUser}"},
            </if>
            update_date=NOW()
        </set>
        where id=${r"#{id}"}
    </update>

    <delete id="delete" parameterType="java.lang.String">
        delete from ${table.name} where id=${r"#{id}"}
    </delete>

</mapper>
