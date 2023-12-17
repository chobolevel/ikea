package com.ikea.service.product;

import com.ikea.entity.product.Product;
import com.ikea.exception.ApiException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

  void create(Product product, List<MultipartFile> uploadFiles) throws ApiException, IOException;

  List<Product> findAll(Product product) throws ApiException;

  Product findOne(Product product) throws ApiException;

  void modify(Product product) throws ApiException;

  void remove(Product product) throws ApiException;

}
