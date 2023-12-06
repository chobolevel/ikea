package com.ikea.controller.api;

import com.ikea.common.BaseResponse;
import com.ikea.entity.user.User;
import com.ikea.exception.ApiException;
import com.ikea.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sign")
@RequiredArgsConstructor
public class SignRestController {

  private final UserService userService;

  @PostMapping("up")
  public ResponseEntity<?> createUser(@RequestBody User user) throws ApiException {
    userService.create(user);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.CREATED), HttpStatus.CREATED);
  }

}
