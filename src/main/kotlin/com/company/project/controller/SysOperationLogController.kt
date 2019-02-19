package com.company.project.controller

import com.company.project.service.ISysOperationLogService
import com.company.project.base.VerifyToken
import com.company.project.common.ResVal
import com.company.project.common.success
import com.company.project.common.getPageInfo
import com.company.project.model.SysOperationLog
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
/**
 * <p>
 * 系统操作日志 前端控制器
 * </p>
 *
 * @author Jonz
 * @since 2019-02-15
 */
@Api(description = "系统操作日志 功能相关接口")
@RestController
@RequestMapping("/sys-operation-log")
class SysOperationLogController{

    @Autowired
    lateinit var sysOperationLogService: ISysOperationLogService


    @PostMapping("/get")
    @ApiOperation(value = "获取系统操作日志")
    @VerifyToken
    fun getSysOperationLog(@RequestParam id: String): ResVal = success(sysOperationLogService.getSysOperationLog(id))

    @PostMapping("/list")
    @ApiOperation(value = "系统操作日志列表")
    @VerifyToken
    fun listSysOperationLog(@RequestBody sysOperationLog: SysOperationLog): ResVal = success(sysOperationLogService.getAllSysOperationLogByPage(sysOperationLog).getPageInfo())


    @PostMapping("/save")
    @ApiOperation(value = "新增或修改系统操作日志")
    @VerifyToken
    fun saveSysOperationLog(@RequestBody sysOperationLog: SysOperationLog): ResVal {
        if (sysOperationLog.id.isEmpty()) {
            sysOperationLog.verify()
            sysOperationLogService.saveSysOperationLog(sysOperationLog)
        } else {
            sysOperationLogService.updateSysOperationLog(sysOperationLog)
        }
        return success(mapOf("id" to sysOperationLog.id))
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除系统操作日志")
    @VerifyToken
    fun deleteSysOperationLog(@RequestParam id: String): ResVal {
        sysOperationLogService.deleteSysOperationLog(id)
        return success()
    }

}
