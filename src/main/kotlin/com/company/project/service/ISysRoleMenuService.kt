package com.company.project.service

import com.github.pagehelper.Page
import com.company.project.model.SysMenu
import com.company.project.model.SysRoleMenu

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author Jonz
 * @since 2018-12-26
 */
interface ISysRoleMenuService : IBaseService {
    fun getSysRoleMenu(id: String): SysRoleMenu
    fun getAllSysRoleMenu(sysRoleMenu: SysRoleMenu): List<SysRoleMenu>
    fun getAllSysRoleMenuByPage(sysRoleMenu: SysRoleMenu): Page<SysRoleMenu>
    fun insertSysRoleMenu(sysRoleMenu: SysRoleMenu)
    fun updateSysRoleMenu(sysRoleMenu: SysRoleMenu)
    fun deleteSysRoleMenu(id: String)
    fun getAllSysMenuByRoleId(roleId: String): List<SysMenu>
    fun deleteSysRoleMenuByRoleId(roleId: String)
    fun queryRoleId(id: String): Int
}
