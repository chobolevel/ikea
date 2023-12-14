package com.ikea.controller.api;

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

  @GetMapping("list")
  public ResponseEntity<?> getProductReviewList(ProductReview productReview) throws ApiException {
    return new ResponseEntity<>(productReviewService.findAll(productReview), HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<?> getProductReview(@PathVariable("id") String id) throws ApiException {
    return new ResponseEntity<>(productReviewService.findOne(ProductReview.builder().id(id).build()), HttpStatus.OK);
  }

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
