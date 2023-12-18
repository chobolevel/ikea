package com.ikea.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sign")
public class SignController {

  @GetMapping("up")
  public String signUp() {
    return "sign/up";
  }

  @GetMapping("in")
  public String signIn() {
    return "sign/in";
  }

}
