package com.company.project.controller

import com.company.project.service.ISysBannerService
import com.company.project.base.VerifyToken
import com.company.project.common.ResVal
import com.company.project.common.success
import com.company.project.common.getPageInfo
import com.company.project.model.SysBanner
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jonz
 * @since 2019-02-15
 */
@Api(description = "banner 功能相关接口")
@RestController
@RequestMapping("/sys-banner")
class SysBannerController{

    @Autowired
    lateinit var sysBannerService: ISysBannerService


    @PostMapping("/get")
    @ApiOperation(value = "获取banner")
    @VerifyToken
    fun getSysBanner(@RequestParam id: String): ResVal = success(sysBannerService.getSysBanner(id))

    @PostMapping("/list")
    @ApiOperation(value = "banner方列表")
    @VerifyToken
    fun listSysBanner(@RequestBody sysBanner: SysBanner): ResVal = success(sysBannerService.getAllSysBannerByPage(sysBanner).getPageInfo())

    @PostMapping("/listByLang")
    @ApiOperation(value = "banner方列表 根据语言分组")
    @VerifyToken
    fun listByLang(@RequestBody sysBanner: SysBanner): ResVal {
        val data = sysBannerService.getAllSysBanner(sysBanner)
        val map = data.groupBy { it.lang }
        return success(map)
    }


    @PostMapping("/save")
    @ApiOperation(value = "新增或修改banner")
    fun saveSysBanner(@RequestBody sysBanner: SysBanner): ResVal {
        if (sysBanner.id.isEmpty()) {
            sysBanner.verify()
            sysBannerService.insertSysBanner(sysBanner)
        } else {
            sysBannerService.updateSysBanner(sysBanner)
        }
        return success(mapOf("id" to sysBanner.id))
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除banner")
    fun deleteSysBanner(@RequestParam id: String): ResVal {
        sysBannerService.deleteSysBanner(id)
        return success()
    }

}
