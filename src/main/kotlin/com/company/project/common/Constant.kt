package com.company.project.common

object Constant {
    
    val ENCRYPT_SALT = "{encryptwords}"
    
    val OWNER: String = "owner"

    // 日志操作
    object LOG_OP_TYPE {
        const val INSERT = "创建"
        const val UPDATE = "编辑"
        const val DELETE = "删除"
        const val LOGIN = "登录"
    }
    
}
