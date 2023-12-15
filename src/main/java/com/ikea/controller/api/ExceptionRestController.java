package com.ikea.controller.api;

import com.ikea.common.BaseResponse;
import com.ikea.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionRestController {

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<?> ApiException(ApiException exception) {
    BaseResponse baseResponse = BaseResponse.getInstance(exception.getType().getStatus(), exception.getMessage());
    return new ResponseEntity<>(baseResponse, exception.getType().getStatus());
  }

}
