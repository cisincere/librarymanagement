package com.l.library.sysadmin.organization.entity.param;

import com.l.library.common.web.entity.param.BaseParam;
import com.l.library.sysadmin.organization.entity.po.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupQueryParam extends BaseParam<Group> {
    private String name;
}
