package com.ikea.service.attachment.impl;

import com.ikea.entity.attachment.Attachment;
import com.ikea.enums.ApiExceptionType;
import com.ikea.exception.ApiException;
import com.ikea.mapper.attachment.AttachmentMapper;
import com.ikea.service.attachment.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

  private final AttachmentMapper attachmentMapper;

  @Override
  public void create(List<Attachment> attachmentList) throws ApiException {
    for(Attachment attachment : attachmentList) {
      if(attachment.getProductOptionId().isEmpty()) {
        throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "productOptionId", "String");
      } else if(attachment.getName().isEmpty()) {
        throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "name", "String");
      }
      attachment.setId(UUID.randomUUID().toString());
    }
    attachmentMapper.creates(attachmentList);
  }

  @Override
  public void modify(Attachment attachment) throws ApiException {
    if(attachment.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    attachmentMapper.modify(attachment);
  }

  @Override
  public void remove(Attachment attachment) throws ApiException {
    if(attachment.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    attachmentMapper.remove(attachment);
  }
}
