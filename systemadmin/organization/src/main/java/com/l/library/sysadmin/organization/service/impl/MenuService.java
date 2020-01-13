package com.l.library.sysadmin.organization.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.l.library.sysadmin.organization.dao.MenuMapper;
import com.l.library.sysadmin.organization.entity.param.MenuQueryParam;
import com.l.library.sysadmin.organization.entity.po.Menu;
import com.l.library.sysadmin.organization.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MenuService extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public boolean add(Menu menu) {
        return this.save(menu);
    }
    @Override
    @CacheInvalidate(name = "menu::", key = "#id")
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    @CacheInvalidate(name = "menu::", key = "#menu.id")
    public boolean update(Menu menu) {
        return this.updateById(menu);
    }

    @Override
    @Cached(name="menu::", key = "#id", cacheType = CacheType.BOTH)
    public Menu get(String id) {
        return this.getById(id);
    }

    @Override
    public List<Menu> query(MenuQueryParam queryParam) {
        QueryWrapper<Menu>query = queryParam.build();
        query.eq("name",queryParam.getName());
        return this.list(query);
    }

    @Override
    public List<Menu> queryByParentId(String parentId) {
        return this.list(new QueryWrapper<Menu>().eq("parent_id", parentId));
    }

}
