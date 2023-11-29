package com.ikea.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ikea.entity.BaseEntity;
import com.ikea.enums.UserRoleType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

  private String id;
  private String username;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;
  private String name;
  private String email;
  private String address;
  private String mobile;
  private UserRoleType role;

}
