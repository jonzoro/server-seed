package com.company.project.model

import com.company.project.base.PrimaryKey
import com.company.project.common.using

/**
 * <p>
 * 合作方菜单表 数据类
 * </p>
 * @author Jonz
 * @since 2019-01-25
 */
data class SysPartnerMenu(

    @PrimaryKey
    var id: String = "",

    /**
     * 合作方id
     */
    var partnerId: String = "",

    /**
     * 菜单id
     */
    var menuId: String = ""
) : Base(), IVerify {

    var checkedIds: Array<String>? = null

    override fun verify() {
        " 不能为空" using (id.isNotEmpty())
        "合作方id 不能为空" using (partnerId.isNotEmpty())
        "菜单id 不能为空" using (menuId.isNotEmpty())
    }
}


