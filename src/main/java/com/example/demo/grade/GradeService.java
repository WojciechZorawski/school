package com.example.demo.grade;

import com.example.demo.exception.BusinessLogicException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class GradeService {

  GradeRepositoryFaker gradeRepositoryFaker = new GradeRepositoryFaker();

  public GradeDTO getGradeById(UUID id) {
    try {
      Grade takenGrade = gradeRepositoryFaker.findById(id);
      return takenGrade.toDto();
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Bledne id");
    }

  }

  public List<GradeDTO> getAllGrades() {
    List<GradeDTO> takenGrades = gradeRepositoryFaker.findAll().stream()
        .map(grade -> grade.toDto())
        .collect(Collectors.toList());
    return takenGrades;
  }

  public GradeDTO createGrade(GradeDTO newGrade) {
    try {
      Grade save = gradeRepositoryFaker.save(newGrade.toEntity());
      return save.toDto();
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Nie udalo sie utworzyc nowej oceny");
    }
  }

  public GradeDTO updateGrade(UUID id, int grade, String subject) {
    try {
      Grade updatedGrade = gradeRepositoryFaker.findById(id);
      updatedGrade.setGrade(grade);
      updatedGrade.setSubject(subject);
      return updatedGrade.toDto();
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Bledne id");
    }
  }

  public void deleteGrade(UUID id) {
    gradeRepositoryFaker.deleteById(id);
  }
}
