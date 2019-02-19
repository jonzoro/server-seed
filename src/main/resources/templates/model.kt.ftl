package ${package.Entity}

<#list table.importPackages as pkg>
import ${pkg}
</#list>
import com.company.project.common.using
import com.company.project.base.PrimaryKey

/**
 * <p>
 * ${table.comment!} 数据类
 * </p>
 * @author ${author}
 * @since ${date}
 */
<#if table.convert>
@TableName("${table.name}")
</#if>
data class ${entity}(
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>

    <#if field.comment!?length gt 0>
    /**
     * ${field.comment}
     */
    </#if>
    <#if field.propertyName == "id">
    @PrimaryKey
    </#if>
    <#if field.propertyType == "Integer">
    var ${field.propertyName}: Int? = null<#if field_has_next>,</#if>
    <#elseif field.propertyType == "String">
    var ${field.propertyName}: String = ""<#if field_has_next>,</#if>
    <#else>
    var ${field.propertyName}: ${field.propertyType}? = null<#if field_has_next>,</#if>
    </#if>
</#list>
<#-- ----------  END 字段循环遍历  ---------->
) : Base(), IVerify {
<#list table.fields as field>
    <#if field.propertyType == "Date">
    var ${field.propertyName}Begin: ${field.propertyType}? = null
    var ${field.propertyName}End: ${field.propertyType}? = null
    </#if>
</#list>

    override fun verify() {
    <#list table.fields as field>
        <#if field.propertyType == "String">
        "${field.comment!} 不能为空" using (${field.propertyName}.isNotEmpty())
        <#else>
        "${field.comment!} 不能为空" using (${field.propertyName} != null)
        </#if>
    </#list>
    }
}


