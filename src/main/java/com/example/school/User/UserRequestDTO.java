package com.example.school.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

  private String name;
  private String lastName;
  private String login;
  private char[] password;
  private UserRole userRole;

  public User toEntity() {
    return User.builder()
               .name(name)
               .lastName(lastName)
               .login(login)
               .password(password)
               .userRole(userRole)
               .build();
  }

}
