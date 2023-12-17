package com.ikea.mapper.attachment;

import com.ikea.entity.attachment.Attachment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttachmentMapper {

  void creates(List<Attachment> attachmentList);

  void modify(Attachment attachment);

  void remove(Attachment attachment);

  void deleteByProductOptionId(Attachment attachment);

}
