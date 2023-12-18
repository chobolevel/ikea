package com.ikea.controller.controller;

import com.ikea.entity.product.ProductOption;
import com.ikea.exception.ApiException;
import com.ikea.service.product.ProductOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("product-option")
@RequiredArgsConstructor
public class ProductOptionController {

  private final ProductOptionService productOptionService;

  @GetMapping("/register/{id}")
  public String registerProductOption(@PathVariable("id") String productId, Model model) {
    model.addAttribute("productId", productId);
    return "product/option/register";
  }

  @GetMapping("/modify/{id}")
  public String modifyProductOption(@PathVariable("id") String id, Model model) throws ApiException {
    ProductOption findProductOption = productOptionService.findOne(ProductOption.builder().id(id).build());
    model.addAttribute("productOption", findProductOption);
    return "product/option/modify";
  }

}
