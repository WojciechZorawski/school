package com.example.school.grade;

import com.example.school.exception.BusinessLogicException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeService {

  private final GradeRepository gradeRepository;

  GradeDTO getGradeById(Long id) {
    Grade takenGrade = findGradeById(id);
    return takenGrade.toDto();
  }

  List<GradeDTO> getAllGrades() {
    List<GradeDTO> takenGrades = gradeRepository.findAll().stream()
                                                .map(grade -> grade.toDto())
                                                .collect(Collectors.toList());
    return takenGrades;
  }

  public GradeDTO createGrade(GradeDTO newGrade) {
    Grade save = gradeRepository.save(newGrade.toEntity());
    return save.toDto();
  }

  public Grade createGradeEntity(Grade newGrade) {
    Grade save = gradeRepository.save(newGrade);
    return save;
  }

  GradeDTO updateGrade(Long id, int grade, String subject) {
    Grade updatedGrade = findGradeById(id);
    updatedGrade.setGrade(grade);
    updatedGrade.setSubject(subject);
    return gradeRepository.save(updatedGrade).toDto();
  }

  void deleteGrade(Long id) {
    gradeRepository.deleteById(id);
  }

  private Grade findGradeById(Long id) {
    return gradeRepository.findById(id)
                          .orElseThrow(() -> new BusinessLogicException("Bledne id"));
  }
}
