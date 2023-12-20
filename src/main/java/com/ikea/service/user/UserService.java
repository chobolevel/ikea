package com.ikea.service.user;

import com.ikea.entity.user.User;
import com.ikea.exception.ApiException;

import javax.mail.MessagingException;

public interface UserService {

  void create(User user) throws ApiException;

  User findOne(User user) throws ApiException;

  void modify(User user) throws ApiException;

  void changePassword(User user) throws ApiException;

  void findUsername(User user) throws ApiException, MessagingException;

  void findPassword(User user) throws ApiException, MessagingException;

  void sendEmailAuthNum(User user) throws ApiException, MessagingException;

  void authenticateEmailAuthNum(User user) throws ApiException;

  void resignUser(User user) throws ApiException;

}
