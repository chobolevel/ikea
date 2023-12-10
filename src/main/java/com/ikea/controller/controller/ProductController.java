package com.ikea.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("product")
public class ProductController {

  @GetMapping("list")
  public String productList() {
    return "/product/list";
  }

  @GetMapping("register")
  public String registerProduct() {
    return "/product/register";
  }

}
