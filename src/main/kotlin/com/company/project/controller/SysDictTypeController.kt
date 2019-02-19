package com.company.project.controller

import com.company.project.base.VerifyToken
import com.company.project.common.ResVal
import com.company.project.common.getPageInfo
import com.company.project.common.success
import com.company.project.model.SysDictType
import com.company.project.service.ISysDictTypeService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * <p>
 * 数据字典类型 前端控制器
 * </p>
 *
 * @author Jonz
 * @since 2019-01-03
 */
@Api(description = "数据字典类型 功能相关接口")
@RestController
@RequestMapping("/sys-dict-type")
class SysDictTypeController{

    @Autowired
    lateinit var sysDictTypeService: ISysDictTypeService


    @PostMapping("/get")
    @ApiOperation(value = "获取数据字典类型")
    @VerifyToken
    fun getSysDictType(@RequestParam id: String): ResVal = success(sysDictTypeService.getSysDictType(id))

    @PostMapping("/list")
    @ApiOperation(value = "数据字典类型列表")
    @VerifyToken
    fun listSysDictType(@RequestBody sysDictType: SysDictType): ResVal {
        //获取删除标识为0(未删除)的字典类型
        sysDictType.delFlag = "0"
        return success(sysDictTypeService.getAllSysDictTypeByPage(sysDictType).getPageInfo())
    }


    @PostMapping("/save")
    @ApiOperation(value = "新增或修改数据字典类型")
    @VerifyToken
    fun saveSysDictType(@RequestBody sysDictType: SysDictType): ResVal {
        if (sysDictType.id.isEmpty()) {
            sysDictType.verify()
            sysDictTypeService.insertSysDictType(sysDictType)
        } else {
            sysDictTypeService.updateSysDictType(sysDictType)
        }
        return success(mapOf("id" to sysDictType.id))
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除数据字典类型")
    @VerifyToken
    fun deleteSysDictType(@RequestParam id: String): ResVal {
        val sysDictType = sysDictTypeService.getSysDictType(id)
        //删除，置删除标识位为1
        sysDictType.delFlag = "1"
        sysDictTypeService.updateSysDictType(sysDictType)
        return success()
    }

}
