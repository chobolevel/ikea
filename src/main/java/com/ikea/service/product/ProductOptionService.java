package com.ikea.service.product;

import com.ikea.entity.product.ProductOption;
import com.ikea.exception.ApiException;

public interface ProductOptionService {

  void create(ProductOption productOption) throws ApiException;

  void modify(ProductOption productOption) throws ApiException;

  void remove(ProductOption productOption) throws ApiException;

}
