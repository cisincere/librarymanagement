package com.l.library.sysadmin.organization.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.l.library.sysadmin.organization.dao.GroupMapper;
import com.l.library.sysadmin.organization.entity.param.GroupQueryParam;
import com.l.library.sysadmin.organization.entity.po.Group;
import com.l.library.sysadmin.organization.service.IGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GroupService extends ServiceImpl<GroupMapper, Group> implements IGroupService {
    @Override
    public boolean add(Group group) {
        return this.save(group);
    }

    @Override
    // 删除缓存名为group:: 下的id
    @CacheInvalidate(name = "group::", key = "#id")
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    @Cached(name = "group::", key = "#group.id")
    public boolean update(Group group) {
        return this.updateById(group);
    }

    @Override
    @Cached(name = "group::", key = "#id", cacheType = CacheType.BOTH)
    public Group get(String id) {
        return this.getById(id);
    }

    @Override
    public List<Group> query(GroupQueryParam groupQueryParam) {
        QueryWrapper<Group> query = groupQueryParam.build();
        query.eq("name", groupQueryParam.getName());
        return this.list(query);
    }

    @Override
    public List<Group> queryByParentId(String id) {
        return this.list(new QueryWrapper<Group>().eq("parent_id", id));
    }
}
