package com.ikea.service.product.impl;

import com.ikea.entity.product.ProductReview;
import com.ikea.enums.ApiExceptionType;
import com.ikea.exception.ApiException;
import com.ikea.mapper.product.ProductReviewMapper;
import com.ikea.service.product.ProductReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductReviewServiceImpl implements ProductReviewService {

  private final ProductReviewMapper productReviewMapper;

  @Override
  public void create(ProductReview productReview) throws ApiException {
    if(productReview.getUserId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "userId", "String");
    } else if(productReview.getProductId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "productId", "String");
    } else if(productReview.getRating() == 0 || productReview.getRating() < 0) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "rating", "double");
    } else if(productReview.getContent().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "content", "String");
    }
    productReview.setId(UUID.randomUUID().toString());
    productReviewMapper.create(productReview);
  }

  @Override
  public void modify(ProductReview productReview) throws ApiException {
    if(productReview.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    productReviewMapper.modify(productReview);
  }

  @Override
  public void remove(ProductReview productReview) throws ApiException {
    if(productReview.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    productReviewMapper.remove(productReview);
  }

}
