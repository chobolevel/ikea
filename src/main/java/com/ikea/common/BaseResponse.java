package com.ikea.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@Builder
public class BaseResponse {

  private int statusCode;
  private String statusText;
  private String message;
  private String timestamp;

  public static BaseResponse getInstance(HttpStatus status) {
    return BaseResponse
        .builder()
        .statusCode(status.value())
        .statusText(status.getReasonPhrase())
        .timestamp(new Date().toString())
        .build();
  }

  public static BaseResponse getInstance(HttpStatus status, String message) {
    return BaseResponse
        .builder()
        .statusCode(status.value())
        .statusText(status.getReasonPhrase())
        .message(message)
        .timestamp(new Date().toString())
        .build();
  }

}
