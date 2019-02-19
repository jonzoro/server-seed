package com.company.project.controller


import com.company.project.base.VerifyToken
import com.company.project.common.ResVal
import com.company.project.common.SysDictUtil
import com.company.project.common.getPageInfo
import com.company.project.common.success
import com.company.project.model.SysPartner
import com.company.project.service.ISysPartnerService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * <p>
 * 合作方表 前端控制器
 * </p>
 *
 * @author Jonz
 * @since 2018-12-24
 */
@Api(description = "合作方 功能相关接口")
@RestController
@RequestMapping("/sys/partner")
class SysPartnerController{

    @Autowired
    lateinit var sysPartnerService: ISysPartnerService


    @PostMapping("/get")
    @ApiOperation(value = "获取合作方表")
    @VerifyToken
    fun getSysPartner(@RequestParam id: String): ResVal = success(sysPartnerService.getSysPartner(id))

    @PostMapping("/list")
    @ApiOperation(value = "合作方表方列表")
    @VerifyToken
    fun listSysPartner(@RequestBody sysPartner: SysPartner): ResVal = success( sysPartnerService.getAllSysPartnerByPage(sysPartner).getPageInfo())


    @PostMapping("/save")
    @ApiOperation(value = "新增或修改合作方表")
    @VerifyToken
    fun saveSysPartner(@RequestBody sysPartner: SysPartner): ResVal {
        sysPartner.verify()
        if (sysPartner.id.isEmpty()) {
            sysPartnerService.insertSysPartner(sysPartner)
        } else {
            sysPartnerService.updateSysPartner(sysPartner)
        }
        return success( mapOf("id" to sysPartner.id))
    }

//    @PostMapping("/delete")
//    @ApiOperation(value = "删除合作方表")
//    fun deleteSysPartner(@RequestParam id: String): ResVal {
//        sysPartnerService.deleteSysPartner(id)
//        return success("success")
//    }

    @PostMapping("/dict")
    @ApiOperation(value = "合作方 数据字典")
    @VerifyToken
    fun dicSysUser(): ResVal {
        return success(SysDictUtil.mapOf("partnerType", "openFlag"))
    }

}
