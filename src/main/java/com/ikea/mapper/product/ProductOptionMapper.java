package com.ikea.mapper.product;

import com.ikea.entity.product.ProductOption;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductOptionMapper {

  void create(ProductOption productOption);

  void modify(ProductOption productOption);

  void remove(ProductOption productOption);

}
