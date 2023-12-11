package com.ikea.entity.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePagingEntity {

  private String createDate;
  private String updateDate;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private int pageNum;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private int offset;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private int limit;

}
