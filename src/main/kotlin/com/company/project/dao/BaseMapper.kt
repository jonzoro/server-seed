package com.company.project.dao

import com.github.pagehelper.Page
import com.company.project.model.Base

/**
 * Created by Jonz on 2018/12/24.
 */
interface BaseMapper<T : Base> {

    fun get(id: String): T?
    fun insert(data: T)
    fun update(data: T)
    fun delete(id: String)
    fun getAll(data: T): List<T>
    fun getAllByPage(data: T): Page<T>
}
