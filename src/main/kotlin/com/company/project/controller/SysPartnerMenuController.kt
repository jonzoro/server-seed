package com.company.project.controller

import com.company.project.base.VerifyToken
import com.company.project.common.ResVal
import com.company.project.common.getPageInfo
import com.company.project.common.success
import com.company.project.model.SysPartnerMenu
import com.company.project.service.ISysPartnerMenuService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * <p>
 * 合作方菜单表 前端控制器
 * </p>
 *
 * @author Jonz
 * @since 2019-01-25
 */
@Api(description = "合作方菜单表 功能相关接口")
@RestController
@RequestMapping("/sys/partner-menu")
class SysPartnerMenuController{

    @Autowired
    lateinit var sysPartnerMenuService: ISysPartnerMenuService


    @PostMapping("/get")
    @ApiOperation(value = "获取合作方菜单表")
    @VerifyToken
    fun getSysPartnerMenu(@RequestParam id: String): ResVal = success(sysPartnerMenuService.getSysPartnerMenu(id))

    @PostMapping("/list")
    @ApiOperation(value = "合作方菜单表列表")
    @VerifyToken
    fun listSysPartnerMenu(@RequestBody sysPartnerMenu: SysPartnerMenu): ResVal = success(sysPartnerMenuService.getAllSysPartnerMenuByPage(sysPartnerMenu).getPageInfo())

    @PostMapping("/treeList")
    @ApiOperation(value = "合作方菜单树状列表")
    @VerifyToken
    fun treeList(@RequestParam partnerId: String): ResVal = success(sysPartnerMenuService.getAllByPartnerId(partnerId))


    @PostMapping("/save")
    @ApiOperation(value = "新增或修改合作方菜单表")
    @VerifyToken
    fun saveSysPartnerMenu(@RequestBody sysPartnerMenu: SysPartnerMenu): ResVal {
        sysPartnerMenuService.insertBatchFast(sysPartnerMenu)
        return  success()
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除合作方菜单表")
    @VerifyToken
    fun deleteSysPartnerMenu(@RequestParam id: String): ResVal {
        sysPartnerMenuService.deleteSysPartnerMenu(id)
        return success()
    }

}
