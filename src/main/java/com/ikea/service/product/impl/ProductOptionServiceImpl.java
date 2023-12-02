package com.ikea.service.product.impl;

import com.ikea.entity.product.ProductOption;
import com.ikea.enums.ApiExceptionType;
import com.ikea.exception.ApiException;
import com.ikea.mapper.product.ProductOptionMapper;
import com.ikea.service.product.ProductOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductOptionServiceImpl implements ProductOptionService {

  private final ProductOptionMapper productOptionMapper;

  @Override
  public void create(ProductOption productOption) throws ApiException {
    if(productOption.getProductId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "productId", "String");
    } else if(productOption.getColor().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "color", "String");
    } else if(productOption.getStock() == 0) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "stock", "int");
    } else if(productOption.getPrice() == 0) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "price", "int");
    }
    productOption.setId(UUID.randomUUID().toString());
    productOptionMapper.create(productOption);
  }

  @Override
  public void modify(ProductOption productOption) throws ApiException {
    if(productOption.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    productOptionMapper.modify(productOption);
  }

  @Override
  public void remove(ProductOption productOption) throws ApiException {
    if(productOption.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    productOptionMapper.remove(productOption);
  }
}
