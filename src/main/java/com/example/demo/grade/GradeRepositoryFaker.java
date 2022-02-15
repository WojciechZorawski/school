package com.example.demo.grade;

import static com.example.demo.grade.GradeFactory.grade2Maker;
import static com.example.demo.grade.GradeFactory.grade3Maker;
import static com.example.demo.grade.GradeFactory.gradeMaker;

import java.util.List;
import java.util.UUID;

public class GradeRepositoryFaker {

  public static List<Grade> grades = List.of(gradeMaker(), grade2Maker(), grade3Maker());

  public List<Grade> findAll() {
    return grades;
  }

  public Grade findById(UUID id) throws ClassNotFoundException {
    Grade grade = grades.stream()
        .filter(o -> o.getId() == id)
        .findFirst()
        .orElseThrow(() -> new ClassNotFoundException("Nie ma takiej oceny"));
    return grade;
  }

}
