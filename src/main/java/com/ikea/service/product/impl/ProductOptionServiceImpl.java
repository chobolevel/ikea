package com.ikea.service.product.impl;

import com.ikea.entity.attachment.Attachment;
import com.ikea.entity.product.ProductOption;
import com.ikea.enums.ApiExceptionType;
import com.ikea.exception.ApiException;
import com.ikea.mapper.attachment.AttachmentMapper;
import com.ikea.mapper.product.ProductOptionMapper;
import com.ikea.service.product.ProductOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductOptionServiceImpl implements ProductOptionService {

  private final AttachmentMapper attachmentMapper;

  private final ProductOptionMapper productOptionMapper;
  @Value("${spring.servlet.multipart.location}")
  private String basePath;

  @Override
  public void create(ProductOption productOption, List<MultipartFile> uploadFiles) throws ApiException, IOException {
    if(productOption.getProductId() == null || productOption.getProductId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "productId", "String");
    } else if(productOption.getColor() == null || productOption.getColor().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "color", "String");
    } else if(productOption.getStock() <= 0) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "stock", "int");
    } else if(productOption.getPrice() <= 0) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "price", "int");
    }
    String productId = productOption.getProductId();
    String productOptionId = UUID.randomUUID().toString();
    productOption.setId(productOptionId);
    // List for attachmentMapper creates
    List<Attachment> attachmentList = uploadFiles.stream().map((file) -> new Attachment(UUID.randomUUID().toString(), productOptionId, file.getOriginalFilename())).toList();
    File mainFolder = new File(String.format("%s\\%s", basePath, productId));
    boolean isMainFolderExists = mainFolder.exists();
    boolean isMainFolderCreate = false;
    if(!isMainFolderExists) {
       isMainFolderCreate = mainFolder.mkdir();
    }
    File subFolder = new File(String.format("%s\\%s\\%s", basePath, productId, productOptionId));
    boolean isSubFolderExists = subFolder.exists();
    boolean isSubFolderCreate = false;
    if(!isSubFolderExists) {
      isSubFolderCreate = subFolder.mkdir();
    }
    boolean isFolderCreate = (isMainFolderCreate || isMainFolderExists) && (isSubFolderCreate || isSubFolderExists);
    if(isFolderCreate) {
      for(MultipartFile file : uploadFiles) {
        file.transferTo(new File(String.format("%s\\%s\\%s", productId, productOptionId, file.getOriginalFilename())));
      }
      productOptionMapper.create(productOption);
      attachmentMapper.creates(attachmentList);
    } else {
        throw new ApiException(ApiExceptionType.FAIL_TO_CREATE_DIRECTORY);
    }
  }

  @Override
  public void modify(ProductOption productOption, List<MultipartFile> uploadFiles) throws ApiException, IOException {
    if(productOption.getId() == null || productOption.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    File folder = new File(String.format("%s\\%s\\%s", basePath, productOption.getProductId(), productOption.getId()));
    if(folder.exists()) {
      // 해당 패키지 존재하는 경우
      // 1. attachment 레코드 삭제 productOptionId 기반
      if(folder.listFiles() != null) {
        for(File file : folder.listFiles()) {
          file.delete();
        }
      }
      folder.delete();
      // 모두 삭제한 후 새롭게 생성
      attachmentMapper.deleteByProductOptionId(Attachment.builder().productOptionId(productOption.getId()).build());
    }
    if(uploadFiles != null && !uploadFiles.isEmpty()) {
      folder.mkdir();
      for(MultipartFile file : uploadFiles) {
        file.transferTo(new File(String.format("%s\\%s\\%s", productOption.getProductId(), productOption.getId(), file.getOriginalFilename())));
      }
      List<Attachment> attachmentList = uploadFiles.stream().map((file) -> new Attachment(UUID.randomUUID().toString(), productOption.getId(), file.getOriginalFilename())).toList();
      attachmentMapper.creates(attachmentList);
    }
    productOptionMapper.modify(productOption);
  }

  @Override
  public void remove(ProductOption productOption) throws ApiException {
    if(productOption.getId() == null || productOption.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    productOptionMapper.remove(productOption);
  }
}
