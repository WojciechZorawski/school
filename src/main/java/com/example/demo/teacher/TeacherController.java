package com.example.demo.teacher;

import java.util.List;
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

@RequestMapping("/teachers")
@RestController
@RequiredArgsConstructor
public class TeacherController {

  private final TeacherService teacherService;

  @GetMapping("/{id}")
  public TeacherDTO getTeacherById(@PathVariable Long id) {
    TeacherDTO teacher = teacherService.getTeacherById(id);
    return teacher;
  }

  @GetMapping("/list")
  public List<TeacherDTO> getAllTeacher() {
    List<TeacherDTO> list = teacherService.getAllTeachers();
    return list;
  }

  @PostMapping
  public TeacherDTO createTeacher(@RequestBody TeacherDTO newTeacher) {
    TeacherDTO createdTeacher = teacherService.createTeacher(newTeacher);
    return createdTeacher;
  }

  @PutMapping("/{id}")
  public TeacherDTO updateTeacher(@PathVariable Long id, @RequestParam String lastName, @RequestParam int age) {
    TeacherDTO updatedTeacher = teacherService.updateTeacher(id, lastName, age);
    return updatedTeacher;
  }

  @DeleteMapping("/{id}")
  public void deleteTeacher(@PathVariable Long id) {
    teacherService.deleteTeacher(id);
  }
}
