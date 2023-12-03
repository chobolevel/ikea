package com.ikea.service.product;

import com.ikea.entity.product.ProductOption;
import com.ikea.exception.ApiException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductOptionService {

  void create(ProductOption productOption, List<MultipartFile> uploadFiles) throws ApiException, IOException;

  void modify(ProductOption productOption) throws ApiException;

  void remove(ProductOption productOption) throws ApiException;

}
