package com.company.project.service.impl

import com.github.pagehelper.Page
import com.company.project.base.IdNotFoundException
import com.company.project.base.loggerFor
import com.company.project.common.UUIDUtil
import com.company.project.common.checkParam
import com.company.project.dao.SysPartnerMenuDao
import com.company.project.model.SysMenu
import com.company.project.model.SysPartnerMenu
import com.company.project.service.ISysMenuService
import com.company.project.service.ISysPartnerMenuService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * <p>
 * 合作方菜单表 服务实现类
 * </p>
 *
 * @author Jonz
 * @since 2019-01-25
 */
@Service
class SysPartnerMenuServiceImpl : ISysPartnerMenuService {

    val log = loggerFor(this.javaClass)

    @Autowired
    lateinit var sysPartnerMenuDao: SysPartnerMenuDao

    @Autowired
    lateinit var sysMenuService: ISysMenuService

    override fun getSysPartnerMenu(id: String): SysPartnerMenu? {
        return sysPartnerMenuDao.get(id) ?: throw IdNotFoundException()
    }

    override fun getAllSysPartnerMenu(sysPartnerMenu: SysPartnerMenu): List<SysPartnerMenu> {
        return sysPartnerMenuDao.getAll(sysPartnerMenu)
    }

    override fun getAllSysPartnerMenuByPage(sysPartnerMenu: SysPartnerMenu): Page<SysPartnerMenu> {
        return sysPartnerMenuDao.getAllByPage(sysPartnerMenu)
    }

    override fun insertSysPartnerMenu(sysPartnerMenu: SysPartnerMenu) {
        sysPartnerMenuDao.insert(sysPartnerMenu)
    }

    override fun updateSysPartnerMenu(sysPartnerMenu: SysPartnerMenu) {
        sysPartnerMenuDao.update(sysPartnerMenu)
    }

    override fun deleteSysPartnerMenu(id: String) {
        sysPartnerMenuDao.delete(id)
    }

    override fun deleteByPartnerId(partnerId: String){
        sysPartnerMenuDao.deleteByPartnerId(partnerId)
    }

    override fun insertBatchFast(sysPartnerMenu: SysPartnerMenu) {
        val partnerId = sysPartnerMenu.partnerId
        checkParam(partnerId)
        // 先删除原有的菜单项
        this.deleteByPartnerId(partnerId)

        val sysPartnerMenuList = mutableListOf<SysPartnerMenu>()
        sysPartnerMenu.checkedIds?.forEach {
            sysPartnerMenuList.add(SysPartnerMenu(id = UUIDUtil.uuid, partnerId = sysPartnerMenu.partnerId, menuId = it))
        }
        // 保存新的菜单项
        sysPartnerMenuDao.insertFast(sysPartnerMenuList)
    }

    override fun getAllByPartnerId(partnerId: String): List<SysMenu> {
        val sysMenu = SysMenu(flag = 1)
        sysMenu.isAll = "0"
        sysMenu.partnerTypes = "1"
        sysMenu.orderByInfo = arrayOf("type", "sort")
        val allMenu = sysMenuService.getAllSysMenu(sysMenu)

        val allPartnerMenu = getAllSysPartnerMenu(SysPartnerMenu(partnerId = partnerId))

        val checkedMenuSet = allPartnerMenu.map {
            it.menuId
        }.toSet()

        allMenu.forEach {
            it.checked = if (checkedMenuSet.contains(it.id)) "1" else "0"
        }

        return sysMenuService.getMenuTreeList(allMenu)
    }

}
