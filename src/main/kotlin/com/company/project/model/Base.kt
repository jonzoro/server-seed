package com.company.project.model

import com.company.project.base.CreateUser
import com.company.project.base.UpdateUser
import java.util.*

open class Base {
    var createDate: Date? = null
    var createDateBegin: Date? = null
    var createDateEnd: Date? = null
    var updateDate: Date? = null
    var updateDatBegine: Date? = null
    var updateDatEnd: Date? = null

    @CreateUser
    var createUser: String? = null

    @UpdateUser
    var updateUser: String? = null

    var orderByInfo: Array<String>? = null
    var currentPage: Int? = null
    var pageSize: Int? = null
}

interface IVerify {
    fun verify()
}
