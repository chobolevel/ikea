package com.ikea.service.product;

import com.ikea.entity.product.Product;
import com.ikea.exception.ApiException;

import java.util.List;

public interface ProductService {

  void create(Product product) throws ApiException;

  List<Product> findAll(Product product) throws ApiException;

  Product findOne(Product product) throws ApiException;

  void modify(Product product) throws ApiException;

  void remove(Product product) throws ApiException;

}
