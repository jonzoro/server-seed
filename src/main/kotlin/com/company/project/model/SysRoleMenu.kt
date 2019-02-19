package com.company.project.model

import com.company.project.base.PrimaryKey
import com.company.project.common.using

/**
 * 角色菜单表
 * @author Jonz
 * @since 2018-12-26
 */
data class SysRoleMenu(

        @PrimaryKey
        var id: String = "",

        /**
         * 角色id
         */
        var roleId: String = "",

        /**
         * 菜单id
         */
        var menuId: String = ""
) : Base(), IVerify {

    var checkedIds: Array<String>? = null

    override fun verify() {
        "角色id 不能为空" using (roleId.isNotEmpty())
        "菜单id 不能为空" using (menuId.isNotEmpty())
    }
}


