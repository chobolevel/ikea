package com.ikea.service.product.impl;

import com.ikea.entity.product.Product;
import com.ikea.enums.ApiExceptionType;
import com.ikea.exception.ApiException;
import com.ikea.mapper.product.ProductMapper;
import com.ikea.mapper.product.ProductOptionMapper;
import com.ikea.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductMapper productMapper;
  private final ProductOptionMapper productOptionMapper;

  @Override
  public void create(Product product) throws ApiException {
    if(product.getName().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "name", "String");
    } else if(product.getDesc().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "desc", "String");
    } else if(product.getMainCategoryCode().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "mainCategory", "String");
    } else if(product.getSubCategoryCode().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "subCategory", "String");
    }
    product.setId(UUID.randomUUID().toString());
    productMapper.create(product);
  }

  @Override
  public List<Product> findAll(Product product) throws ApiException {
    product.setOffset((product.getPageNum() - 1) * 10);
    List<Product> productList = productMapper.findAll(product);
    return productList.stream().skip(product.getOffset()).limit(product.getLimit()).toList();
  }

  @Override
  public Product findOne(Product product) throws ApiException {
    return productMapper.findOne(product);
  }

  @Override
  public void modify(Product product) throws ApiException {
    if(product.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    productMapper.modify(product);
  }

  @Override
  public void remove(Product product) throws ApiException {
    if(product.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    productMapper.remove(product);
  }
}
