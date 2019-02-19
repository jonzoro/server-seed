package com.company.project.service

import com.company.project.model.SysOperationLog
import com.github.pagehelper.Page

/**
 * <p>
 * 系统操作日志 服务类
 * </p>
 *
 * @author Jonz
 * @since 2019-02-15
 */
interface ISysOperationLogService{
    fun getSysOperationLog(id: String): SysOperationLog?
    fun getAllSysOperationLog(sysOperationLog: SysOperationLog): List<SysOperationLog>
    fun getAllSysOperationLogByPage(sysOperationLog: SysOperationLog): Page<SysOperationLog>
    fun saveSysOperationLog(sysOperationLog: SysOperationLog)
    fun updateSysOperationLog(sysOperationLog: SysOperationLog)
    fun deleteSysOperationLog(id: String)
}
