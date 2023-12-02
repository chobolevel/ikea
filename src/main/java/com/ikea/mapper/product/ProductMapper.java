package com.ikea.mapper.product;

import com.ikea.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

  void create(Product product);

  List<Product> findAll(Product product);

  Product findOne(Product product);

  void modify(Product product);

  void remove(Product product);

}