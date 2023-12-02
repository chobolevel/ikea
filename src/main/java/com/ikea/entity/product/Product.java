package com.ikea.entity.product;

import com.ikea.entity.base.BasePagingEntity;
import com.ikea.entity.category.MainCategory;
import com.ikea.entity.category.SubCategory;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BasePagingEntity {

  private String id;
  private String name;
  private String desc;
  private String mainCategoryCode;
  private String subCategoryCode;

  private MainCategory mainCategory;
  private SubCategory subCategory;
  private List<ProductOption> productOptionList;

}
