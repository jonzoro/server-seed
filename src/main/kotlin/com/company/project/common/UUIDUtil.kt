package com.company.project.common

import java.util.*

/**
 * 生成UUID工具类
 */
object UUIDUtil {

    val uuid: String
        get() = UUID.randomUUID().toString().replace("-", "")
}