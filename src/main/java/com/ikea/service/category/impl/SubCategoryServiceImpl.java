package com.ikea.service.category.impl;

import com.ikea.entity.category.SubCategory;
import com.ikea.enums.ApiExceptionType;
import com.ikea.exception.ApiException;
import com.ikea.mapper.category.SubCategoryMapper;
import com.ikea.service.category.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

  private final SubCategoryMapper subCategoryMapper;

  @Override
  public void create(SubCategory subCategory) throws ApiException {
    if(subCategory.getMainCategoryId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "mainCategoryId", "String");
    } else if(subCategory.getCode().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "code", "String");
    } else if(subCategory.getName().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "name", "String");
    }
    SubCategory findSubCategory = subCategoryMapper.findOne(SubCategory
        .builder()
        .mainCategoryId(subCategory.getMainCategoryId())
        .code(subCategory.getCode())
        .build());
    if(findSubCategory != null) {
      throw new ApiException(ApiExceptionType.ALREADY_EXISTS, "SubCategory", "code");
    }
    subCategory.setId(UUID.randomUUID().toString());
    subCategoryMapper.create(subCategory);
  }

  @Override
  public List<SubCategory> findAll(SubCategory subCategory) throws ApiException {
    return subCategoryMapper.findAll(subCategory);
  }

  @Override
  public void modify(SubCategory subCategory) throws ApiException {
    if(subCategory.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    subCategoryMapper.modify(subCategory);
  }

  @Override
  public void remove(SubCategory subCategory) throws ApiException {
    if(subCategory.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    subCategoryMapper.remove(subCategory);
  }

}
