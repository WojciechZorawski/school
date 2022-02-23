package com.example.demo.student;

import static com.example.demo.grade.GradeFactory.grade2Maker;
import static com.example.demo.grade.GradeFactory.grade3Maker;
import static com.example.demo.grade.GradeFactory.gradeMaker;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.UUID;

public class StudentFactory {

  public static Student studentMaker() {
    Student newStudent = Student.builder()
        .id(UUID.fromString("043b6ffc-d963-4a00-99ac-4a2d6738dce0"))
        .name("Wojtek")
        .lastName("Zorawski")
        .email("jakisemail@pl")
        .dateOfBirth(LocalDate.of(2022, Month.APRIL, 11))
        .age(12)
        .listOfGrades(List.of(gradeMaker()))
        .build();
    return newStudent;
  }

  public static Student student2Maker() {
    Student newStudent2 = Student.builder()
        .id(UUID.fromString("54de8239-e273-4de6-b323-49392e1fe69e"))
        .name("Asia")
        .lastName("Warchulska")
        .email("asia@pl")
        .dateOfBirth(LocalDate.of(2021, Month.JANUARY, 18))
        .age(21)
        .listOfGrades(List.of(gradeMaker(), grade2Maker()))
        .build();
    return newStudent2;
  }

  public static Student student3Maker() {
    Student newStudent3 = Student.builder()
        .id(UUID.fromString("ea365a0c-0fc0-4585-b54d-057296c8eead"))
        .name("Katarzyna")
        .lastName("Branicka")
        .email("kasia@interia.pl")
        .dateOfBirth(LocalDate.of(2019, Month.AUGUST, 26))
        .age(31)
        .listOfGrades(List.of(gradeMaker(), grade2Maker(), grade3Maker()))
        .build();
    return newStudent3;
  }

}

