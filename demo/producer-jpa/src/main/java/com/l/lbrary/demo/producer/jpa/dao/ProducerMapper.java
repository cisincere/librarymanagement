package com.l.lbrary.demo.producer.jpa.dao;

import com.l.lbrary.demo.producer.jpa.entity.po.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProducerMapper extends PagingAndSortingRepository<Product,String> {
}
