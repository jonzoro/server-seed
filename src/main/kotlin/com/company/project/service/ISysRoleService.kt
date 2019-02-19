package com.company.project.service

import com.github.pagehelper.Page
import com.company.project.model.SysRole

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Jonz
 * @since 2018-12-26
 */
interface ISysRoleService : IBaseService {
    fun getSysRole(id: String): SysRole
    fun getAllSysRole(sysRole: SysRole): List<SysRole>
    fun getAllSysRoleByPage(sysRole: SysRole): Page<SysRole>
    fun insertSysRole(sysRole: SysRole)
    fun updateSysRole(sysRole: SysRole)
    fun deleteSysRole(id: String):Int
    fun getAllSysRoleByUserId(userId: String): List<SysRole>
}
