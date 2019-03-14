package ${package.ServiceImpl}

import ${package.Entity}.${entity}
import ${package.Mapper}.${table.mapperName}
import ${package.Service}.${table.serviceName}
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import com.github.pagehelper.Page
import com.company.project.base.AuthException
import com.company.project.base.IdNotFoundException
import com.company.project.base.loggerFor
import com.company.project.common.getSessionUser

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
class ${table.serviceImplName} : ${table.serviceName} {

    val log = loggerFor(this.javaClass)

    @Autowired
    lateinit var ${table.mapperName?uncap_first}: ${table.mapperName}

    override fun get${entity}(id: String): ${entity}? {
        return ${table.mapperName?uncap_first}.get(id) ?: throw IdNotFoundException()
    }

    override fun getAll${entity}(${entity?uncap_first}: ${entity}): List<${entity}> {
        <#list table.fields as field>
        </#list>
        return ${table.mapperName?uncap_first}.getAll(${entity?uncap_first})
    }

    override fun getAll${entity}ByPage(${entity?uncap_first}: ${entity}): Page<${entity}> {
        <#list table.fields as field>
        </#list>
        return ${table.mapperName?uncap_first}.getAllByPage(${entity?uncap_first})
    }

    override fun insert${entity}(${entity?uncap_first}: ${entity}) {
        ${table.mapperName?uncap_first}.insert(${entity?uncap_first})
    }

    override fun update${entity}(${entity?uncap_first}: ${entity}) {
        ${table.mapperName?uncap_first}.update(${entity?uncap_first})
    }

    override fun delete${entity}(id: String) {
        ${table.mapperName?uncap_first}.delete(id)
    }

}
