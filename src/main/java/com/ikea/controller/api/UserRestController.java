package com.ikea.controller.api;

import com.ikea.common.BaseResponse;
import com.ikea.entity.user.User;
import com.ikea.enums.ApiExceptionType;
import com.ikea.exception.ApiException;
import com.ikea.security.user.CustomUserDetails;
import com.ikea.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {

  private final UserService userService;

  @GetMapping("me")
  public ResponseEntity<?> getMyUser(@AuthenticationPrincipal CustomUserDetails userDetails) throws ApiException {
    if(userDetails == null) {
      throw new ApiException(ApiExceptionType.FAIL_TO_FETCH, "User");
    }
    String id = userDetails.getUser().getId();
    User findUser = userService.findOne(User.builder().id(id).build());
    return new ResponseEntity<>(findUser, HttpStatus.OK);
  }

  @PostMapping("find-username")
  public ResponseEntity<?> findUsername(@RequestBody User user) throws ApiException, MessagingException {
    userService.findUsername(user);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

  @PostMapping("find-password")
  public ResponseEntity<?> findPassword(@RequestBody User user) throws ApiException, MessagingException {
    userService.findPassword(user);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

  @PostMapping("send-auth-num")
  public ResponseEntity<?> sendAuthNum(@RequestBody User user) throws ApiException, MessagingException {
    userService.sendEmailAuthNum(user);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

  @PostMapping("certificate-auth-num")
  public ResponseEntity<?> certificateAuthNum(@RequestBody User user) throws ApiException {
    userService.authenticateEmailAuthNum(user);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

  @PutMapping("{id}")
  public ResponseEntity<?> modifyUser(@PathVariable("id") String id, @RequestBody User user) throws ApiException {
    user.setId(id);
    userService.modify(user);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

  @PutMapping("change-password")
  public ResponseEntity<?> changePassword(@RequestBody User user) throws ApiException {
    userService.changePassword(user);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> resignUser(@PathVariable("id") String id) throws ApiException {
    userService.resignUser(User.builder().id(id).build());
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

}
