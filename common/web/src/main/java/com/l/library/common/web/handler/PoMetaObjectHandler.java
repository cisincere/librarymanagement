package com.l.library.common.web.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.l.library.common.web.entity.po.BasePo;
import com.l.library.manangement.common.core.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.sql.Date;
import java.time.ZonedDateTime;

@Slf4j
// mybatisplus自定义填充公共字段 ,即没有传的字段自动填充
public class PoMetaObjectHandler implements MetaObjectHandler {
    private String getCurrentUsername(){
        return StringUtils.defaultIfBlank(UserContextHolder.getInstance().getUsername(), BasePo.DEFAULT_USERNAME);
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setInsertFieldValByName("createdBy", getCurrentUsername(), metaObject);
        this.setInsertFieldValByName("createdTime", Date.from(ZonedDateTime.now().toInstant()),metaObject);
        this.updateFill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setUpdateFieldValByName("updatedBy", getCurrentUsername(), metaObject);
        this.setUpdateFieldValByName("updatedTime", Date.from(ZonedDateTime.now().toInstant()), metaObject);
    }
}
