package com.example.demo.grade;

import static com.example.demo.grade.GradeFactory.grade2Maker;
import static com.example.demo.grade.GradeFactory.grade3Maker;
import static com.example.demo.grade.GradeFactory.gradeMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class GradeRepositoryFaker {

  public static List<Grade> grades = new ArrayList<>();

  public GradeRepositoryFaker() {
    grades.add(gradeMaker());
    grades.add(grade2Maker());
    grades.add(grade3Maker());
  }

  public List<Grade> findAll() {
    return grades;
  }

  public Grade findById(UUID id) throws ClassNotFoundException {
    Grade foundGrade = grades.stream()
        .filter(o -> o.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new ClassNotFoundException("Nie ma takiej oceny"));
    return foundGrade;
  }

  public Grade save(Grade entity) throws ClassNotFoundException{
    grades.add(entity);
    Grade byId = findById(entity.getId());
    return byId;
  }

  public void deleteById(UUID id) throws IllegalArgumentException{
    Grade deletedGrade;
    try {
      deletedGrade = findById(id);
    } catch (ClassNotFoundException e)  {
      throw new IllegalArgumentException("Bledne id");
    }
    grades.remove(deletedGrade);
  }

}
