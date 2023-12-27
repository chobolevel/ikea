package com.ikea.entity.base;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasePagingResponse {

  private int pageNum;
  private int limit;
  private int totalCount;
  private List<?> data;

}
