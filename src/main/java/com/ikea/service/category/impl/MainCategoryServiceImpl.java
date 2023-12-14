package com.ikea.service.category.impl;

import com.ikea.entity.category.MainCategory;
import com.ikea.enums.ApiExceptionType;
import com.ikea.exception.ApiException;
import com.ikea.mapper.category.MainCategoryMapper;
import com.ikea.service.category.MainCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MainCategoryServiceImpl implements MainCategoryService {

  private final MainCategoryMapper categoryMapper;

  @Override
  public void create(MainCategory category) throws ApiException {
    if(category.getCode().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "code", "String");
    } else if(category.getName().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "name", "String");
    }
    MainCategory findCategory = categoryMapper.findOne(MainCategory.builder().code(category.getCode().toUpperCase()).build());
    if(findCategory != null) {
      throw new ApiException(ApiExceptionType.ALREADY_EXISTS, "MainCategory", "code");
    }
    category.setId(UUID.randomUUID().toString());
    categoryMapper.create(category);
  }

  @Override
  public List<MainCategory> findAll(MainCategory category) throws ApiException {
    return categoryMapper.findAll(category);
  }

  @Override
  public void modify(MainCategory category) throws ApiException {
    if(category.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    categoryMapper.modify(category);
  }

  @Override
  public void remove(MainCategory category) throws ApiException {
    if(category.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    categoryMapper.remove(category);
  }

}
