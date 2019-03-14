package com.company.project.service.impl

import com.github.pagehelper.Page
import com.company.project.base.IdNotFoundException
import com.company.project.base.loggerFor
import com.company.project.common.SysDictUtil
import com.company.project.dao.SysDictTypeDao
import com.company.project.model.SysDictType
import com.company.project.service.ISysDictTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * <p>
 * 数据字典类型 服务实现类
 * </p>
 *
 * @author Jonz
 * @since 2019-01-03
 */
@Service
class SysDictTypeServiceImpl : ISysDictTypeService {

    val log = loggerFor(this.javaClass)


    @Autowired
    lateinit var sysDictTypeDao: SysDictTypeDao

    override fun getSysDictType(id: String): SysDictType {
        return sysDictTypeDao.get(id) ?: throw IdNotFoundException()
    }

    override fun getAllSysDictType(sysDictType: SysDictType): List<SysDictType> {
        return sysDictTypeDao.getAll(sysDictType)
    }

    override fun getAllSysDictTypeByPage(sysDictType: SysDictType): Page<SysDictType> {
        return sysDictTypeDao.getAllByPage(sysDictType)
    }

    override fun insertSysDictType(sysDictType: SysDictType) {
        sysDictTypeDao.insert(sysDictType)
        refreshCache()
    }

    override fun updateSysDictType(sysDictType: SysDictType) {
        sysDictTypeDao.update(sysDictType)
        refreshCache()
    }

    override fun deleteSysDictType(id: String) {
        sysDictTypeDao.delete(id)
        refreshCache()
    }

    override fun getAllSysDict(): List<Map<String, String>> {
        return sysDictTypeDao.getAllSysDict()
    }

    override fun refreshCache() {
        val sysDictTypes = getAllSysDict()
        SysDictUtil.initDict(sysDictTypes)
    }

}
