package com.ikea.controller;

import com.ikea.common.BaseResponse;
import com.ikea.entity.category.MainCategory;
import com.ikea.exception.ApiException;
import com.ikea.service.category.MainCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/main-category")
@RequiredArgsConstructor
public class MainCategoryRestController {

  private final MainCategoryService categoryService;

  @GetMapping("list")
  public ResponseEntity<?> getCategoryList() throws ApiException {
    return new ResponseEntity<>(categoryService.findAll(MainCategory.builder().build()), HttpStatus.OK);
  }

  @PostMapping("")
  public ResponseEntity<?> createCategory(@RequestBody MainCategory category) throws ApiException {
    categoryService.create(category);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.CREATED), HttpStatus.CREATED);
  }

  @PutMapping("{id}")
  public ResponseEntity<?> modifyCategory(@PathVariable("id") String id, @RequestBody MainCategory category) throws ApiException {
    category.setId(id);
    categoryService.modify(category);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> removeCategory(@PathVariable("id") String id) throws ApiException {
    categoryService.remove(MainCategory.builder().id(id).build());
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

}
