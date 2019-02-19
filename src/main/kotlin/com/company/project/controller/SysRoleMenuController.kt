package com.company.project.controller

import com.company.project.base.VerifyToken
import com.company.project.common.ResVal
import com.company.project.common.checkParam
import com.company.project.common.getPageInfo
import com.company.project.common.success
import com.company.project.model.SysRoleMenu
import com.company.project.service.ISysRoleMenuService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * <p>
 * 角色菜单表 前端控制器
 * </p>
 *
 * @author Jonz
 * @since 2018-12-26
 */
@Api(description = "角色菜单 功能相关接口")
@RestController
@RequestMapping("/sys/roleMenu")
class SysRoleMenuController {
    @Autowired
    lateinit var sysRoleMenuService: ISysRoleMenuService


    @PostMapping("/get")
    @ApiOperation(value = "获取角色菜单表")
    @VerifyToken
    fun getSysRoleMenu(@RequestParam id: String): ResVal = success(sysRoleMenuService.getSysRoleMenu(id))

    @PostMapping("/list")
    @ApiOperation(value = "角色菜单表方列表")
    @VerifyToken
    fun listSysRoleMenu(@RequestBody sysRoleMenu: SysRoleMenu): ResVal = success(sysRoleMenuService.getAllSysRoleMenuByPage(sysRoleMenu).getPageInfo())

    @PostMapping("/treeList")
    @ApiOperation(value = "角色菜单树状列表")
    @VerifyToken
    fun treeList(@RequestParam roleId: String): ResVal = success(sysRoleMenuService.getAllSysMenuByRoleId(roleId))


    @PostMapping("/save")
    @ApiOperation(value = "新增或修改角色菜单表")
    @VerifyToken
    fun saveSysRoleMenu(@RequestBody sysRoleMenu: SysRoleMenu): ResVal {
        val roleId = sysRoleMenu.roleId
        checkParam(roleId)
        sysRoleMenuService.deleteSysRoleMenuByRoleId(roleId)
        sysRoleMenu.checkedIds?.forEach {
            sysRoleMenuService.insertSysRoleMenu(SysRoleMenu(roleId = roleId, menuId = it))
        }
        return success()
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除角色菜单表")
    @VerifyToken
    fun deleteSysRoleMenu(@RequestParam id: String): ResVal {
        sysRoleMenuService.deleteSysRoleMenu(id)
        return success()
    }

}
