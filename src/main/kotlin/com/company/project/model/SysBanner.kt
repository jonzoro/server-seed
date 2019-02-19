package com.company.project.model

import com.company.project.base.BusinessKey
import com.company.project.base.PrimaryKey
import com.company.project.common.using

/**
 * <p>
 *  数据类
 * </p>
 * @author Jonz
 * @since 2019-02-15
 */
data class SysBanner(

    /**
     * id
     */
    @PrimaryKey
    var id: String = "",

    /**
     * 标题
     */
    @BusinessKey("标题")
    var title: String = "",

    /**
     * 路径
     */
    var imgPath: String = "",

    /**
     * 跳转地址
     */
    var linkUrl: String = "",

    /**
     * 业务类型，tz:通证
     */
    var type: String = "",

    /**
     * 状态，1启用，0禁用
     */
    var status: String = "",

    var sort: Int? = null,

    /**
     * 语言版本，ch中文，en英文
     */
    var lang: String = "",

    var appId: String = "",

    /**
     * 备注
     */
    var remarks: String = "",

    /**
     * 备注en
     */
    var remarksEn: String = ""
) : Base(), IVerify {

    override fun verify() {
        "标题 不能为空" using (this.title.isNotEmpty())
        "路径 不能为空" using (this.imgPath.isNotEmpty())
        type = type.ifEmpty { "0" }
        status = status.ifEmpty { "1" }
    }
}


