package com.company.project.controller

import com.company.project.base.VerifyToken
import com.company.project.common.ResVal
import com.company.project.common.getPageInfo
import com.company.project.common.success
import com.company.project.model.SysUserRole
import com.company.project.service.ISysRoleService
import com.company.project.service.ISysUserRoleService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * <p>
 * 用户与角色对应关系 前端控制器
 * </p>
 *
 * @author Jonz
 * @since 2018-12-26
 */
@Api(description = "用户与角色对应关系 功能相关接口")
@RestController
@RequestMapping("/sys/userRole")
class SysUserRoleController {

    @Autowired
    lateinit var sysUserRoleService: ISysUserRoleService

    @Autowired
    lateinit var sysRoleService: ISysRoleService

    @PostMapping("/get")
    @ApiOperation(value = "获取用户与角色对应关系")
    @VerifyToken
    fun getSysUserRole(@RequestParam id: String): ResVal = success(sysUserRoleService.getSysUserRole(id))

    @PostMapping("/list")
    @ApiOperation(value = "用户与角色对应关系列表")
    @VerifyToken
    fun listSysUserRole(@RequestBody sysUserRole: SysUserRole): ResVal = success(sysUserRoleService.getAllSysUserRoleByPage(sysUserRole).getPageInfo())

    @PostMapping("/save")
    @ApiOperation(value = "新增或修改用户与角色对应关系")
    @VerifyToken
    fun saveSysUserRole(@RequestBody sysUserRole: SysUserRole): ResVal {
        sysUserRole.verify()
        if (sysUserRole.id.isEmpty()) {
            sysUserRoleService.insertSysUserRole(sysUserRole)
        } else {
            sysUserRoleService.updateSysUserRole(sysUserRole)
        }
        return success(mapOf("id" to sysUserRole.id))
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除用户与角色对应关系")
    @VerifyToken
    fun deleteSysUserRole(@RequestParam id: String): ResVal {
        sysUserRoleService.deleteSysUserRole(id)
        return success()
    }

    @PostMapping("/getAll")
    @ApiOperation(value = "查询用户类型列表")
    @VerifyToken
    fun getAllSysUserRole(@RequestBody sysUserRole: SysUserRole): ResVal {
        return success(sysRoleService.getAllSysRoleByUserId(sysUserRole.userId))
    }

    @PostMapping("/updateListFast")
    @ApiOperation(value = "配置用户类型列表")
    @VerifyToken
    fun updateUserTypeListFast(@RequestBody sysUserRole: SysUserRole): ResVal {
        sysUserRoleService.insertBatchFast(sysUserRole)
        return success()
    }

}
