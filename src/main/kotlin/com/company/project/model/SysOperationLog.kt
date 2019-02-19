package com.company.project.model

import com.company.project.model.Base
import com.company.project.common.using
import com.company.project.base.PrimaryKey

/**
 * <p>
 * 系统操作日志 数据类
 * </p>
 * @author Jonz
 * @since 2019-02-18
 */
data class SysOperationLog(

    @PrimaryKey
    var id: String = "",

    var opUser: String = "",

    var opType: String = "",

    var effectTableId: String = "",

    var businessKey: String = "",

    var effectTable: String = "",

    var effectColumn: String = "",

    var remark: String = ""
) : Base(), IVerify {

    override fun verify() {
        " 不能为空" using (id.isNotEmpty())
        " 不能为空" using (opUser.isNotEmpty())
        " 不能为空" using (opType.isNotEmpty())
        " 不能为空" using (effectTableId.isNotEmpty())
        " 不能为空" using (businessKey.isNotEmpty())
        " 不能为空" using (effectTable.isNotEmpty())
        " 不能为空" using (effectColumn.isNotEmpty())
        " 不能为空" using (remark.isNotEmpty())
    }
}


