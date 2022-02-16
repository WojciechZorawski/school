package com.example.demo.student;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

  private final StudentService studentService;

  @GetMapping("/{id}")
  public StudentDTO getStudentById(@PathVariable UUID id) {
    StudentDTO student = studentService.getStudentById(id);
    return student;
  }

  @GetMapping("/list")
  public List<StudentDTO> getAllStudents() {
    List<StudentDTO> students = studentService.getAllStudents();
    return students;
  }

  @PostMapping
  public StudentDTO createStudent(@RequestBody StudentDTO newStudent) {
    StudentDTO createdStudent = studentService.createStudent(newStudent);
    return createdStudent;
  }

  @PutMapping("/{id}")
  public StudentDTO updateStudent(@PathVariable UUID id, @RequestParam String name, @RequestParam String email) {
    StudentDTO updatedStudent = studentService.updateStudent(id, name, email);
    return updatedStudent;
  }

  @DeleteMapping("/{id}")
  public void deleteStudent(@PathVariable UUID id) {
    studentService.deleteStudent(id);
  }

}
