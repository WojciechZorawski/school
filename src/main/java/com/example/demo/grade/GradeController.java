package com.example.demo.grade;

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
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeController {

  private final GradeService gradeService;

  @GetMapping("/{id}")
  public GradeDTO getGradeById(@PathVariable UUID id) {
    GradeDTO grade = gradeService.getGradeById(id);
    return grade;
  }

  @GetMapping("/list")
  public List<GradeDTO> getAllGrades() {
    List<GradeDTO> grades = gradeService.getAllGrades();
    return grades;
  }

  @PostMapping
  public GradeDTO createGrade(@RequestBody GradeDTO newGrade) {
    GradeDTO createdTeacher = gradeService.createGrade(newGrade);
    return createdTeacher;
  }

  @PutMapping("/{id}")
  public GradeDTO updateGrade(@PathVariable UUID id, @RequestParam int grade, @RequestParam String subject) {
    GradeDTO updatedGrade = gradeService.updateGrade(id, grade, subject);
    return updatedGrade;
  }

  @DeleteMapping("/{id}")
  public void deleteGrade(@PathVariable UUID id) {
    gradeService.deleteGrade(id);
  }

}
