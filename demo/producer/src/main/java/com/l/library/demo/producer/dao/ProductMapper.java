package com.l.library.demo.producer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.l.library.demo.producer.entity.po.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}