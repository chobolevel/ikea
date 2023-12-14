package com.ikea.mapper.category;

import com.ikea.entity.category.MainCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainCategoryMapper {

  void create(MainCategory category);

  List<MainCategory> findAll(MainCategory category);

  MainCategory findOne(MainCategory mainCategory);

  void modify(MainCategory category);

  void remove(MainCategory category);

}
