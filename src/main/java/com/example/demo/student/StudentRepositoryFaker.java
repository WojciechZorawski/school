package com.example.demo.student;

import static com.example.demo.student.StudentFactory.student2Maker;
import static com.example.demo.student.StudentFactory.student3Maker;
import static com.example.demo.student.StudentFactory.studentMaker;

import java.util.List;
import java.util.UUID;

public class StudentRepositoryFaker {

  public static List<Student> students = List.of(studentMaker(), student2Maker(), student3Maker());

  public List<Student> findAll() {
    return students;
  }

  public Student findById(UUID id) throws ClassNotFoundException {

    Student student = students.stream()
        .filter(s -> s.getId() == id)
        .findFirst()
        .orElseThrow(() -> new ClassNotFoundException("Student o podanym id nie istnieje"));

    return student;
  }

}
