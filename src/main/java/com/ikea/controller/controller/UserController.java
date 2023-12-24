package com.ikea.controller.controller;

import com.ikea.entity.user.User;
import com.ikea.exception.ApiException;
import com.ikea.security.user.CustomUserDetails;
import com.ikea.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("profile")
  public String profile(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) throws ApiException {
    User user = userService.findOne(User.builder().id(customUserDetails.getUser().getId()).build());
    model.addAttribute("user", user);
    return "user/profile";
  }

  @GetMapping("find-username")
  public String findUsername() {
    return "user/find-username";
  }

  @GetMapping("find-password")
  public String findPassword() {
    return "user/find-password";
  }

}
