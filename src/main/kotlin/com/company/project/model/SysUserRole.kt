package com.company.project.model

import com.company.project.base.PrimaryKey
import com.company.project.common.using

/**
 * 用户与角色对应关系
 * @author Jonz
 * @since 2018-12-26
 */
data class SysUserRole(

        @PrimaryKey
        var id: String = "",

        /**
         * 合作方id
         */
        var partnerId: String = "",

        /**
         * 用户ID
         */
        var userId: String = "",

        /**
         * 角色ID
         */
        var roleId: String = "",

        /**
         * 角色编码
         */
        var roleCode: String = ""

) : Base(), IVerify {
    /**
     * 角色名称
     */
    var roleName: String? = null
    /**
     * 角色ID列表
     */
    var roleIdList: String? = null
    /**
     * 用户ID列表
     */
    var userIds: Array<String>? = null

    override fun verify() {
        "用户ID 不能为空" using (userId.isNotEmpty())
        "角色ID 不能为空" using (roleId.isNotEmpty())
    }
}


