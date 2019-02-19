package com.company.project.service.impl

import com.github.pagehelper.Page
import com.company.project.base.IdNotFoundException
import com.company.project.base.CustomizeException
import com.company.project.base.loggerFor
import com.company.project.common.SysDictUtil
import com.company.project.common.getSessionUserOrThrow
import com.company.project.dao.SysPartnerDao
import com.company.project.model.SysPartner
import com.company.project.model.SysUser
import com.company.project.service.ISysPartnerService
import com.company.project.service.ISysUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * <p>
 * 合作方表 服务实现类
 * </p>
 *
 * @author Jonz
 * @since 2018-12-24
 */
@Service
class SysPartnerServiceImpl : ISysPartnerService {

    val log = loggerFor(this.javaClass)

    @Autowired
    lateinit var sysPartnerDao: SysPartnerDao
    @Autowired
    lateinit var sysUserService: ISysUserService

    override fun getSysPartner(id: String): SysPartner {
        return sysPartnerDao.get(id) ?: throw IdNotFoundException()
    }

    override fun getByChannelMark(channelMark: String): SysPartner? {
        return sysPartnerDao.getByChannelMark(channelMark)
    }

    override fun getAllSysPartner(sysPartner: SysPartner): List<SysPartner> {
        return sysPartnerDao.getAll(sysPartner)
    }

    override fun getAllSysPartnerByPage(sysPartner: SysPartner): Page<SysPartner> {
        val user = getSessionUserOrThrow()
        if (!user.isOwner()) {
            sysPartner.channelMark = user.channelMark
        }
        return sysPartnerDao.getAllByPage(sysPartner)
    }

    override fun insertSysPartner(sysPartner: SysPartner) {
        val user = getSessionUserOrThrow()
        if (!user.isOwner()) {
            throw CustomizeException(message = "权限不足")
        }
        sysPartner.partnerType = "1"
        sysPartnerDao.insert(sysPartner)
        // 添加合作方同时添加管理员账户
        sysUserService.insertSysUser(SysUser(username = "admin", initPass = "${sysPartner.channelMark}@2018", channelMark = sysPartner.channelMark, name = "管理员", openFlag = "1", userType = "1"))

        // 更新缓存
        SysDictUtil.addSysPartner(sysPartner)

    }

    override fun updateSysPartner(sysPartner: SysPartner) {
        val user = getSessionUserOrThrow()
        if (!user.isOwner()) {
            throw CustomizeException(message = "权限不足")
        }
        sysPartnerDao.update(sysPartner)
        // 更新缓存
        SysDictUtil.updateSysPartner(sysPartner)
    }

    override fun deleteSysPartner(id: String) {
        throw CustomizeException(message = "合作方不允许删除")
    }

}
