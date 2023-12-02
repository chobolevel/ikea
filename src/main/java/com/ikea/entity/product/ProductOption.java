package com.ikea.entity.product;

import com.ikea.entity.base.BaseEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOption extends BaseEntity {

  private String id;
  private String productId;
  private String color;
  private String width;
  private String depth;
  private String height;
  private int stock;
  private int price;

}
