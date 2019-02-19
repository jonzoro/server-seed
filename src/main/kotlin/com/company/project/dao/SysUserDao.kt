package com.company.project.dao;

import com.github.pagehelper.Page
import com.company.project.model.SysUser

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Jonz
 * @since 2018-12-25
 */
interface SysUserDao : BaseMapper<SysUser> {
    fun getUser(channelMark: String, userName: String): SysUser?
    fun findRoleNameByUserIdByPage(sysUser:SysUser): Page<SysUser>
}
