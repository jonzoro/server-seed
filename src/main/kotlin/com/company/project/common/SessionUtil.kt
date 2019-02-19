package com.company.project.common

import com.company.project.base.AuthException
import com.company.project.model.SysUser
import javax.servlet.http.HttpServletRequest

/**
 * Created by Jonz on 2018/12/24.
 */
object SessionUtil {

    val USER_SESSION = ThreadLocal<SysUser>()
    val REQUEST_HOLDER = ThreadLocal<HttpServletRequest>()
}

fun setSessionUser(user: SysUser) {
    SessionUtil.USER_SESSION.set(user)
}

fun getSessionUserOrThrow() = getSessionUser() ?: throw AuthException()

fun getSessionUser(): SysUser? = SessionUtil.USER_SESSION.get()

fun setRequest(request: HttpServletRequest) {
    SessionUtil.REQUEST_HOLDER.set(request)
}

fun getRequest(): HttpServletRequest? {
    return SessionUtil.REQUEST_HOLDER.get()
}
