package com.l.library.sysadmin.organization.entity.po;

import com.l.library.common.web.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends BasePo {
    private String parentId;
    private String name;
    private String type;
    private String href;
    private int orderNum;
    private String description;
}
