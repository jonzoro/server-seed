package com.company.project.model

import com.company.project.base.PrimaryKey
import com.company.project.common.using

/**
 * <p>
 *  数据项列表 数据类
 * </p>
 * @author Jonz
 * @since 2019-01-03
 */
data class SysDictItem(

    @PrimaryKey
    var id: String = "",

    /**
     * 真实值
     */
    var value: String = "",

    /**
     * 显示值
     */
    var showVal: String = "",

    /**
     * 描述
     */
    var remark: String = "",

    /**
     * 字典id
     */
    var typeId: String = "",

    /**
     * 项目排序
     */
    var sort: Int? = null,

    /**
     * 删除标识
     */
    var delFlag: String = ""
) : Base(), IVerify {

    override fun verify() {
        "真实值 不能为空" using (value.isNotEmpty())
        "显示值 不能为空" using (showVal.isNotEmpty())
        "描述 不能为空" using (remark.isNotEmpty())
        "字典id 不能为空" using (typeId.isNotEmpty())
    }
}


