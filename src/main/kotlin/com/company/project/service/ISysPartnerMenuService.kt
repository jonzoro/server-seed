package com.company.project.service

import com.github.pagehelper.Page
import com.company.project.model.SysMenu
import com.company.project.model.SysPartnerMenu

/**
 * <p>
 * 合作方菜单表 服务类
 * </p>
 *
 * @author Jonz
 * @since 2019-01-25
 */
interface ISysPartnerMenuService{
    fun getSysPartnerMenu(id: String): SysPartnerMenu?
    fun getAllSysPartnerMenu(sysPartnerMenu: SysPartnerMenu): List<SysPartnerMenu>
    fun getAllSysPartnerMenuByPage(sysPartnerMenu: SysPartnerMenu): Page<SysPartnerMenu>
    fun insertSysPartnerMenu(sysPartnerMenu: SysPartnerMenu)
    fun updateSysPartnerMenu(sysPartnerMenu: SysPartnerMenu)
    fun deleteSysPartnerMenu(id: String)
    fun deleteByPartnerId(partnerId: String)
    fun insertBatchFast(sysPartnerMenu: SysPartnerMenu)
    fun getAllByPartnerId(partnerId: String): List<SysMenu>
}
