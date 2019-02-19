package com.company.project.service.impl

import com.github.pagehelper.Page
import com.company.project.base.IdNotFoundException
import com.company.project.base.loggerFor
import com.company.project.common.getSessionUserOrThrow
import com.company.project.dao.SysBannerDao
import com.company.project.model.SysBanner
import com.company.project.service.ISysBannerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * <p>
 * banner 服务实现类
 * </p>
 *
 * @author Jonz
 * @since 2019-02-15
 */
@Service
class SysBannerServiceImpl : ISysBannerService {

    val log = loggerFor(this.javaClass)

    @Autowired
    lateinit var sysBannerDao: SysBannerDao

    override fun getSysBanner(id: String): SysBanner? {
        return sysBannerDao.get(id) ?: throw IdNotFoundException()
    }

    override fun getAllSysBanner(sysBanner: SysBanner): List<SysBanner> {
        if(sysBanner.appId == ""){
            sysBanner.appId = getSessionUserOrThrow().channelMark
        }
        return sysBannerDao.getAll(sysBanner)
    }

    override fun getAllSysBannerByPage(sysBanner: SysBanner): Page<SysBanner> {
        if(sysBanner.appId == ""){
            sysBanner.appId = getSessionUserOrThrow().channelMark
        }
        return sysBannerDao.getAllByPage(sysBanner)
    }

    override fun insertSysBanner(sysBanner: SysBanner) {
        if(sysBanner.appId == ""){
            sysBanner.appId = getSessionUserOrThrow().channelMark
        }
        sysBannerDao.insert(sysBanner)
    }

    override fun updateSysBanner(sysBanner: SysBanner) {
        sysBannerDao.update(sysBanner)
    }

    override fun deleteSysBanner(id: String) {
        sysBannerDao.delete(id)
    }

}
