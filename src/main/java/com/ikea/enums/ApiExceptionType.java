package com.ikea.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApiExceptionType {

  MISSING_PARAMETER(HttpStatus.BAD_REQUEST, "%s[Type: %s]이/가 누락되었습니다.");

  private final HttpStatus status;
  private final String message;

}
