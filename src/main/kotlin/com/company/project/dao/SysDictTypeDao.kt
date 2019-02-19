package com.company.project.dao;

import com.company.project.model.SysDictType

/**
 * <p>
 * 数据字典类型 Mapper 接口
 * </p>
 *
 * @author Jonz
 * @since 2019-01-03
 */
interface SysDictTypeDao : BaseMapper<SysDictType>{
    fun getAllSysDict(): List<Map<String,String>>
}
