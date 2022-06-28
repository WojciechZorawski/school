package com.example.school.User;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/login")
  public UserResponseDTO getUserByLoginAndPassword(@RequestParam String login, @RequestParam char[] password) {
    UserResponseDTO user = userService.getUserByLoginAndPassword(login, password);
    return user;
  }

}
