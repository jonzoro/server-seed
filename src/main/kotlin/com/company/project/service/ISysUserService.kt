package com.company.project.service

import com.github.pagehelper.Page
import com.company.project.model.SysUser

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Jonz
 * @since 2018-12-25
 */
interface ISysUserService : IBaseService {
    fun getSysUser(id: String): SysUser
    fun getAllSysUser(sysUser: SysUser): List<SysUser>
    fun getAllSysUserByPage(sysUser: SysUser): Page<SysUser>
    fun insertSysUser(sysUser: SysUser)
    fun updateSysUser(sysUser: SysUser)
    fun deleteSysUser(id: String)
    fun getUserToken(user: SysUser): String
    fun verifyUser(token: String?): SysUser
    fun changePass(oldPass: String, newPass: String)
    fun login(channelMark: String, userName: String, password: String): Map<String, Any>
}
