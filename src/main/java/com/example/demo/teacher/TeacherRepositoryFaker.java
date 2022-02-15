package com.example.demo.teacher;

import static com.example.demo.teacher.TeacherFactory.teacher2Maker;
import static com.example.demo.teacher.TeacherFactory.teacherMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeacherRepositoryFaker {

  public List<Teacher> teachers = new ArrayList<>();

  public TeacherRepositoryFaker() {
    teachers.add(teacherMaker());
    teachers.add(teacher2Maker());
  }

  public List<Teacher> findAll() {
    return teachers;
  }

  public Teacher findById(UUID id) throws ClassNotFoundException {
    Teacher foundTeacher = teachers.stream()
        .filter(n -> n.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new ClassNotFoundException("Nie ma takiego nauczyciela"));
    return foundTeacher;

  }

  public Teacher save(Teacher entity) throws ClassNotFoundException {
    teachers.add(entity);
    Teacher byId = findById(entity.getId());
    return byId;

  }

  public void deleteById(UUID id) throws IllegalArgumentException {
    Teacher deletedTeacher;
    try {
      deletedTeacher = findById(id);
    } catch (ClassNotFoundException e) {
      throw new IllegalArgumentException("Bledne id");
    }
    teachers.remove(deletedTeacher);
  }
}


