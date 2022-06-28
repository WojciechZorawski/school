package com.example.school.User;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private String name;
  private String lastName;
  @Id
  private String login;
  private char[] password;
  @Enumerated(EnumType.STRING)
  private UserRole userRole;

  public UserResponseDTO toResponseDto() {
    return UserResponseDTO.builder()
                          .name(name)
                          .lastName(lastName)
                          .token(generateUserToken(login, password, userRole))
                          .build();
  }

  private String generateUserToken(String login, char[] password, UserRole userRole){
    String token = password[0] + new StringBuilder(login).reverse().toString().toLowerCase() + userRole.getCode() + password[password.length-1];
    return token;
  }

}
