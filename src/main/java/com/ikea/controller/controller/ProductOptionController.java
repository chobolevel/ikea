package com.ikea.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("product-option")
public class ProductOptionController {

  @GetMapping("register")
  public String registerProductOption(@RequestParam("productId") String productId, Model model) {
    model.addAttribute("productId", productId);
    return "/product/option/register";
  }

}
