package com.ikea.controller.api;

import com.ikea.common.BaseResponse;
import com.ikea.entity.product.Product;
import com.ikea.enums.ApiExceptionType;
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

  @PostMapping("add-dibs")
  public ResponseEntity<?> addDibs(HttpServletResponse res, @CookieValue(value = "dibs", required = false) String dibs, @RequestBody Product product) throws ApiException {
    if(product.getId() == null || product.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    Cookie cookie;
    if(dibs == null) {
      cookie = new Cookie("dibs", product.getId());
      cookie.setPath("/");
      cookie.setMaxAge(60 * 60 * 24);
      res.addCookie(cookie);
    } else {
      String[] splitArr = dibs.split("/");
      if(splitArr.length >= 10) {
        String collect = Arrays.stream(splitArr).limit(9).collect(Collectors.joining("/"));
        cookie = new Cookie("dibs", product.getId() + "/" + collect);
      } else {
        cookie = new Cookie("dibs", product.getId() + "/" + dibs);
      }
    }
    cookie.setMaxAge(60 * 60 * 24);
    cookie.setPath("/");
    res.addCookie(cookie);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

  @PostMapping("add-cart")
  public ResponseEntity<?> addCart(HttpServletResponse res, @CookieValue(value = "cart", required = false) String cart, @RequestBody Product product) throws ApiException {
    if(product.getId() == null || product.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    if(product.getSelectedOptionId() == null || product.getSelectedOptionId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "selectedOptionId", "String");
    }
    if(product.getQuantity() <= 0) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "quantity", "int");
    }
    Cookie cookie;
    if(cart == null) {
      cookie = new Cookie("cart", product.getId() + "&" + product.getSelectedOptionId() + "&" + product.getQuantity());
    } else {
      String[] splitArr = cart.split("/");
      if(splitArr.length >= 10) {
        String collect = Arrays.stream(splitArr).limit(9).collect(Collectors.joining("/"));
        cookie = new Cookie("cart", product.getId() + "&" + product.getSelectedOptionId() + "&" + product.getQuantity() + "/" + collect);
      } else {
        cookie = new Cookie("cart", product.getId() + "&" + product.getSelectedOptionId() + "&" + product.getQuantity() + "/" + cart);
      }
    }
    cookie.setPath("/");
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
