package com.ikea.entity.attachment;

import com.ikea.entity.base.BaseEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment extends BaseEntity {

  private String id;
  private String productOptionId;
  private String name;

}
