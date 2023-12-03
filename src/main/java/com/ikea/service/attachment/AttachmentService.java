package com.ikea.service.attachment;

import com.ikea.entity.attachment.Attachment;
import com.ikea.exception.ApiException;

import java.util.List;

public interface AttachmentService {

  void create(List<Attachment> attachmentList) throws ApiException;

  void modify(Attachment attachment) throws ApiException;

  void remove(Attachment attachment) throws ApiException;

}
