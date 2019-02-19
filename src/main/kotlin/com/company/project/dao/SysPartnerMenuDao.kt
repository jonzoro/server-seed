package com.company.project.dao;

import com.company.project.model.SysPartnerMenu;
import org.apache.ibatis.annotations.Param

/**
 * <p>
 * 合作方菜单表 Mapper 接口
 * </p>
 *
 * @author Jonz
 * @since 2019-01-25
 */
interface SysPartnerMenuDao : BaseMapper<SysPartnerMenu>{
    fun deleteByPartnerId(partnerId: String)
    fun insertFast(@Param("sysPartnerMenuList") sysPartnerMenuList: List<SysPartnerMenu>)
}