package com.company.project.service.impl

import com.github.pagehelper.Page
import com.company.project.base.IdNotFoundException
import com.company.project.base.loggerFor
import com.company.project.common.getSessionUserOrThrow
import com.company.project.dao.SysRoleDao
import com.company.project.model.SysRole
import com.company.project.model.SysUserRole
import com.company.project.service.ISysRoleMenuService
import com.company.project.service.ISysRoleService
import com.company.project.service.ISysUserRoleService
import com.company.project.service.ISysUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Jonz
 * @since 2018-12-26
 */
@Service
class SysRoleServiceImpl : ISysRoleService {

    val log = loggerFor(this.javaClass)

    @Autowired
    lateinit var sysRoleDao: SysRoleDao

    @Autowired
    lateinit var sysRoleMenuService: ISysRoleMenuService

    @Autowired
    lateinit var sysUserService: ISysUserService

    @Autowired
    lateinit var sysUserRoleService: ISysUserRoleService


    override fun getSysRole(id: String): SysRole {
        return sysRoleDao.get(id) ?: throw IdNotFoundException()
    }

    override fun getAllSysRole(sysRole: SysRole): List<SysRole> {
        val user = getSessionUserOrThrow()
        sysRole.channelMark = user.channelMark
        if (user.isOwner()) {


        }
        return sysRoleDao.getAll(sysRole)
    }

    override fun getAllSysRoleByUserId(userId: String): List<SysRole> {
        val user = getSessionUserOrThrow()
        val sysRole = SysRole(channelMark = user.channelMark)
        if (user.isOwner()) {
            val modUser = sysUserService.getSysUser(userId)
            sysRole.channelMark = modUser.channelMark
        }
        val allRole = sysRoleDao.getAll(sysRole)

        val userRoleIds = sysUserRoleService.getAllSysUserRole(SysUserRole(userId = userId)).map { it.roleId }
        allRole.forEach {
            it.selected = if (userRoleIds.contains(it.id)) "1" else "0"
        }
        return allRole
    }


    override fun getAllSysRoleByPage(sysRole: SysRole): Page<SysRole> {
        val user = getSessionUserOrThrow()
        sysRole.channelMark = user.channelMark
        return sysRoleDao.getAllByPage(sysRole)
    }

    override fun insertSysRole(sysRole: SysRole) {
        val user = getSessionUserOrThrow()
        sysRole.channelMark = user.channelMark
        sysRoleDao.insert(sysRole)
    }

    override fun updateSysRole(sysRole: SysRole) {
        sysRoleDao.update(sysRole)
    }

    override fun deleteSysRole(id: String): Int {
       val data=sysRoleMenuService.queryRoleId(id)
       if(data==0){
           sysRoleDao.delete(id)
       }
        return data
    }

}
