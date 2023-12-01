package com.ikea.controller;

import com.ikea.common.BaseResponse;
import com.ikea.entity.category.SubCategory;
import com.ikea.exception.ApiException;
import com.ikea.service.category.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sub-category")
@RequiredArgsConstructor
public class SubCategoryRestController {

  private final SubCategoryService subCategoryService;

  @GetMapping("list")
  public ResponseEntity<?> getSubCategoryList() throws ApiException {
    return new ResponseEntity<>(subCategoryService.findAll(SubCategory.builder().build()), HttpStatus.OK);
  }

  @PostMapping("")
  public ResponseEntity<?> createSubCategory(@RequestBody SubCategory subCategory) throws ApiException {
    subCategoryService.create(subCategory);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.CREATED), HttpStatus.CREATED);
  }

  @PutMapping("{id}")
  public ResponseEntity<?> modifySubCategory(@PathVariable("id") String id, @RequestBody SubCategory subCategory) throws ApiException {
    subCategory.setId(id);
    subCategoryService.modify(subCategory);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> removeSubCategory(@PathVariable("id") String id) throws ApiException {
    subCategoryService.remove(SubCategory.builder().id(id).build());
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

}
