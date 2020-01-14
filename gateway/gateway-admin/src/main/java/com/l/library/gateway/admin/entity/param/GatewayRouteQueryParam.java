package com.l.library.gateway.admin.entity.param;

import com.l.library.common.web.entity.param.BaseParam;
import com.l.library.gateway.admin.entity.po.GatewayRoute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayRouteQueryParam extends BaseParam<GatewayRoute> {
    private String uri;
}
