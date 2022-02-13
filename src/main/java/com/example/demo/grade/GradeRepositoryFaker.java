package com.example.demo.grade;

import static com.example.demo.grade.GradeFactory.grade2Maker;
import static com.example.demo.grade.GradeFactory.grade3Maker;
import static com.example.demo.grade.GradeFactory.gradeMaker;

import java.util.List;

public class GradeRepositoryFaker {

  public static List<Grade> grades = List.of(gradeMaker(1L), grade2Maker(2L), grade3Maker(3L));

  public List<Grade> findAll() {
    return grades;
  }

  public Grade findById(Long id) throws ClassNotFoundException {
    Grade grade = grades.stream()
        .filter(o -> o.getId() == id)
        .findFirst()
        .orElseThrow(() -> new ClassNotFoundException("Nie ma takiej oceny"));
    return grade;
  }

}
