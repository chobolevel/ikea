package com.ikea.controller.controller;

import com.ikea.entity.category.MainCategory;
import com.ikea.entity.product.Product;
import com.ikea.exception.ApiException;
import com.ikea.service.category.MainCategoryService;
import com.ikea.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;
  private final MainCategoryService mainCategoryService;

  @GetMapping("list")
  public String productList(@RequestParam(value = "mainCategoryCode", required = false) String mainCategoryCode,
                            @RequestParam(value = "subCategoryCode", required = false) String subCategoryCode,
                            @RequestParam(value = "keyword", required = false) String keyword,
                            Model model) {
    model.addAttribute("mainCategoryCode", mainCategoryCode);
    model.addAttribute("subCategoryCode", subCategoryCode);
    model.addAttribute("keyword", keyword);
    return "product/list";
  }

  @GetMapping("{id}")
  public String productDetail(@PathVariable("id") String productId, Model model) {
    model.addAttribute("productId", productId);
    return "product/detail";
  }

  @GetMapping("register")
  public String registerProduct() {
    return "product/register";
  }

  @GetMapping("/modify/{id}")
  public String modifyProduct(@PathVariable("id") String productId, Model model) throws ApiException {
    Product findProduct = productService.findOne(Product.builder().id(productId).build());
    List<MainCategory> mainCategoryList = mainCategoryService.findAll(MainCategory.builder().build());
    model.addAttribute("product", findProduct);
    model.addAttribute("mainCategoryList", mainCategoryList);
    return "product/modify";
  }

  @GetMapping("dibs")
  public String dibs(@CookieValue(value = "dibs", required = false) String dibs, Model model) throws ApiException {
    List<String> dibsIdList = Arrays.asList(dibs.split("/"));
    List<Product> productList = productService.findAll(Product.builder().productIdList(dibsIdList).build());
    model.addAttribute("productList", productList);
    return "product/dibs";
  }

}
