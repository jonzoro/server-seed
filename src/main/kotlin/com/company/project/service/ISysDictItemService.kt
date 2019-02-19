package com.company.project.service

import com.github.pagehelper.Page
import com.company.project.model.SysDictItem

/**
 * <p>
 *  数据项列表 服务类
 * </p>
 *
 * @author Jonz
 * @since 2019-01-03
 */
interface ISysDictItemService{
    fun getSysDictItem(id: String): SysDictItem
    fun getAllSysDictItem(sysDictItem: SysDictItem): List<SysDictItem>
    fun getAllSysDictItemByPage(sysDictItem: SysDictItem): Page<SysDictItem>
    fun insertSysDictItem(sysDictItem: SysDictItem)
    fun updateSysDictItem(sysDictItem: SysDictItem)
    fun deleteSysDictItem(id: String)
}
