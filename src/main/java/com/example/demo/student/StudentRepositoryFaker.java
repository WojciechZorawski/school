package com.example.demo.student;

import static com.example.demo.student.StudentFactory.student2Maker;
import static com.example.demo.student.StudentFactory.student3Maker;
import static com.example.demo.student.StudentFactory.studentMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class StudentRepositoryFaker {

  List<Student> students = new ArrayList<>();

  StudentRepositoryFaker() {
    students.add(studentMaker());
    students.add(student2Maker());
    students.add(student3Maker());
  }

  List<Student> findAll() {
    return students;
  }

  Student findById(UUID id) throws ClassNotFoundException {
    Student student = students.stream()
                              .filter(s -> s.getId().equals(id))
                              .findFirst()
                              .orElseThrow(() -> new ClassNotFoundException("Nie ma takiego studenta"));
    return student;
  }

  Student save(Student entity) throws ClassNotFoundException {
    students.add(entity);
    Student byId = findById(entity.getId());
    return byId;
  }

  void deleteById(UUID id) {
    try {
      Student deletedStudent = findById(id);
      students.remove(deletedStudent);
    } catch (ClassNotFoundException e) {
      throw new IllegalArgumentException("Bledne id");
    }

  }

}
