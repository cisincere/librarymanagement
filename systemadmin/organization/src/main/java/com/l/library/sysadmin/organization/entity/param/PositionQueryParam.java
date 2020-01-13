package com.l.library.sysadmin.organization.entity.param;

import com.l.library.common.web.entity.form.BaseQueryForm;
import com.l.library.common.web.entity.param.BaseParam;
import com.l.library.sysadmin.organization.entity.po.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionQueryParam extends BaseParam<Position> {
    private String name;
}
