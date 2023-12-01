package com.ikea.entity.category;

import com.ikea.entity.BaseEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainCategory extends BaseEntity {

  private String id;
  private String code;
  private String name;

}
