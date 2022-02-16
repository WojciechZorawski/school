package com.example.demo.grade;

import java.time.LocalDate;
import java.util.UUID;

public class GradeFactory {

  public static Grade gradeMaker() {
    Grade newGrade = Grade.builder()
        .id(UUID.fromString("e09c2a63-2de3-4d07-9b4a-6072ad1561e6"))
        .date(LocalDate.of(2022, 2, 10))
        .grade(3)
        .subject("Jezyk polski")
        .build();
    return newGrade;

  }

  public static Grade grade2Maker() {
    Grade newGrade2 = Grade.builder()
        .id(UUID.randomUUID())
        .date(LocalDate.of(2022, 2, 9))
        .grade(4)
        .subject("Matematyka")
        .build();
    return newGrade2;

  }

  public static Grade grade3Maker() {
    Grade newGrade3 = Grade.builder()
        .id(UUID.randomUUID())
        .date(LocalDate.of(2022, 2, 8))
        .grade(5)
        .subject("Jezyk polski")
        .build();
    return newGrade3;

  }

}
