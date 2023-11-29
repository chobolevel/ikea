package com.ikea.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserRoleType implements BaseEnum {

  ROLE_USER("서비스 사용자"),
  ROLE_ADMIN("서비스 관리자");

  private String name;

  @Override
  public String getCode() {
    return name();
  }

  @Override
  public String getName() {
    return name;
  }
}
