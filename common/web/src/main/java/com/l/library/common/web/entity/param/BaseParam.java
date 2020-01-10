package com.l.library.common.web.entity.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.l.library.common.web.entity.po.BasePo;

import java.util.Date;

public class BaseParam<T extends BasePo> {
    private Date createdTimeStart;

    private Date createdTimeEnd;

    /**
     * mybatis plus 插件 自定义sql
     * @return
     */
    public QueryWrapper<T> build(){
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge(null != this.createdTimeStart, "created_time",this.createdTimeStart)
                .le(null != this.createdTimeEnd, "created_time", this.createdTimeEnd);
        return queryWrapper;
    }
}
