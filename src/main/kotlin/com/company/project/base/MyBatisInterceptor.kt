package com.company.project.base

import com.github.pagehelper.PageInterceptor
import com.google.common.base.CaseFormat
import com.company.project.common.UUIDUtil
import com.company.project.common.getSessionUser
import com.company.project.model.Base
import org.apache.ibatis.cache.CacheKey
import org.apache.ibatis.executor.Executor
import org.apache.ibatis.mapping.BoundSql
import org.apache.ibatis.mapping.MappedStatement
import org.apache.ibatis.mapping.SqlCommandType
import org.apache.ibatis.plugin.*
import org.apache.ibatis.session.ResultHandler
import org.apache.ibatis.session.RowBounds
import java.lang.reflect.Field
import java.util.*


/**
 * 插入及更新的拦截器，主要是更新主键及用户id
 * Created by Jonz on 2018/12/25.
 */
@Intercepts(
    Signature(type = Executor::class, method = "update", args = arrayOf(MappedStatement::class, Any::class))
)
class InsertInterceptor : Interceptor {
    override fun intercept(invocation: Invocation): Any {

        val mappedStatement = invocation.args[0] as MappedStatement

        // 获取 SQL 命令
        val sqlCommandType = mappedStatement.sqlCommandType

        // 获取参数
        val parameter = invocation.args[1]

        // 获取私有成员变量
        val declaredFields = mutableListOf<Field>()
        var tempClass = parameter.javaClass
        while (true) {
            declaredFields.addAll(tempClass.declaredFields)
            tempClass = tempClass.superclass
            if (tempClass == Base::class.java) {
                declaredFields.addAll(tempClass.declaredFields)
                break
            } else if (tempClass == Object::class.java) {
                break
            }
        }

        for (field in declaredFields) {
            if (field.getAnnotation(CreateUser::class.java) != null) {
                if (SqlCommandType.INSERT == sqlCommandType) { // insert 语句插入 createUser
                    val user = getSessionUser()
                    user?.apply {
                        field.isAccessible = true
                        field.set(parameter, user.id)
                    }
                }
            }

            if (field.getAnnotation(UpdateUser::class.java) != null) { // insert 或 update 语句插入 updateUser
                if (SqlCommandType.INSERT == sqlCommandType || SqlCommandType.UPDATE == sqlCommandType) {
                    val user = getSessionUser()
                    user?.apply {
                        field.isAccessible = true
                        field.set(parameter, user.id)
                    }
                }
            }

            if (field.getAnnotation(PrimaryKey::class.java) != null) { // insert 语句插入 主键
                field.isAccessible = true
                val id = field.get(parameter)
                if (id is String) {
                    if (id.isEmpty()) {
                        if (SqlCommandType.INSERT == sqlCommandType) {
                            field.set(parameter, UUIDUtil.uuid)
                        }
                    }
                }
            }
        }

        return invocation.proceed()
    }

    override fun setProperties(properties: Properties?) {}

    override fun plugin(target: Any?): Any {
        return Plugin.wrap(target, this)
    }

}

/**
 *  查询的拦截器，主要将前端自动排序字段的驼峰转换为转换下滑线
 */
@Intercepts(
    Signature(
        type = Executor::class,
        method = "query",
        args = arrayOf(MappedStatement::class, Any::class, RowBounds::class, ResultHandler::class)
    ),
    Signature(
        type = Executor::class,
        method = "query",
        args = arrayOf(
            MappedStatement::class,
            Any::class,
            RowBounds::class,
            ResultHandler::class,
            CacheKey::class,
            BoundSql::class
        )
    )
)
class PageInterceptor : PageInterceptor() {

    override fun intercept(invocation: Invocation): Any {
        val mappedStatement = invocation.args[0] as MappedStatement
        val byPage = mappedStatement.id.endsWith("ByPage", true)
        // 获取 SQL 命令
        val sqlCommandType = mappedStatement.sqlCommandType

        if (SqlCommandType.SELECT == sqlCommandType) {
            // 获取参数
            val parameter = invocation.args[1]
            if (parameter is Base) {
                parameter.orderByInfo?.apply {
                    parameter.orderByInfo = this.map {
                        CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, it)
                    }.toTypedArray()
                }
                if (byPage) {
                    if (parameter.pageSize == null) {
                        parameter.pageSize = 10
                    }
                    if (parameter.currentPage == null) {
                        parameter.currentPage = 1
                    }
                }
            }
            invocation.args[1] = parameter
        }

        if (byPage) {
            return super.intercept(invocation)
        }
        return invocation.proceed()
    }
}


@Target(
    AnnotationTarget.FIELD
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class PrimaryKey

@Target(
    AnnotationTarget.FIELD
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class CreateUser

@Target(
    AnnotationTarget.FIELD
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class UpdateUser
