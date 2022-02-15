package com.example.demo.student;

import static com.example.demo.student.StudentFactory.studentMaker;

import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  public Student getStudent() {
    return studentMaker();

  }

  public String deleteStudent(UUID id) {
    return "Student usuniety o id " + id;
  }

  public String deleteStudent1(String studentId) {
    Long long1 = Long.valueOf(studentId);
    return "Student znowu usuniety o id " + long1;
  }

  public Student createStudent(Student newStudent) {
    return newStudent;
  }

  public String updateStudent(UUID id, String email, int age) {
    Student updatedStudent = studentMaker();
    Student updatedStudent2 = studentMaker();
    if (id == updatedStudent.getId()) {
      updatedStudent.setEmail(email);
      updatedStudent.setAge(age);
      return updatedStudent.toString();
    } else if (id == updatedStudent2.getId()) {
      updatedStudent2.setEmail(email);
      updatedStudent2.setAge(age);
      return updatedStudent2.toString();
    }
    return null;
  }

}
