package com.company.project.model

import com.company.project.base.PrimaryKey
import com.company.project.common.using

/**
 * 菜单表
 * @author Jonz
 * @since 2018-12-25
 */
data class SysMenu(

        /**
         * 主键id
         */
        @PrimaryKey
        var id: String = "",

        /**
         * 父菜单ID，一级菜单为0
         */
        var parentId: String = "",

        /**
         * 菜单名称
         */
        var menuName: String = "",

        /**
         * 菜单对应的页面url
         */
        var url: String = "",

        /**
         * 显示顺序
         */
        var sort: Int? = null,

        /**
         * 是否有效
         */
        var flag: Int? = null,
        /**
         * 类型，0顶级菜单，1侧边菜单，2按钮
         */
        var type: Int? = null,

        /**
         * 菜单对应的图标
         */
        var icon: String = "",

        /**
         * 根据合作方类型过滤菜单（如：1,2)
         */
        var partnerTypes: String = "",

        /**
         * 菜单id（前端需要）
         */
        var menuId: Int? = null,

        /**
         * 模块名称
         */
        var menuAs: String = ""

) : Base(), IVerify {

    var parentMenuId: Int? = null
    var children: Array<SysMenu>? = null
    var roleIds: Array<String>? = null
    var checked: String? = null
    var isAll: String? = null
    var partnerId: String? = null
    var ids: Array<String>? = null

    override fun verify() {
        "父菜单ID 不能为空" using (parentId.isNotEmpty())
        "菜单名称 不能为空" using (menuName.isNotEmpty())
//            "菜单对应的页面url 不能为空" using (url.isNotEmpty())
        "显示顺序 不能为空" using (sort != null)
        "是否有效 不能为空" using (flag != null)
    }
}


