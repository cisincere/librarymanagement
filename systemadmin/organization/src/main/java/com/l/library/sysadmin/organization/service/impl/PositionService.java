package com.l.library.sysadmin.organization.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.l.library.sysadmin.organization.dao.PositionMapper;
import com.l.library.sysadmin.organization.entity.param.PositionQueryParam;
import com.l.library.sysadmin.organization.entity.po.Position;
import com.l.library.sysadmin.organization.service.IPositionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PositionService extends ServiceImpl<PositionMapper, Position> implements IPositionService {
    @Override
    public boolean add(Position position) {
        return this.save(position);
    }

    @Override
    @CacheInvalidate(name = "position::", key = "#id")
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    @CacheInvalidate(name = "position::", key = "#position.id")
    public boolean update(Position position) {
        return this.updateById(position);
    }

    @Override
    public Position get(String id) {
        return this.getById(id);
    }

    @Override
    public List<Position> query(PositionQueryParam positionQueryParam) {
        QueryWrapper<Position> queryWrapper = positionQueryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(positionQueryParam.getName()), "name", positionQueryParam.getName());
        return this.list(queryWrapper);
    }
}
