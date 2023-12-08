package com.ikea.service.user.impl;

import com.ikea.entity.user.User;
import com.ikea.enums.ApiExceptionType;
import com.ikea.enums.UserRoleType;
import com.ikea.exception.ApiException;
import com.ikea.mapper.user.UserMapper;
import com.ikea.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public void create(User user) throws ApiException {
    if(user.getUsername().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "String", "username");
    } else if(user.getPassword().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "String", "password");
    } else if(user.getName().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "String", "name");
    } else if(user.getEmail().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "String", "email");
    } else if(user.getAddress().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "String", "address");
    } else if(user.getMobile().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "String", "mobile");
    }
    user.setId(UUID.randomUUID().toString());
    user.setRole(UserRoleType.ROLE_USER);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userMapper.create(user);
  }

  @Override
  public User findOne(User user) throws ApiException {
    return userMapper.findOne(user);
  }

  @Override
  public void modify(User user) throws ApiException {
    if(user.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "String", "id");
    }
    userMapper.modify(user);
  }

  @Override
  public void changePassword(User user) throws ApiException {
    if(user.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    } else if(user.getCurPassword().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "curPassword", "String");
    } else if(user.getPassword().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "password", "String");
    }
    User findUser = userMapper.findOne(User.builder().id(user.getId()).build());
    if(findUser == null) {
      throw new ApiException(ApiExceptionType.FAIL_TO_FETCH, "User");
    }
    String findUserCurPassword = findUser.getPassword();
    boolean isPwMatch = passwordEncoder.matches(user.getCurPassword(), findUserCurPassword);
    if(!isPwMatch) {
      throw new ApiException(ApiExceptionType.DOES_NOT_MATCH_CUR_PASSWORD);
    }
    userMapper.modify(User.builder()
        .id(user.getId())
        .password(passwordEncoder.encode(user.getPassword()))
        .build());
  }

  @Override
  public void remove(User user) throws ApiException {
    if(user.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "String", "id");
    }
    userMapper.remove(user);
  }

}
