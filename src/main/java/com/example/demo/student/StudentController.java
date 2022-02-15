package com.example.demo.student;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

  StudentService studentService;

  @Autowired
  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public Student getStudent() {
    Student student20 = studentService.getStudent();
    return student20;
  }

  @DeleteMapping(path = "/{id}")
  public String deleteStudent(@PathVariable UUID id) {
    String deleting = studentService.deleteStudent(id);
    return deleting;
  }

  @DeleteMapping(path = "/delete")
  public String deleteStudent1(@RequestParam String studentId) {
    String deleting2 = studentService.deleteStudent1(studentId);
    return deleting2;
  }

  @PostMapping
  public Student createStudent(@RequestBody Student newStudent) {
    Student createdStudent = studentService.createStudent(newStudent);
    return createdStudent;
  }

  @PutMapping(path = "/{id}")
  public String updateStudent(@PathVariable UUID id, @RequestParam String email, int age) {
    String updatedStudent = studentService.updateStudent(id, email, age);
    return updatedStudent;
  }

}
