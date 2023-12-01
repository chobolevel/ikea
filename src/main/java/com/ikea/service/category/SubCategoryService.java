package com.ikea.service.category;

import com.ikea.entity.category.SubCategory;
import com.ikea.exception.ApiException;

import java.util.List;

public interface SubCategoryService {

  void create(SubCategory subCategory) throws ApiException;

  List<SubCategory> findAll(SubCategory subCategory) throws ApiException;

  void modify(SubCategory subCategory) throws ApiException;

  void remove(SubCategory subCategory) throws ApiException;

}
