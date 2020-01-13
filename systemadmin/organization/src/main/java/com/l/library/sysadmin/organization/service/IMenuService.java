package com.l.library.sysadmin.organization.service;

import com.l.library.sysadmin.organization.entity.param.MenuQueryParam;
import com.l.library.sysadmin.organization.entity.po.Menu;

import java.util.List;

public interface IMenuService{
    /**
     * 获取菜单
     * @param id
     * @return
     */
    Menu get(String id);

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    boolean add(Menu menu);

    /**
     * 查询菜单
     * @param queryParam
     * @return
     */
    List<Menu> query(MenuQueryParam queryParam);

    /**
     * 根据父id查找菜单
     * @param parentId
     * @return
     */
    List<Menu> queryByParentId(String parentId);

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    boolean update(Menu menu);

    /**
     * 根据id删除菜单
     * @param id
     * @return
     */
    boolean delete(String id);
}
