package com.ikea.entity.product;

import com.ikea.entity.base.BasePagingEntity;
import com.ikea.entity.user.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductReview extends BasePagingEntity {

  private String id;
  private String userId;
  private String productId;
  private double rating;
  private String content;

  private User user;

}
