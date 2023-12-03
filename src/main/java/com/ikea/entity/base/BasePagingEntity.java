package com.ikea.entity.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePagingEntity {

  private String createDate;
  private String updateDate;
  private int pageNum;
  private int offset;
  private int limit;

}
