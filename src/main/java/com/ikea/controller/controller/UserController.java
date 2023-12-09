package com.ikea.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

  @GetMapping("profile")
  public String profile() {
    return "/user/profile";
  }

  @GetMapping("find-username")
  public String findUsername() {
    return "/user/find-username";
  }

  @GetMapping("find-password")
  public String findPassword() {
    return "/user/find-password";
  }

}
