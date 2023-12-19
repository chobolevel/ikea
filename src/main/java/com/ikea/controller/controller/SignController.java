package com.ikea.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sign")
public class SignController {

  @GetMapping("up")
  public String signUp() {
    return "sign/up";
  }

  @GetMapping("in")
  public String signIn(@RequestParam(value = "errorMessage", required = false) String errorMessage, Model model) {
    model.addAttribute("errorMessage", errorMessage);
    return "sign/in";
  }

}
