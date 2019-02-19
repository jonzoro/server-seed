package com.company.project.service.impl

import com.github.pagehelper.Page
import com.company.project.base.IdNotFoundException
import com.company.project.base.loggerFor
import com.company.project.dao.SysDictItemDao
import com.company.project.model.SysDictItem
import com.company.project.service.ISysDictItemService
import com.company.project.service.ISysDictTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * <p>
 *  数据项列表服务实现类
 * </p>
 *
 * @author Jonz
 * @since 2019-01-03
 */
@Service
class SysDictItemServiceImpl : ISysDictItemService {
    val log = loggerFor(this.javaClass)

    @Autowired
    lateinit var sysDictItemDao: SysDictItemDao
    @Autowired
    lateinit var sysDictTypeService: ISysDictTypeService

    override fun getSysDictItem(id: String): SysDictItem {
        return sysDictItemDao.get(id) ?: throw IdNotFoundException()
    }

    override fun getAllSysDictItem(sysDictItem: SysDictItem): List<SysDictItem> {
        return sysDictItemDao.getAll(sysDictItem)
    }

    override fun getAllSysDictItemByPage(sysDictItem: SysDictItem): Page<SysDictItem> {
        val newSysDictItem = SysDictItem()
        newSysDictItem.delFlag = "0"
        newSysDictItem.typeId = sysDictItem.typeId
        return sysDictItemDao.getAllByPage(newSysDictItem)
    }

    override fun insertSysDictItem(sysDictItem: SysDictItem) {
        sysDictItem.delFlag = "0"
        if (sysDictItem.sort == null) {
            sysDictItem.sort = 0
        }
        sysDictItemDao.insert(sysDictItem)
        sysDictTypeService.refreshCache()
    }

    override fun updateSysDictItem(sysDictItem: SysDictItem) {
        sysDictItemDao.update(sysDictItem)
        sysDictTypeService.refreshCache()
    }

    /**
     * 此处为逻辑删除, 只将数据deleteFlag设置为1
     */
    override fun deleteSysDictItem(id: String) {
        val sysDictItem = SysDictItem(id = id, delFlag = "1")
        sysDictItemDao.update(sysDictItem)
        sysDictTypeService.refreshCache()
    }

}
