package com.ikea.exception;

import com.ikea.enums.ApiExceptionType;
import lombok.Getter;

@Getter
public class ApiException extends Exception {
  private final ApiExceptionType type;

  private String[] args;

  public ApiException(ApiExceptionType type) {
    super(type.getMessage());
    this.type = type;
  }

  public ApiException(ApiExceptionType type, String... args) {
    super(String.format(type.getMessage(), args));
    this.type = type;
    this.args = args;
  }
}
