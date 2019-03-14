package com.company.project.model

import com.company.project.base.PrimaryKey
import com.company.project.common.using

/**
 * 角色表
 * @author Jonz
 * @since 2018-12-26
 */
data class SysRole(

    /**
     * 主键id
     */
    @PrimaryKey
    var id: String = "",

    /**
     * 角色名称
     */
    var roleName: String = "",

    /**
     * 角色描述
     */
    var roleDesc: String = "",

    /**
     * 勾选框 1 已选中
     */
    var selected: String = ""
) : Base(), IVerify {

    override fun verify() {
        "角色名称 不能为空" using (roleName.isNotEmpty())
    }
}


