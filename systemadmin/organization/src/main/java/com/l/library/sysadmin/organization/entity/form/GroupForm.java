package com.l.library.sysadmin.organization.entity.form;

import com.l.library.common.web.entity.form.BaseForm;
import com.l.library.sysadmin.organization.entity.po.Group;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel
@Data
public class GroupForm extends BaseForm <Group>{
    @NotBlank(message = "用户组父id不能为空")
    @ApiModelProperty(value = "用户组父id") // 用于构建SwaggerApi
    private String parentId;

    @NotBlank(message = "用户组名称不能为空")
    @ApiModelProperty(value = "用户组名称") // 用于构建SwaggerApi
    private String name;

    @ApiModelProperty(value = "用户组描述")
    private String description;
}
