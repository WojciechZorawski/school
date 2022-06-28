package com.example.school.User;

import com.example.school.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  UserResponseDTO getUserByLoginAndPassword(String login, char[] password) {
    User takenUser = userRepository.findUserByLoginAndPassword(login, password)
                                   .orElseThrow(() -> new BusinessLogicException("Bledny login lub haslo"));
    return takenUser.toResponseDto();
  }

}
