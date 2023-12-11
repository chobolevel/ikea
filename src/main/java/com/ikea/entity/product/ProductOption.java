package com.ikea.entity.product;

import com.ikea.entity.attachment.Attachment;
import com.ikea.entity.base.BaseEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOption extends BaseEntity {

  private String id;
  private String productId;
  private String color;
  private int width;
  private int depth;
  private int height;
  private int stock;
  private int price;

  private List<Attachment> attachmentList;

}
