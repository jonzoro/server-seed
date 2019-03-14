package com.company.project.service.impl

import com.github.pagehelper.Page
import com.company.project.base.IdNotFoundException
import com.company.project.base.loggerFor
import com.company.project.common.getSessionUserOrThrow
import com.company.project.dao.SysMenuDao
import com.company.project.model.SysMenu
import com.company.project.model.SysUser
import com.company.project.model.SysUserRole
import com.company.project.service.ISysMenuService
import com.company.project.service.ISysUserRoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author Jonz
 * @since 2018-12-25
 */
@Service
class SysMenuServiceImpl : ISysMenuService {

    val log = loggerFor(this.javaClass)

    @Autowired
    lateinit var sysMenuDao: SysMenuDao
    @Autowired
    lateinit var sysUserRoleService: ISysUserRoleService

    override fun getSysMenu(id: String): SysMenu {
        return sysMenuDao.get(id) ?: throw IdNotFoundException()
    }

    override fun getAllSysMenu(sysMenu: SysMenu): List<SysMenu> {
        return sysMenuDao.getAll(sysMenu)
    }

    override fun getAllSysMenuByPage(sysMenu: SysMenu): Page<SysMenu> {
        return sysMenuDao.getAllByPage(sysMenu)
    }

    override fun insertSysMenu(sysMenu: SysMenu) {
        sysMenuDao.insert(sysMenu)
    }

    override fun updateSysMenu(sysMenu: SysMenu) {
        sysMenuDao.update(sysMenu)
    }


    override fun deleteSysMenu(id: String) {
        sysMenuDao.delete(id)
    }


    override fun getAllSysMenuByUser(user: SysUser): List<SysMenu> {
        val menu = SysMenu()
        if (!user.isAdmin()) {
            val roleIds = sysUserRoleService.getAllSysUserRole(SysUserRole(userId = user.id)).map { it.roleId }.toTypedArray()
            menu.roleIds = roleIds
        }
        menu.orderByInfo = arrayOf("type", "sort")
        menu.flag = 1
        val all = sysMenuDao.getAll(menu)
        val parentIds = all.map { if (it.parentId.isEmpty()) "0" else it.parentId }.filter { it != "0" }.toMutableSet()
        all.forEach {
            parentIds.remove(it.id)
        }
        val list = all.toMutableList();
        if (parentIds.isNotEmpty()) {
            val parentMenu = SysMenu()
            parentMenu.ids = parentIds.toTypedArray()
            val add = sysMenuDao.getAll(parentMenu)
            list.addAll(add)
        }
        return getMenuTreeList(list)
    }

    override fun getMenuTreeList(all: List<SysMenu>): List<SysMenu> {

        val menuMap = all.groupBy { if (it.parentId.isEmpty()) "0" else it.parentId }

        val result = menuMap["0"] ?: emptyList()

        result.forEach {
            findAllChildMenu(it, menuMap)
        }
        return result
    }

    fun findAllChildMenu(pMenu: SysMenu, menuMap: Map<String, List<SysMenu>>) {
        val list = menuMap[pMenu.id]
        list?.forEach {
            it.parentMenuId = pMenu.menuId
            findAllChildMenu(it, menuMap)
        }
        pMenu.children = list?.toTypedArray()
    }

}
