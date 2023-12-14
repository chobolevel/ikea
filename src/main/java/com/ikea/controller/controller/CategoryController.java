package com.ikea.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("category")
public class CategoryController {

  @GetMapping("/main/register")
  public String mainCategoryRegister() {
    return "/category/main/register";
  }

}
