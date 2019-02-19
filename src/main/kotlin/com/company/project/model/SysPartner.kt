package com.company.project.model

import com.company.project.base.PrimaryKey
import com.company.project.common.using

/**
 * 合作方表
 * @author Jonz
 * @since 2018-12-24
 */
data class SysPartner(

    /**
     * 主键id
     */
    @PrimaryKey
    var id: String = "",

    /**
     * 渠道标识，字典表
     */
    var channelMark: String = "",

    /**
     * 合作方名称
     */
    var partnerName: String = "",

    /**
     * 用户标识
     */
    var userNo: String = "",

    /**
     * 钱包地址
     */
    var walletAddr: String? = null,

    /**
     * 是否开启
     */
    var openFlag: String = "",

    /**
     * 备注
     */
    var remark: String? = null,

    /**
     * 合作类型，0为最高级别（owner）
     */
    var partnerType: String = ""
) : Base(), IVerify {

    override fun verify() {
        "渠道标识 不能为空" using (channelMark.isNotEmpty())
        "合作方名称 不能为空" using (partnerName.isNotEmpty())
        "是否开启 不能为空" using (openFlag.isNotEmpty())
//            "合作类型 不能为空" using (partnerType.isNotEmpty())
    }
}


