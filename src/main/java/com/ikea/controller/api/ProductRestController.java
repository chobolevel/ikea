package com.ikea.controller.api;

import com.ikea.common.BaseResponse;
import com.ikea.entity.product.Product;
import com.ikea.exception.ApiException;
import com.ikea.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductRestController {

  private final ProductService productService;

  @GetMapping("list")
  public ResponseEntity<?> getProductList(Product product) throws ApiException {
    return new ResponseEntity<>(productService.findAll(product), HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<?> getProductDetail(@PathVariable("id") String id) throws ApiException {
    return new ResponseEntity<>(productService.findOne(Product.builder().id(id).build()), HttpStatus.OK);
  }

  @PostMapping("")
  public ResponseEntity<?> createProduct(@RequestPart Product product, @RequestPart List<MultipartFile> uploadFiles) throws ApiException, IOException {
    productService.create(product, uploadFiles);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.CREATED), HttpStatus.CREATED);
  }

  @PostMapping("add-cart")
  public ResponseEntity<?> addCart(HttpServletResponse res, @CookieValue(value = "cart", required = false) String cart, @RequestBody Product product) throws ApiException {
    if(cart == null) {
      Cookie cookie = new Cookie("cart", product.getId());
      cookie.setMaxAge(60 * 60 * 24);
      res.addCookie(cookie);
    }
    List<String> cartList = Arrays.asList(cart.split("/"));
    cartList.add(product.getId());
    Cookie cookie = new Cookie("cart", cartList.stream().collect(Collectors.joining("/")));
    cookie.setMaxAge(60 * 60 * 24);
    res.addCookie(cookie);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

  @PutMapping("{id}")
  public ResponseEntity<?> modifyProduct(@PathVariable("id") String id, @RequestBody Product product) throws ApiException {
    product.setId(id);
    productService.modify(product);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> removeProduct(@PathVariable("id") String id) throws ApiException {
    productService.remove(Product.builder().id(id).build());
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

}
