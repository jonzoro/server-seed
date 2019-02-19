package com.company.project.controller


import com.company.project.base.IgnoreToken
import com.company.project.base.VerifyToken
import com.company.project.common.*
import com.company.project.model.SysMenu
import com.company.project.model.SysUser
import com.company.project.service.ISysMenuService
import com.company.project.service.ISysUserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Jonz
 * @since 2018-12-25
 */
@Api(description = "用户表 功能相关接口")
@RestController
@RequestMapping("/sys/user")
class SysUserController {

    @Autowired
    lateinit var sysUserService: ISysUserService
    @Autowired
    lateinit var sysMenuService: ISysMenuService


    @PostMapping("/get")
    @ApiOperation(value = "获取用户表")
    @VerifyToken
    fun getSysUser(@RequestParam id: String): ResVal = success(sysUserService.getSysUser(id).let {
        it.initPass = ""
        it.password = ""
        it
    })

    @PostMapping("/list")
    @ApiOperation(value = "用户列表")
    @VerifyToken
    fun listSysUser(@RequestBody sysUser: SysUser): ResVal = success(sysUserService.getAllSysUserByPage(sysUser).getPageInfo())


    @PostMapping("/save")
    @ApiOperation(value = "新增或修改用户表")
    @VerifyToken
    fun saveSysUser(@RequestBody sysUser: SysUser): ResVal {
        if (sysUser.id.isEmpty()) {
            sysUser.verify()
            sysUserService.insertSysUser(sysUser)
        } else {
            sysUserService.updateSysUser(sysUser)
        }
        return success(mapOf("id" to sysUser.id))
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除用户表")
    @VerifyToken
    fun deleteSysUser(@RequestParam id: String): ResVal {
        sysUserService.deleteSysUser(id)
        return success()
    }

    @PostMapping("/changePass")
    @ApiOperation(value = "用户修改密码")
    @VerifyToken
    fun changePass(@RequestParam oldPass: String,
                   @RequestParam newPass: String): ResVal {
        sysUserService.changePass(oldPass, newPass)
        return success()
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    @IgnoreToken
    fun login(@RequestParam channelMark: String,
              @RequestParam userName: String,
              @RequestParam password: String): ResVal {
        return success(sysUserService.login(channelMark, userName, password))
    }

    @PostMapping("/userInfo")
    @ApiOperation(value = "获取用户信息")
    @VerifyToken
    fun userInfo(): ResVal {
        val user = getSessionUserOrThrow()
        val data = user.toMap("id", "username", "channelMark", "name", "phone", "email", "userType")
        val menuList = sysMenuToMapList(sysMenuService.getAllSysMenuByUser(user))
        data["menuList"] = menuList
        user.partner?.apply {
            data["partner"] = this.toMap("id", "channelMark", "partnerName", "partnerType")
        }
        return success(data)
    }

    @PostMapping("/menuList")
    @ApiOperation(value = "用户权限列表")
    @VerifyToken
    fun menuList(): ResVal {
        val user = getSessionUserOrThrow()
        val menuList = sysMenuService.getAllSysMenuByUser(user)

        return success(sysMenuToMapList(menuList))
    }

    fun sysMenuToMapList(menuList: List<SysMenu>): List<MutableMap<String, Any?>> {
        return menuList.map {
            val map = mutableMapOf<String, Any?>(
                    "menuName" to it.menuName,
                    "url" to it.url,
                    "id" to it.menuId,
                    "parentId" to it.parentMenuId,
                    "menuAs" to it.menuAs
            )
            it.children?.apply {
                map["children"] = sysMenuToMapList(this.toList())
            }
            map
        }
    }

    @PostMapping("/dict")
    @ApiOperation(value = "用户 数据字典")
    @VerifyToken
    fun dicSysUser(): ResVal {
        val user = getSessionUserOrThrow()
        return if (user.isOwner()) {
            val dict = SysDictUtil.mapOf("partner", "userType", "openFlag")
            dict["partner"] = dict["partner"]!!.filter { "1" == it["openFlag"] }
            success(dict)
        } else {
            success(SysDictUtil.mapOf("userType", "openFlag"))
        }
    }


}
