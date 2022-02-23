package com.example.demo.register;

import static com.example.demo.register.RegisterFactory.register2Maker;
import static com.example.demo.register.RegisterFactory.registerMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class RegisterRepositoryFaker {

  public List<Register> registers = new ArrayList<>();

  public RegisterRepositoryFaker() {
    registers.add(registerMaker());
    registers.add(register2Maker());
  }

  public List<Register> findAll() {
    return registers;
  }

  public Register findById(UUID id) throws ClassNotFoundException {
    Register register = registers.stream()
                                 .filter(r -> r.getId().equals(id))
                                 .findFirst()
                                 .orElseThrow(() -> new ClassNotFoundException("Nie ma takiego dziennika"));
    return register;
  }

  public Register save(Register entity) throws ClassNotFoundException {
    registers.add(entity);
    Register byId = findById(entity.getId());
    return byId;
  }

  public void deleteById(UUID id) {
    try {
      Register deletedRegister = findById(id);
      registers.remove(deletedRegister);
    } catch (ClassNotFoundException e) {
      throw new IllegalArgumentException("Bledne id");
    }

  }

  public Optional<Register> findByTeacherId(UUID teacherId){
    Optional<Register> wantedRegister = registers.stream()
        .filter(register -> register.getTeacher().getId().equals(teacherId))
        .findFirst();
    return wantedRegister;
  }
}