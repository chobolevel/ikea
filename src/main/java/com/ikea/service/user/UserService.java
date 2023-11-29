package com.ikea.service.user;

import com.ikea.entity.user.User;
import com.ikea.exception.ApiException;

public interface UserService {

  void create(User user) throws ApiException;

  User findOne(User user) throws ApiException;

  void modify(User user) throws ApiException;

  void remove(User user) throws ApiException;

}
