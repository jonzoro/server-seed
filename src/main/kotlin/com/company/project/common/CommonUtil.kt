package com.company.project.common

import com.github.pagehelper.Page
import com.company.project.base.ParamException
import com.company.project.model.Base
import java.lang.reflect.Field
import java.util.*

/**
 * Created by Jonz on 2018/12/24.
 */
infix fun String.using(expr: Boolean) {
    if (!expr) throw ParamException(message = this)
}

fun checkParam(vararg params: String) {
    params.forEach {
        if (it.isEmpty()) throw ParamException()
    }
}

fun success(data: Any? = null): ResVal {
    return ResValMsg(0, data, "操作成功")
}

fun error(msg: String?, code: Int = 1): ResVal {
    return ResValMsg(code = code, data = null, msg = msg)
}

fun <K, V> MutableMap<K, MutableList<V>>.getOrCreate(key: K): MutableList<V> {
    var list = this[key]
    if (list == null) {
        list = mutableListOf()
        this[key] = list
    }
    return list
}

fun <T : Base> Page<T>.toMapListPage(vararg fields: String): PageInfo<MutableMap<String, Any?>> {
    return PageInfo(this.toMapList(*fields), total.toInt(), pageSize, pages, pageNum)
}

fun <T : Base> List<T>.toMapList(vararg fields: String): MutableList<MutableMap<String, Any?>> {
    val result = mutableListOf<MutableMap<String, Any?>>()
    this.forEach {
        result.add(it.toMap(*fields))
    }
    return result
}

fun Base.toMap(vararg fields: String): MutableMap<String, Any?> {
    val map = mutableMapOf<String, Any?>()
    if (fields.isEmpty()) {
        return map
    }
    val fieldList = mutableListOf<Field>()
    var tempClass: Class<*>? = this.javaClass
    while (tempClass != null) {
        fieldList.addAll(Arrays.asList(*tempClass.declaredFields))
        tempClass = tempClass.superclass
        if (tempClass == Base::class.java) {
            fieldList.addAll(Arrays.asList(*tempClass.declaredFields))
            break
        }
    }


    for (field in fieldList) {
        field.isAccessible = true
        val modelField = field.name
        if (fields.contains(modelField)) {
            try {
                val value: Any? = field.get(this)
                map[modelField] = value
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
    }
    return map
}
