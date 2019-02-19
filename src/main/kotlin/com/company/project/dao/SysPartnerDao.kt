package com.company.project.dao;

import com.company.project.model.SysPartner

/**
 * <p>
 * 合作方表 Mapper 接口
 * </p>
 *
 * @author Jonz
 * @since 2018-12-24
 */
interface SysPartnerDao : BaseMapper<SysPartner> {
    fun getByChannelMark(channelMark: String): SysPartner?
}
