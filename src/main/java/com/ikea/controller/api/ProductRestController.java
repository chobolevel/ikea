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

import java.io.IOException;
import java.util.List;

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
