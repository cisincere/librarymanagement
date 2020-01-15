package com.l.library.demo.producer.entity.po;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.l.library.common.web.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BasePo {
    private String name;
    private String description;
    @TableLogic
    private String deleted = "N";
}
