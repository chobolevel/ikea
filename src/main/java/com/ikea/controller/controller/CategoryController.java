package com.ikea.controller.controller;

import com.ikea.entity.category.MainCategory;
import com.ikea.entity.category.SubCategory;
import com.ikea.exception.ApiException;
import com.ikea.service.category.MainCategoryService;
import com.ikea.service.category.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {

  private final MainCategoryService mainCategoryService;
  private final SubCategoryService subCategoryService;

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

  @GetMapping("/sub/list")
  public String subCategoryList(@RequestParam("mainCategoryId") String mainCategoryId, Model model) throws ApiException {
    List<SubCategory> subCategoryList = subCategoryService.findAll(SubCategory.builder().mainCategoryId(mainCategoryId).build());
    model.addAttribute("subCategoryList", subCategoryList);
    return "category/sub/list";
  }

  @GetMapping("/sub/modify/{id}")
  public String modifySubCategory(@PathVariable("id") String id, Model model) throws ApiException {
    List<MainCategory> mainCategoryList = mainCategoryService.findAll(MainCategory.builder().build());
    SubCategory subCategory = subCategoryService.findOne(SubCategory.builder().id(id).build());
    model.addAttribute("mainCategoryList", mainCategoryList);
    model.addAttribute("subCategory", subCategory);
    return "category/sub/modify";
  }

}
