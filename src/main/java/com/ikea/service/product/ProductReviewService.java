package com.ikea.service.product;

import com.ikea.entity.product.ProductReview;
import com.ikea.exception.ApiException;

public interface ProductReviewService {

  void create(ProductReview productReview) throws ApiException;

  void modify(ProductReview productReview) throws ApiException;

  void remove(ProductReview productReview) throws ApiException;

}
