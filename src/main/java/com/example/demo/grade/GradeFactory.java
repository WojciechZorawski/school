package com.example.demo.grade;

import java.time.LocalDate;

public class GradeFactory {

  public static Grade gradeMaker(Long id) {
    Grade newGrade = Grade.builder()
        .id(id)
        .date(LocalDate.of(2022, 2, 10))
        .grade(3)
        .subject("Jezyk polski")
        .build();
    return newGrade;

  }

  public static Grade grade2Maker(Long id) {
    Grade newGrade2 = Grade.builder()
        .id(id)
        .date(LocalDate.of(2022, 2, 9))
        .grade(4)
        .subject("Matematyka")
        .build();
    return newGrade2;

  }

  public static Grade grade3Maker(Long id) {
    Grade newGrade3 = Grade.builder()
        .id(id)
        .date(LocalDate.of(2022, 2, 8))
        .grade(5)
        .subject("Jezyk polski")
        .build();
    return newGrade3;

  }

}
