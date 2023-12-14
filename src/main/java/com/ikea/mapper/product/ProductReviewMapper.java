package com.ikea.mapper.product;

import com.ikea.entity.product.ProductReview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductReviewMapper {

  void create(ProductReview productReview);

  List<ProductReview> findAll(ProductReview productReview);

  ProductReview findOne(ProductReview productReview);

  void modify(ProductReview productReview);

  void remove(ProductReview productReview);

}
