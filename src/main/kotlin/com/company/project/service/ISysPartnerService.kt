package com.company.project.service

import com.github.pagehelper.Page
import com.company.project.model.SysPartner

/**
 * <p>
 * 合作方表 服务类
 * </p>
 *
 * @author Jonz
 * @since 2018-12-24
 */
interface ISysPartnerService : IBaseService {
    fun getSysPartner(id: String): SysPartner
    fun getAllSysPartner(sysPartner: SysPartner): List<SysPartner>
    fun getAllSysPartnerByPage(sysPartner: SysPartner): Page<SysPartner>
    fun insertSysPartner(sysPartner: SysPartner)
    fun updateSysPartner(sysPartner: SysPartner)
    fun deleteSysPartner(id: String)
    fun getByChannelMark(channelMark: String): SysPartner?
}
