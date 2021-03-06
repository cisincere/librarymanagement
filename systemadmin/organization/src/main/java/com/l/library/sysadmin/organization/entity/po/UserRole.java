package com.l.library.sysadmin.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.l.library.common.web.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_role_relation")
public class UserRole extends BasePo {
    private String userId;
    private String roleId;
}