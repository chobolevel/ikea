package com.ikea.entity.category;

import com.ikea.entity.BaseEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainCategory extends BaseEntity {

  private String id;
  private String code;
  private String name;

  private List<SubCategory> subCategoryList;

}
