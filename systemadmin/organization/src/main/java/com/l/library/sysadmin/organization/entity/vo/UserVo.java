package com.l.library.sysadmin.organization.entity.vo;

import com.l.library.common.web.entity.vo.BaseVo;
import com.l.library.sysadmin.organization.entity.po.User;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Set;

public class UserVo extends BaseVo<User> {
    public UserVo(User user) {
        BeanUtils.copyProperties(user, this);
    }

    private String name;
    private String mobile;
    private String username;
    private String description;
    private String deleted;
    private Set<String> roleIds;
    private String createdBy;
    private String updatedBy;
    private Date createdTime;
    private Date updatedTime;
}
