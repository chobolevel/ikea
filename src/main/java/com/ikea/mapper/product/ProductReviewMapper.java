package com.ikea.mapper.product;

import com.ikea.entity.product.ProductReview;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductReviewMapper {

  void create(ProductReview productReview);

  void modify(ProductReview productReview);

  void remove(ProductReview productReview);

}
