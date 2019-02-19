package com.company.project.service.impl

import com.company.project.model.SysOperationLog
import com.company.project.dao.SysOperationLogDao
import com.company.project.service.ISysOperationLogService
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import com.github.pagehelper.Page
import com.company.project.base.AuthException
import com.company.project.base.IdNotFoundException
import com.company.project.base.loggerFor
import com.company.project.common.getSessionUser

/**
 * <p>
 * 系统操作日志 服务实现类
 * </p>
 *
 * @author Jonz
 * @since 2019-02-15
 */
@Service
class SysOperationLogServiceImpl : ISysOperationLogService {

    val log = loggerFor(this.javaClass)

    @Autowired
    lateinit var sysOperationLogDao: SysOperationLogDao

    override fun getSysOperationLog(id: String): SysOperationLog? {
        return sysOperationLogDao.get(id) ?: throw IdNotFoundException()
    }

    override fun getAllSysOperationLog(sysOperationLog: SysOperationLog): List<SysOperationLog> {
        return sysOperationLogDao.getAll(sysOperationLog)
    }

    override fun getAllSysOperationLogByPage(sysOperationLog: SysOperationLog): Page<SysOperationLog> {
        return sysOperationLogDao.getAllByPage(sysOperationLog)
    }

    override fun saveSysOperationLog(sysOperationLog: SysOperationLog) {
        sysOperationLogDao.insert(sysOperationLog)
    }

    override fun updateSysOperationLog(sysOperationLog: SysOperationLog) {
        sysOperationLogDao.update(sysOperationLog)
    }

    override fun deleteSysOperationLog(id: String) {
        sysOperationLogDao.delete(id)
    }

}
