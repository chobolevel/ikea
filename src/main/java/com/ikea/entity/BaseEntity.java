package com.ikea.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEntity {

  private String createDate;
  private String updateDate;
  private int pageNum;
  private int limit;

}
