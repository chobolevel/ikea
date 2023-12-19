package com.ikea.entity.base;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailEntity {

  private String to;
  private String username;
  private String password;
  private String authNum;

}
