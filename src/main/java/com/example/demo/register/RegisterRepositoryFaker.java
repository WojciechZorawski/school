package com.example.demo.register;

import static com.example.demo.register.RegisterFactory.register2Maker;
import static com.example.demo.register.RegisterFactory.registerMaker;

import java.util.List;
import java.util.UUID;

public class RegisterRepositoryFaker {

  public static List<Register> registers = List.of(registerMaker(), register2Maker());

  public List<Register> findAll() {
    return registers;
  }

  public Register findById(UUID id) throws ClassNotFoundException {
    Register register = registers.stream()
        .filter(d -> d.getId() == id)
        .findFirst()
        .orElseThrow(() -> new ClassNotFoundException("Nie ma takiego dziennika"));
    return register;

  }
}