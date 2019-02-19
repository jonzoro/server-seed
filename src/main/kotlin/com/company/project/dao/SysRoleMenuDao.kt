package com.company.project.dao;

import com.company.project.model.SysRoleMenu

/**
 * <p>
 * 角色菜单表 Mapper 接口
 * </p>
 *
 * @author Jonz
 * @since 2018-12-26
 */
interface SysRoleMenuDao : BaseMapper<SysRoleMenu> {
    fun deleteByRoleId(roleId: String)
    fun queryRoleId(id: String):Int
}
