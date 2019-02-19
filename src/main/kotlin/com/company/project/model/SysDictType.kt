package com.company.project.model

import com.company.project.base.PrimaryKey
import com.company.project.common.using

/**
 * <p>
 * 数据字典类型 数据类
 * </p>
 * @author Jonz
 * @since 2019-01-03
 */
data class SysDictType(

    @PrimaryKey
    var id: String = "",

    /**
     * 编码
     */
    var code: String = "",

    /**
     * 字典名称
     */
    var name: String = "",

    /**
     * 删除标识
     */
    var delFlag: String = "",

    /**
     * 描述
     */
    var remark: String = ""
) : Base(), IVerify {

    override fun verify() {
        "编码 不能为空" using (code.isNotEmpty())
        "字典名称 不能为空" using (name.isNotEmpty())
        "描述 不能为空" using (remark.isNotEmpty())
        if(delFlag.isEmpty()){
            delFlag="0"
        }
    }
}


