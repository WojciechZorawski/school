package com.example.demo.student;

import static com.example.demo.student.StudentFactory.student2Maker;
import static com.example.demo.student.StudentFactory.student3Maker;
import static com.example.demo.student.StudentFactory.studentMaker;

import java.util.List;

public class StudentRepositoryFaker {

  public static List<Student> students = List.of(studentMaker(1L), student2Maker(2L), student3Maker(3L));

  public List<Student> findAll() {
    return students;
  }

  public Student findById(Long id) throws ClassNotFoundException {

    Student student = students.stream()
        .filter(s -> s.getId() == id)
        .findFirst()
        .orElseThrow(() -> new ClassNotFoundException("Student o podanym id nie istnieje"));

    return student;
  }

}
