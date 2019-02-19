package com.company.project.service

import com.github.pagehelper.Page
import com.company.project.model.SysMenu
import com.company.project.model.SysUser

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author Jonz
 * @since 2018-12-25
 */
interface ISysMenuService : IBaseService {
    fun getSysMenu(id: String): SysMenu
    fun getAllSysMenu(sysMenu: SysMenu): List<SysMenu>
    fun getAllSysMenuByPage(sysMenu: SysMenu): Page<SysMenu>
    fun insertSysMenu(sysMenu: SysMenu)
    fun updateSysMenu(sysMenu: SysMenu)
    fun deleteSysMenu(id: String)
    fun getAllSysMenuByUser(user: SysUser): List<SysMenu>
    fun getMenuTreeList(all: List<SysMenu>): List<SysMenu>
}
