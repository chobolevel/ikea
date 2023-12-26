package com.ikea.service.product.impl;

import com.ikea.entity.product.Product;
import com.ikea.entity.product.ProductOption;
import com.ikea.enums.ApiExceptionType;
import com.ikea.exception.ApiException;
import com.ikea.mapper.product.ProductMapper;
import com.ikea.service.product.ProductOptionService;
import com.ikea.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductMapper productMapper;
  private final ProductOptionService productOptionService;

  @Override
  public void create(Product product, List<MultipartFile> uploadFiles) throws ApiException, IOException {
    if(product.getName() == null || product.getName().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "name", "String");
    } else if(product.getDesc() == null || product.getDesc().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "desc", "String");
    } else if(product.getMainCategoryCode() == null || product.getMainCategoryCode().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "mainCategory", "String");
    } else if(product.getSubCategoryCode() == null || product.getSubCategoryCode().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "subCategory", "String");
    }
    String productId = UUID.randomUUID().toString();
    product.setId(productId);

    ProductOption productOption = product.getProductOption();
    productOption.setProductId(productId);
    productMapper.create(product);
    productOptionService.create(productOption, uploadFiles);
  }

  @Override
  public List<Product> findAll(Product product) throws ApiException {
    if(product.getPageNum() <= 0) {
      product.setPageNum(1);
    }
    if(product.getLimit() <= 0) {
      product.setLimit(10);
    }
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
    if(product.getId() == null || product.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    productMapper.modify(product);
  }

  @Override
  public void remove(Product product) throws ApiException {
    if(product.getId() == null || product.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    productMapper.remove(product);
  }
}
