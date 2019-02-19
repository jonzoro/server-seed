package com.company.project.base

import com.company.project.common.ResVal
import com.company.project.common.error
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest

/**
 * Created by Jonz on 2018/12/22.
 */
open class CustomizeException(override val message: String, var code: Int = 1) : RuntimeException(message)

class AuthException(override val message: String = "用户验证失败，请重新登录！") : CustomizeException(message)

class ParamException(override val message: String = "存在参数为空或有误，请核对！") : CustomizeException(message)

class IdNotFoundException(override val message: String = "Id 数据项不存在，请核对！") : CustomizeException(message)


@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [Exception::class])
    @ResponseBody
    fun jsonErrorHandler(req: HttpServletRequest, e: Exception): ResponseEntity<ResVal> {
        if (e is CustomizeException) {
            return ResponseEntity.ok(error(e.message, e.code))
        }
        logger.error("错误信息" + e.toString(), e)
        return ResponseEntity.ok(error(e.message ?: "接口异常。", 1))
    }

}
