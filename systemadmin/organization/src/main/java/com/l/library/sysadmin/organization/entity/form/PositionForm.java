package com.l.library.sysadmin.organization.entity.form;

import com.l.library.common.web.entity.form.BaseForm;
import com.l.library.sysadmin.organization.entity.po.Position;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel
public class PositionForm extends BaseForm<Position> {

    @NotBlank(message = "职位名称不能为空")
    @ApiModelProperty(value = "职位名称")
    private String name;

    @ApiModelProperty(value = "职位描述")
    private String description;
}
