package com.company.project.controller

import com.company.project.base.VerifyToken
import com.company.project.common.ResVal
import com.company.project.common.getPageInfo
import com.company.project.common.success
import com.company.project.model.SysDictItem
import com.company.project.service.ISysDictItemService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * <p>
 *  数据字典项目列表前端控制器
 * </p>
 *
 * @author Jonz
 * @since 2019-01-03
 */
@Api(description = "数据字典项目功能相关接口")
@RestController
@RequestMapping("/sys-dict-item")
class SysDictItemController{

    @Autowired
    lateinit var sysDictItemService: ISysDictItemService


    @PostMapping("/get")
    @ApiOperation(value = "数据字典项目获取")
    @VerifyToken
    fun getSysDictItem(@RequestParam id: String): ResVal = success(sysDictItemService.getSysDictItem(id))

    @PostMapping("/list")
    @ApiOperation(value = "数据字典项目列表")
    @VerifyToken
    fun listSysDictItem(@RequestBody sysDictItem: SysDictItem): ResVal {
        if (sysDictItem.orderByInfo == null) {
            sysDictItem.orderByInfo = arrayOf("value")
        }
        return success(sysDictItemService.getAllSysDictItemByPage(sysDictItem).getPageInfo())
    }


    @PostMapping("/save")
    @ApiOperation(value = "数据字典项目新增或修改")
    @VerifyToken
    fun saveSysDictItem(@RequestBody sysDictItem: SysDictItem): ResVal {
        sysDictItem.verify()
        if (sysDictItem.id.isEmpty()) {
            sysDictItemService.insertSysDictItem(sysDictItem)
        } else {
            sysDictItemService.updateSysDictItem(sysDictItem)
        }
        return success(mapOf("id" to sysDictItem.id))
    }

    @PostMapping("/delete")
    @ApiOperation(value = "数据字典项目删除")
    @VerifyToken
    fun deleteSysMenu(@RequestParam id: String): ResVal {
        sysDictItemService.deleteSysDictItem(id)
        return success("success")
    }
}
