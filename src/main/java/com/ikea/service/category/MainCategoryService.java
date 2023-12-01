package com.ikea.service.category;

import com.ikea.entity.category.MainCategory;
import com.ikea.exception.ApiException;

import java.util.List;

public interface MainCategoryService {

  void create(MainCategory category) throws ApiException;

  List<MainCategory> findAll(MainCategory category) throws ApiException;

  void modify(MainCategory category) throws ApiException;

  void remove(MainCategory category) throws ApiException;

}
