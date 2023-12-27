package com.ikea.controller.controller;

import com.ikea.entity.category.MainCategory;
import com.ikea.entity.product.Product;
import com.ikea.exception.ApiException;
import com.ikea.service.category.MainCategoryService;
import com.ikea.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
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
    if(dibs == null) {
      model.addAttribute("productList", new ArrayList<>());
    } else {
      List<String> dibsIdList = Arrays.asList(dibs.split("/"));
      List<Product> productList = productService.findAll(Product.builder().productIdList(dibsIdList).build());
      model.addAttribute("productList", productList);
    }
    return "product/dibs";
  }

  @GetMapping("cart")
  public String cart(@CookieValue(value = "cart", required = false) String cart, Model model) throws ApiException {
    if(cart == null) {
      model.addAttribute("productList", new ArrayList<>());
    } else {
      List<String> cartSplitList = Arrays.asList(cart.split("/"));
      List<List<String>> cartList = cartSplitList.stream().map((c) -> Arrays.asList(c.split("&"))).toList();
      List<String> productIdList = cartList.stream().map((c) -> c.get(0)).toList();
      List<Product> findProductList = productService.findAll(Product.builder().productIdList(productIdList).build());
      List<Product> productList = findProductList.stream().map((p) -> {
        String productId = p.getId();
        List<String> matchedStringList = cartList.stream().filter((c) -> c.get(0).equals(productId)).findFirst().orElseThrow();
        p.setSelectedOptionId(matchedStringList.get(1));
        p.setQuantity(Integer.parseInt(matchedStringList.get(2)));
        return p;
      }).toList();
      log.debug("cartSplitList: {}", cartSplitList);
      log.debug("cartList: {}", cartList);
      log.debug("findProductList: {}", findProductList);
      model.addAttribute("productList", productList);
    }
    return "product/cart";
  }

}
