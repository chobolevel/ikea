package com.ikea.entity.product;

import com.ikea.entity.base.BaseEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductReview extends BaseEntity {

  private String id;
  private String userId;
  private String productId;
  private double rating;
  private String content;

}
