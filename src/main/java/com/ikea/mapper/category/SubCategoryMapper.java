package com.ikea.mapper.category;

import com.ikea.entity.category.SubCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubCategoryMapper {

  void create(SubCategory subCategory);

  List<SubCategory> findAll(SubCategory subCategory);

  SubCategory findOne(SubCategory subCategory);

  void modify(SubCategory subCategory);

  void remove(SubCategory subCategory);

}
