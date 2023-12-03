package com.ikea.controller;

import com.ikea.common.BaseResponse;
import com.ikea.entity.product.ProductReview;
import com.ikea.exception.ApiException;
import com.ikea.service.product.ProductReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-review")
@RequiredArgsConstructor
public class ProductReviewRestController {

  private final ProductReviewService productReviewService;

  @PostMapping("")
  public ResponseEntity<?> createProductReview(@RequestBody ProductReview productReview) throws ApiException {
    productReviewService.create(productReview);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.CREATED), HttpStatus.CREATED);
  }

  @PutMapping("{id}")
  public ResponseEntity<?> modifyProductReview(@PathVariable("id") String id, @RequestBody ProductReview productReview) throws ApiException {
    productReview.setId(id);
    productReviewService.modify(productReview);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> removeProductReview(@PathVariable("id") String id) throws ApiException {
    productReviewService.remove(ProductReview.builder().id(id).build());
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

}
