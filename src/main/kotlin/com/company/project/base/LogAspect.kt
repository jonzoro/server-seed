package com.company.project.base

import com.company.project.common.Constant
import com.company.project.common.getSessionUser
import com.company.project.model.SysOperationLog
import com.company.project.service.ISysOperationLogService
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by Jonz on 2019/02/14.
 */
@Aspect
@Component
class LogAspect {

    @Autowired
    lateinit var sysOperationLogService: ISysOperationLogService

    /***
     * 用户登录切点
     */
    @Pointcut("execution(* com.company.project.service.ISysUserService.login(..))")
    fun loginPointcut(){}

    /***
     * 数据插入切点
     */
    @Pointcut("execution(* com.company.project.service.*.insert*(..))")
    fun insertPointcut(){}

    /***
     * 数据更新切点
     */
    @Pointcut("execution(* com.company.project.service.*.update*(..))")
    fun updatePointcut(){}

    /***
     * 数据删除切点
     */
    @Pointcut("execution(* com.company.project.service.*.delete*(..))")
    fun deletePointcut(){}

    @AfterReturning("loginPointcut()")
    fun loginLogHandler(point: JoinPoint) {loginHandler(point)}

    @AfterReturning("insertPointcut()")
    fun insertLogHandler(point: JoinPoint) {commonHandler(point, Constant.LOG_OP_TYPE.INSERT)}

    @AfterReturning("updatePointcut()")
    fun updateLogHandler(point: JoinPoint) {commonHandler(point, Constant.LOG_OP_TYPE.UPDATE)}

    @AfterReturning("deletePointcut()")
    fun deleteLogHandler(point: JoinPoint) {commonHandler(point, Constant.LOG_OP_TYPE.DELETE)}

    fun loginHandler(point: JoinPoint) {
        val args = point.args
        // 构建日志并入库
        val sysOperationLog = SysOperationLog()
        sysOperationLog.opUser = args[1].toString()
        sysOperationLog.opType = Constant.LOG_OP_TYPE.LOGIN
        sysOperationLogService.saveSysOperationLog(sysOperationLog)
    }

    /***
     * 公用切点处理
     */
    fun commonHandler(point: JoinPoint, opType: String) {
        // 当前执行的类
        val thisClass = point.`this`
        // 获取当前登陆用户
        val user = getSessionUser()

        // 特殊备注通过注解获取
        val signature: MethodSignature = point.signature as MethodSignature
        val method = signature.method
        val sysLog = method.getAnnotation(SysLog::class.java)
        val remark = sysLog?.value ?: ""

        // 获取ID
        val effectTableId = getId(point.args[0], opType)

        // 获取业务主键
        val businessKey = getBusinessKey(point.args[0], opType)

        // 构建日志并入库
        val sysOperationLog = SysOperationLog()
        sysOperationLog.opUser = user?.username ?: "unknown"
        sysOperationLog.opType = opType
        val rawName = thisClass.javaClass.name.split(".")[5]
        val className = rawName.split("$")[0].replace("ServiceImpl", "")
        sysOperationLog.effectTable = className
        sysOperationLog.effectTableId = effectTableId
        sysOperationLog.businessKey = businessKey
        sysOperationLog.remark = remark
        sysOperationLogService.saveSysOperationLog(sysOperationLog)
    }

    fun getId(arg: Any, opType: String): String {
        return if (Constant.LOG_OP_TYPE.DELETE == opType) {
            arg.toString()
        } else {
            val getMethod = arg.javaClass.getMethod("getId")
            getMethod.invoke(arg).toString()
        }
    }

    fun getBusinessKey(arg: Any, opType: String): String {
        var result = ""
        if (Constant.LOG_OP_TYPE.DELETE == opType) {
            result = arg.toString()
        } else {
            val fields = arg.javaClass.declaredFields
            for (field in fields) {
                if (null != field.getAnnotation(BusinessKey::class.java)) {
                    val businessKey = field.getAnnotation(BusinessKey::class.java).remark
                    val methodName  = "get" + field.name.capitalize()
                    val method = arg.javaClass.getMethod(methodName)
                    val businessKeyVal = method.invoke(arg).toString()
                    result = "$businessKey:$businessKeyVal"
                    break
                }
            }
        }
        return result
    }
}

/***
 * 日志备注注解
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class SysLog(val value: String = "")

/***
 * 业务主键注解
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class BusinessKey(val remark: String = "")