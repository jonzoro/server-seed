package com.company.project.dao;

import com.company.project.model.SysUserRole;
import org.apache.ibatis.annotations.Param

/**
 * <p>
 * 用户与角色对应关系 Mapper 接口
 * </p>
 *
 * @author Jonz
 * @since 2018-12-26
 */
interface SysUserRoleDao : BaseMapper<SysUserRole> {
    fun deleteByUserId(userId: String)
    fun insertFast(@Param("sysUserRoleList") sysUserRoleList: List<SysUserRole>)
    fun findRoleNameByUserId(userRole: SysUserRole): List<SysUserRole>
}
