package com.example.school.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

  TEACHER("7777"),
  STUDENT("4507"),
  ADMIN("1010");

  private String code;
}
