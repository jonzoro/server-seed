package ${package.Service}

import ${package.Entity}.${entity}
import com.github.pagehelper.Page

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
interface ${table.serviceName}{
    fun get${entity}(id: String): ${entity}?
    fun getAll${entity}(${entity?uncap_first}: ${entity}): List<${entity}>
    fun getAll${entity}ByPage(${entity?uncap_first}: ${entity}): Page<${entity}>
    fun insert${entity}(${entity?uncap_first}: ${entity})
    fun update${entity}(${entity?uncap_first}: ${entity})
    fun delete${entity}(id: String)
}
