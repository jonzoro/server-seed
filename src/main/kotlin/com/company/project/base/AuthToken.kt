package com.company.project.base

import com.company.project.common.setRequest
import com.company.project.common.setSessionUser
import com.company.project.model.SysUser
import com.company.project.service.ISysUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by Jonz on 2018/12/22.
 */
@Target(
    AnnotationTarget.CLASS, AnnotationTarget.FUNCTION
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class IgnoreToken(val required: Boolean = true)

@Target(
    AnnotationTarget.CLASS, AnnotationTarget.FUNCTION
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class VerifyToken(val required: Boolean = true)

class AuthenticationInterceptor : HandlerInterceptor {

    @Autowired
    lateinit var sysUserService: ISysUserService
    @Autowired
    lateinit var environment: Environment

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        // OPTIONS或不是映射到方法直接通过
        if (request.method == "OPTIONS" || handler !is HandlerMethod) {
            return true
        }
        setRequest(request)

        // 从 http 请求头中取出 token
        val token: String? = request.getHeader("token")

        if (token == null || token.isEmpty()) {
            val env = environment.activeProfiles[0]
            if (env == "dev" || env == "test") {
                setSessionUser(SysUser(id = "2776cd3a081011e9bd39fa163e168207", username = "admin", userType = "0", openFlag = "1"))
                return true
            }
        }
        val method = handler.method

        //检查是否有IgnoreToken注释，有则跳过认证
        if (method.isAnnotationPresent(IgnoreToken::class.java)) {
            val passToken = method.getAnnotation(IgnoreToken::class.java)
            if (passToken.required) {
                return true
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(VerifyToken::class.java)) {
            val userLoginToken = method.getAnnotation(VerifyToken::class.java)
            if (userLoginToken.required) {
                // 执行认证
                val user = sysUserService.verifyUser(token)
                setSessionUser(user)
                return true
            }
        }
        return true
    }
}
