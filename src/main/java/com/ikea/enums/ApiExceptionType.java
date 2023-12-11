package com.ikea.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApiExceptionType {

  MISSING_PARAMETER(HttpStatus.BAD_REQUEST, "%s[Type: %s]이/가 누락되었습니다."),
  FAIL_TO_FETCH(HttpStatus.BAD_REQUEST, "[Type: %s]정보조회에 실패하였습니다."),
  DOES_NOT_MATCH_CUR_PASSWORD(HttpStatus.BAD_REQUEST, "현재 비밀번호가 일치하지 않습니다."),
  FAIL_TO_CREATE_DIRECTORY(HttpStatus.BAD_REQUEST, "디렉토리 생성에 실패하였습니다.");

  private final HttpStatus status;
  private final String message;

}
