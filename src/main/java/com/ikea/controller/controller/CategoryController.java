package com.ikea.controller.controller;

import com.ikea.entity.category.MainCategory;
import com.ikea.exception.ApiException;
import com.ikea.service.category.MainCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {

  private final MainCategoryService mainCategoryService;

  @GetMapping("/main/register")
  public String mainCategoryRegister() {
    return "category/main/register";
  }

  @GetMapping("/main/list")
  public String mainCategoryList() {
    return "category/main/list";
  }

  @GetMapping("/main/modify/{id}")
  public String modifyMainCategory(@PathVariable("id") String id, Model model) throws ApiException {
    model.addAttribute("mainCategory", mainCategoryService.findOne(MainCategory.builder().id(id).build()));
    return "category/main/modify";
  }

  @GetMapping("/sub/register")
  public String subCategoryRegister() {
    return "category/sub/register";
  }

}
