package com.example.school.User;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, String> {

  Optional<User> findUserByLoginAndPassword(String login, char[] password);

}
