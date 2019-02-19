package com.company.project.common

open class ResVal(
        open val code: Int, // 0-success, 1-error
        open val data: Any?
)

data class ResValMsg(
        override val code: Int, // 0-success, 1-error
        override val data: Any? = null,
        val msg: String?
) : ResVal(code, data)

data class ResData<T>(
        override val code: Int, // 0-success, 1-error
        override val data: T? = null,
        val msg: String = ""
) : ResVal(code, data)


