package com.l.library.demo.producer.entity.param;

import com.l.library.common.web.entity.param.BaseParam;
import com.l.library.demo.producer.entity.po.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductQueryParam extends BaseParam<Product> {
    private String name;
}
