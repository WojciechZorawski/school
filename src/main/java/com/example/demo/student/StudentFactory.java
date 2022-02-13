package com.example.demo.student;

import static com.example.demo.grade.GradeFactory.grade2Maker;
import static com.example.demo.grade.GradeFactory.grade3Maker;
import static com.example.demo.grade.GradeFactory.gradeMaker;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class StudentFactory {

  public static Student studentMaker(Long id) {
    Student newStudent = Student.builder()
        .id(id)
        .name("Wojtek")
        .email("jakisemail@pl")
        .dateOfBirth(LocalDate.of(2022, Month.APRIL, 11))
        .age(12)
        .listOfGrades(List.of(gradeMaker(1L)))
        .build();
    return newStudent;
  }

  public static Student student2Maker(Long id) {
    Student newStudent2 = Student.builder()
        .id(id)
        .name("Asia")
        .email("asia@pl")
        .dateOfBirth(LocalDate.of(2021, Month.JANUARY, 18))
        .age(21)
        .listOfGrades(List.of(gradeMaker(1L), grade2Maker(2L)))
        .build();
    return newStudent2;
  }

  public static Student student3Maker(Long id) {
    Student newStudent3 = Student.builder()
        .id(id)
        .name("Katarzyna")
        .email("kasia@interia.pl")
        .dateOfBirth(LocalDate.of(2019, Month.AUGUST, 26))
        .age(31)
        .listOfGrades(List.of(gradeMaker(1L), grade2Maker(2L), grade3Maker(3L)))
        .build();
    return newStudent3;
  }

}

