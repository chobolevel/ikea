package com.ikea.entity.category;

import com.ikea.entity.base.BaseEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubCategory extends BaseEntity {

  private String id;
  private String mainCategoryId;
  private String code;
  private String name;

}
