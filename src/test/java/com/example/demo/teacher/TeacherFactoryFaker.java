package com.example.demo.teacher;

import com.github.javafaker.Faker;
import java.util.Random;
import java.util.UUID;

public class TeacherFactoryFaker {

  private static final Faker FAKER = Faker.instance(new Random(81));

  static TeacherDTO toDto(Teacher entity){
    return TeacherDTO.builder()
        .name(entity.getName())
        .lastName(entity.getLastName())
        .age(entity.getAge())
        .profession(entity.getProfession())
        .build();
  }

  static TeacherDTO getValidTeacherDto() {
    return TeacherDTO.builder()
                     .name(FAKER.name().name())
                     .lastName(FAKER.name().lastName())
                     .age(FAKER.number().numberBetween(18, 99))
                     .profession(FAKER.name().name())
                     .build();
  }

  static Teacher getValidTeacherEntity() {
    return Teacher.builder()
                  .id(UUID.randomUUID())
                  .name(FAKER.name().name())
                  .lastName(FAKER.name().lastName())
                  .age(FAKER.number().numberBetween(18, 99))
                  .profession(FAKER.name().name())
                  .build();
  }

  static TeacherDTO getInvalidTeacherDto() {
    return TeacherDTO.builder()
                     .name(null)
                     .lastName(null)
                     .age(FAKER.number().numberBetween(1, 124))
                     .profession(FAKER.name().name())
                     .build();
  }
}