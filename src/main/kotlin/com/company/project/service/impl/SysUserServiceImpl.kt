package com.company.project.service.impl

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.github.pagehelper.Page
import com.company.project.base.*
import com.company.project.common.*
import com.company.project.dao.SysUserDao
import com.company.project.model.SysUser
import com.company.project.service.ISysUserRoleService
import com.company.project.service.ISysUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Jonz
 * @since 2018-12-25
 */
@Service
class SysUserServiceImpl : ISysUserService {

    val log = loggerFor(this.javaClass)

    @Autowired
    lateinit var sysUserDao: SysUserDao
    @Autowired
    lateinit var sysUserRoleService: ISysUserRoleService


    override fun getSysUser(id: String): SysUser {
        return sysUserDao.get(id) ?: throw IdNotFoundException()
    }

    override fun getAllSysUser(sysUser: SysUser): List<SysUser> {
        val user = getSessionUserOrThrow()
        if (!user.isOwner()) {
            sysUser.channelMark = user.channelMark
        }
        return sysUserDao.getAll(sysUser)
    }

    override fun getAllSysUserByPage(sysUser: SysUser): Page<SysUser> {
        val user = getSessionUserOrThrow()
        if (!user.isOwner()) {
            sysUser.userType = "2"
            sysUser.channelMark = user.channelMark
        } else {
            sysUser.userTypes = arrayOf("1", "2")
        }
        val list = sysUserDao.getAllByPage(sysUser)
        if (list.isNotEmpty()) {
            val userRoleMap = sysUserRoleService.findRoleNameByUserId(list.map { it.id }.toTypedArray())
                    .map { it.userId to it.roleName }.toMap()

            list.forEach {
                it.userTypeName = SysDictUtil.findType("userType", it.userType)
                if (it.userType == "2") {
                    it.userTypeName = userRoleMap[it.id]
                }
            }
        }
        return list
    }

    override fun insertSysUser(sysUser: SysUser) {
        val user = getSessionUserOrThrow()

        if (sysUser.channelMark.isEmpty()) {
            sysUser.channelMark = user.channelMark
        }

        if (sysUser.userType == "0" || sysUser.userType == "1") {//管理员账户
            if (!user.isOwner()) {
                throw CustomizeException(message = "权限不足，无法新增管理员")
            }
        }
        if (sysUser.initPass.isNotEmpty()) {
            sysUser.password = MessageDigestUtil.md5Pass(sysUser.initPass)
        }

        sysUserDao.getUser(sysUser.channelMark, sysUser.username)?.apply {
            throw CustomizeException(message = "合作方【${sysUser.channelMark}】下用户名【${sysUser.username}】已存在")
        }
        sysUserDao.insert(sysUser)
    }

    override fun updateSysUser(sysUser: SysUser) {
        if (sysUser.initPass.isNotEmpty()) {
            sysUser.password = MessageDigestUtil.md5Pass(sysUser.initPass)
        }
        sysUserDao.update(sysUser)
    }

    override fun deleteSysUser(id: String) {
        val sysUser = getSysUser(id)
        val user = getSessionUserOrThrow()

        if (sysUser.userType == "0" || sysUser.userType == "1") {//管理员账户
            if (!user.isOwner()) {
                throw CustomizeException(message = "权限不足，无法删除管理员")
            }
        }
        sysUserDao.delete(id)
    }

    override fun changePass(oldPass: String, newPass: String) {
        checkParam(oldPass, newPass)
        val user = getSessionUserOrThrow()
        if (!user.password.equals(MessageDigestUtil.md5Pass(oldPass), true)) {
            throw CustomizeException("原密码不正确。")
        }
        updateSysUser(SysUser(id = user.id, password = MessageDigestUtil.md5Pass(newPass)))
    }

    override fun login(channelMark: String, userName: String, password: String): Map<String, Any> {
        val user = sysUserDao.getUser(channelMark, userName) ?: throw CustomizeException(message = "该用户不存在。")
        if (!user.password.equals(MessageDigestUtil.md5Pass(password), true)) {
            throw CustomizeException(message = "密码错误。")
        }
        if (user.openFlag != "1") {
            throw CustomizeException(message = "该用户已被禁用，请联系管理员。")
        }
        val token = getUserToken(user)
        return mapOf( "token" to token )
    }

    override fun getUserToken(user: SysUser): String {
        //todo 后续需要处理下加密问题
        return JWT.create().withAudience(user.id)
                .withIssuedAt(Date())
                .sign(Algorithm.HMAC256(Constant.ENCRYPT_SALT))
    }

    override fun verifyUser(token: String?): SysUser {
        if (token == null) {
            throw  ParamException()
        }

        try {
            val userId = JWT.decode(token).audience[0]
            // 验证 token
            val jwtVerifier = JWT.require(Algorithm.HMAC256(Constant.ENCRYPT_SALT)).build()
            jwtVerifier.verify(token)
            return getSysUser(userId)
        } catch (e: Exception) {
            throw  AuthException()
        }
    }

}
