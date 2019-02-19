package com.company.project.service

import com.github.pagehelper.Page
import com.company.project.model.SysUserRole

/**
 * <p>
 * 用户与角色对应关系 服务类
 * </p>
 *
 * @author Jonz
 * @since 2018-12-26
 */
interface ISysUserRoleService{
    fun getSysUserRole(id: String): SysUserRole
    fun getAllSysUserRole(sysUserRole: SysUserRole): List<SysUserRole>
    fun getAllSysUserRoleByPage(sysUserRole: SysUserRole): Page<SysUserRole>
    fun insertSysUserRole(sysUserRole: SysUserRole)
    fun updateSysUserRole(sysUserRole: SysUserRole)
    fun deleteSysUserRole(id: String)
    fun deleteByUserId(userId: String)
    fun insertBatchFast(sysUserRole:SysUserRole)
    fun findRoleNameByUserId(userIds: Array<String>):List<SysUserRole>
}
