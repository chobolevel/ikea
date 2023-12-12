package com.ikea.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("product")
public class ProductController {

  @GetMapping("list")
  public String productList(@RequestParam(value = "mainCategoryCode", required = false) String mainCategoryCode,
                            @RequestParam(value = "subCategoryCode", required = false) String subCategoryCode,
                            @RequestParam(value = "keyword", required = false) String keyword,
                            Model model) {
    model.addAttribute("mainCategoryCode", mainCategoryCode);
    model.addAttribute("subCategoryCode", subCategoryCode);
    model.addAttribute("keyword", keyword);
    return "/product/list";
  }

  @GetMapping("{id}")
  public String productDetail(@PathVariable("id") String productId, Model model) {
    model.addAttribute("productId", productId);
    return "/product/detail";
  }

  @GetMapping("register")
  public String registerProduct() {
    return "/product/register";
  }

}
