package com.example.demo.teacher;

import static com.example.demo.teacher.TeacherFactory.teacher2Maker;
import static com.example.demo.teacher.TeacherFactory.teacherMaker;

import java.util.List;
import java.util.UUID;

public class TeacherRepositoryFaker {

  public static List<Teacher> teachers = List.of(teacherMaker(), teacher2Maker());

  public List<Teacher> findAll() {
    return teachers;
  }

  public static Teacher findById(UUID id) throws ClassNotFoundException {
    Teacher teacher = teachers.stream()
        .filter(n -> n.getId() == id)
        .findFirst()
        .orElseThrow(() -> new ClassNotFoundException("Nie ma takiego nauczyciela"));
    return teacher;

  }
}


