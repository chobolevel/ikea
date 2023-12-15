package com.ikea.service.product.impl;

import com.ikea.entity.product.ProductReview;
import com.ikea.enums.ApiExceptionType;
import com.ikea.exception.ApiException;
import com.ikea.mapper.product.ProductReviewMapper;
import com.ikea.service.product.ProductReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductReviewServiceImpl implements ProductReviewService {

  private final ProductReviewMapper productReviewMapper;

  @Override
  public void create(ProductReview productReview) throws ApiException {
    if(productReview.getUser() == null || productReview.getUserId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "userId", "String");
    } else if(productReview.getProductId() == null || productReview.getProductId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "productId", "String");
    } else if(productReview.getRating() <= 0) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "rating", "double");
    } else if(productReview.getContent() == null || productReview.getContent().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "content", "String");
    }
    productReview.setId(UUID.randomUUID().toString());
    productReviewMapper.create(productReview);
  }

  @Override
  public List<ProductReview> findAll(ProductReview productReview) throws ApiException {
    if(productReview.getProductId() == null || productReview.getProductId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "productId", "String");
    } else if(productReview.getPageNum() <= 0) {
      productReview.setPageNum(1);
    } else if(productReview.getLimit() <= 0) {
      productReview.setLimit(10);
    }
    productReview.setOffset((productReview.getPageNum() - 1) * 10);
    List<ProductReview> productReviewList = productReviewMapper.findAll(productReview);
    return productReviewList.stream().skip(productReview.getOffset()).limit(productReview.getLimit()).toList();
  }

  @Override
  public ProductReview findOne(ProductReview productReview) throws ApiException {
    if(productReview.getId() == null || productReview.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    return productReviewMapper.findOne(productReview);
  }

  @Override
  public void modify(ProductReview productReview) throws ApiException {
    if(productReview.getId() == null || productReview.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    productReviewMapper.modify(productReview);
  }

  @Override
  public void remove(ProductReview productReview) throws ApiException {
    if(productReview.getId() == null || productReview.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    productReviewMapper.remove(productReview);
  }

}
