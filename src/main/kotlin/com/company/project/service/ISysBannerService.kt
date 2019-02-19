package com.company.project.service

import com.company.project.model.SysBanner
import com.github.pagehelper.Page

/**
 * <p>
 * banner 服务类
 * </p>
 *
 * @author Jonz
 * @since 2019-02-15
 */
interface ISysBannerService{
    fun getSysBanner(id: String): SysBanner?
    fun getAllSysBanner(sysBanner: SysBanner): List<SysBanner>
    fun getAllSysBannerByPage(sysBanner: SysBanner): Page<SysBanner>
    fun insertSysBanner(sysBanner: SysBanner)
    fun updateSysBanner(sysBanner: SysBanner)
    fun deleteSysBanner(id: String)
}
