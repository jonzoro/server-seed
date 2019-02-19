package com.company.project.service.impl

import com.github.pagehelper.Page
import com.company.project.base.IdNotFoundException
import com.company.project.base.loggerFor
import com.company.project.dao.SysRoleMenuDao
import com.company.project.model.SysMenu
import com.company.project.model.SysRoleMenu
import com.company.project.service.ISysMenuService
import com.company.project.service.ISysRoleMenuService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author Jonz
 * @since 2018-12-26
 */
@Service
class SysRoleMenuServiceImpl : ISysRoleMenuService {

    val log = loggerFor(this.javaClass)

    @Autowired
    lateinit var sysRoleMenuDao: SysRoleMenuDao

    @Autowired
    lateinit var sysMenuService: ISysMenuService

    override fun getSysRoleMenu(id: String): SysRoleMenu {
        return sysRoleMenuDao.get(id) ?: throw IdNotFoundException()
    }

    override fun getAllSysRoleMenu(sysRoleMenu: SysRoleMenu): List<SysRoleMenu> {
        return sysRoleMenuDao.getAll(sysRoleMenu)
    }

    override fun getAllSysRoleMenuByPage(sysRoleMenu: SysRoleMenu): Page<SysRoleMenu> {
        return sysRoleMenuDao.getAllByPage(sysRoleMenu)
    }

    override fun insertSysRoleMenu(sysRoleMenu: SysRoleMenu) {
        sysRoleMenuDao.insert(sysRoleMenu)
    }

    override fun updateSysRoleMenu(sysRoleMenu: SysRoleMenu) {
        sysRoleMenuDao.update(sysRoleMenu)
    }

    override fun deleteSysRoleMenu(id: String) {
        sysRoleMenuDao.delete(id)
    }

    override fun getAllSysMenuByRoleId(roleId: String): List<SysMenu> {
        val sysMenu = SysMenu(flag = 1)
        sysMenu.isAll = "0"
        val allMenu = sysMenuService.getAllSysMenu(sysMenu)

        val checkedRoles = getAllSysRoleMenu(SysRoleMenu(roleId = roleId))

        val checkedRoleSet = checkedRoles.map {
            it.menuId
        }.toSet()

        allMenu.forEach {
            it.checked = if (checkedRoleSet.contains(it.id)) "1" else "0"
        }

        return sysMenuService.getMenuTreeList(allMenu)
    }

    override fun deleteSysRoleMenuByRoleId(roleId: String) {
        sysRoleMenuDao.deleteByRoleId(roleId)
    }

    override fun queryRoleId(id: String): Int {
        return sysRoleMenuDao.queryRoleId(id)
    }

}
