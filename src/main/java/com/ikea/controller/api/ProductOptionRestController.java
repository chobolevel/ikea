package com.ikea.controller.api;

import com.ikea.common.BaseResponse;
import com.ikea.entity.product.ProductOption;
import com.ikea.exception.ApiException;
import com.ikea.service.product.ProductOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product-option")
@RequiredArgsConstructor
public class ProductOptionRestController {

  private final ProductOptionService productOptionService;

  @PostMapping("")
  public ResponseEntity<?> createProductOption(@RequestPart ProductOption productOption, @RequestPart List<MultipartFile> uploadFiles) throws ApiException, IOException {
    productOptionService.create(productOption, uploadFiles);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.CREATED), HttpStatus.CREATED);
  }

  @PutMapping("{id}")
  public ResponseEntity<?> modifyProductOption(@PathVariable("id") String id, @RequestBody ProductOption productOption) throws ApiException {
    productOption.setId(id);
    productOptionService.modify(productOption);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> removeProductOption(@PathVariable("id") String id) throws ApiException {
    productOptionService.remove(ProductOption.builder().id(id).build());
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

}
