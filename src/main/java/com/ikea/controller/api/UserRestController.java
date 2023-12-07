package com.ikea.controller.api;

import com.ikea.common.BaseResponse;
import com.ikea.entity.user.User;
import com.ikea.exception.ApiException;
import com.ikea.security.user.CustomUserDetails;
import com.ikea.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {

  private final UserService userService;

  @GetMapping("me")
  public ResponseEntity<?> getMyUser(@AuthenticationPrincipal CustomUserDetails userDetails) throws ApiException {
    String id = userDetails.getUser().getId();
    User findUser = userService.findOne(User.builder().id(id).build());
    return new ResponseEntity<>(findUser, HttpStatus.OK);
  }

  @PutMapping("{id}")
  public ResponseEntity<?> modifyUser(@PathVariable("id") String id, @RequestBody User user) throws ApiException {
    user.setId(id);
    userService.modify(user);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> removeUser(@PathVariable("id") String id) throws ApiException {
    userService.remove(User.builder().id(id).build());
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

}
