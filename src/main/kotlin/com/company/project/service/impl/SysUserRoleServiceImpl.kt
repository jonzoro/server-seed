package com.company.project.service.impl

import com.github.pagehelper.Page
import com.company.project.base.IdNotFoundException
import com.company.project.base.CustomizeException
import com.company.project.base.loggerFor
import com.company.project.common.UUIDUtil
import com.company.project.dao.SysUserRoleDao
import com.company.project.model.SysUserRole
import com.company.project.service.ISysUserRoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author Jonz
 * @since 2018-12-26
 */
@Service
class SysUserRoleServiceImpl : ISysUserRoleService {


    val log = loggerFor(this.javaClass)

    @Autowired
    lateinit var sysUserRoleDao: SysUserRoleDao

    override fun getSysUserRole(id: String): SysUserRole {
        return sysUserRoleDao.get(id) ?: throw IdNotFoundException()
    }

    override fun getAllSysUserRole(sysUserRole: SysUserRole): List<SysUserRole> {
        return sysUserRoleDao.getAll(sysUserRole)
    }

    override fun getAllSysUserRoleByPage(sysUserRole: SysUserRole): Page<SysUserRole> {
        return sysUserRoleDao.getAllByPage(sysUserRole)
    }

    override fun insertSysUserRole(sysUserRole: SysUserRole) {
        sysUserRoleDao.insert(sysUserRole)
    }

    override fun updateSysUserRole(sysUserRole: SysUserRole) {
        sysUserRoleDao.update(sysUserRole)
    }

    override fun deleteSysUserRole(id: String) {
        sysUserRoleDao.delete(id)
    }

    override fun deleteByUserId(userId: String) {
        sysUserRoleDao.deleteByUserId(userId)
    }

    @Transactional
    override fun insertBatchFast(sysUserRole: SysUserRole) {
        try {
            val sysUserRoleList = mutableListOf<SysUserRole>()
            sysUserRoleDao.deleteByUserId(sysUserRole.userId)
            val strList = sysUserRole.roleId.split(",")
            strList.forEach {
                val newSysUserRole = SysUserRole()
                newSysUserRole.roleId = it
                newSysUserRole.id = UUIDUtil.uuid
                newSysUserRole.roleCode = sysUserRole.roleCode
                newSysUserRole.userId = sysUserRole.userId
                newSysUserRole.partnerId = sysUserRole.partnerId
                sysUserRoleList.add(newSysUserRole)
            }
            sysUserRoleDao.insertFast(sysUserRoleList)
        } catch (e: CustomizeException) {
            throw CustomizeException("更新失败")
        }

    }

    override fun findRoleNameByUserId(userIds: Array<String>): List<SysUserRole> {
        val userRole = SysUserRole()
        userRole.userIds = userIds
        return sysUserRoleDao.findRoleNameByUserId(userRole)

    }
}
