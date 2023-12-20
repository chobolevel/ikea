package com.ikea.mapper.user;

import com.ikea.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  void create(User user);

  User findOne(User user);

  void modify(User user);

  void updateResignYnById(User user);

}
