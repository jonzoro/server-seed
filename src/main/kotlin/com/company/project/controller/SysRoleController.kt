package com.company.project.controller

import com.company.project.base.VerifyToken
import com.company.project.common.ResVal
import com.company.project.common.getPageInfo
import com.company.project.common.success
import com.company.project.model.SysRole
import com.company.project.service.ISysRoleService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author Jonz
 * @since 2018-12-26
 */
@Api(description = "角色 功能相关接口")
@RestController
@RequestMapping("/sys/role")
class SysRoleController{

    @Autowired
    lateinit var sysRoleService: ISysRoleService


    @PostMapping("/get")
    @ApiOperation(value = "获取角色表")
    @VerifyToken
    fun getSysRole(@RequestParam id: String): ResVal = success(sysRoleService.getSysRole(id))

    @PostMapping("/list")
    @ApiOperation(value = "角色表方列表")
    @VerifyToken
    fun listSysRole(@RequestBody sysRole: SysRole): ResVal = success(sysRoleService.getAllSysRoleByPage(sysRole).getPageInfo())


    @PostMapping("/save")
    @ApiOperation(value = "新增或修改角色表")
    @VerifyToken
    fun saveSysRole(@RequestBody sysRole: SysRole): ResVal {
        sysRole.verify()
        if (sysRole.id.isEmpty()) {
            sysRoleService.insertSysRole(sysRole)
        } else {
            sysRoleService.updateSysRole(sysRole)
        }
        return success(mapOf("id" to sysRole.id))
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除角色表")
    @VerifyToken
    fun deleteSysRole(@RequestParam id: String): ResVal {
        val data=sysRoleService.deleteSysRole(id)
        return success(data)
    }

}
