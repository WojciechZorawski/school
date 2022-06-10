package com.example.school.grade;

import static com.example.school.grade.GradeFactoryFaker.getValidGradeEntity;
import static com.example.school.grade.GradeFactoryFaker.toDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GradeServiceTest {

  @InjectMocks
  private GradeService gradeService;
  @Mock
  private GradeRepository gradeRepository;

  @Test
  void getGradeByIdReturnsProperGrade() {
    Grade gradeEntity = getValidGradeEntity();
    Long id = gradeEntity.getId();

    when(gradeRepository.findById(id)).thenReturn(Optional.of(gradeEntity));

    GradeDTO gradeDto = gradeService.getGradeById(id);
    compareGradeEntityToGradeDto(gradeEntity, gradeDto);
  }

  @Test
  void getAllGradesReturnsProperGradeList() {
    List<Grade> entityList = List.of(getValidGradeEntity());

    when(gradeRepository.findAll()).thenReturn(entityList);

    List<GradeDTO> dtoList = gradeService.getAllGrades();
    assertEquals(1, dtoList.size());
    compareGradeEntityToGradeDto(entityList.get(0), dtoList.get(0));
  }

  @Test
  void createGradeReturnsProperGrade() {
    Grade newGrade = getValidGradeEntity();

    when(gradeRepository.save(any(Grade.class))).thenReturn(newGrade);

    GradeDTO createdGrade = gradeService.createGrade(toDto(newGrade));
    compareGradeEntityToGradeDto(newGrade, createdGrade);

  }

  @Test
  void createGradeEntityReturnsProperGrade() {
    Grade newGrade = getValidGradeEntity();

    when(gradeRepository.save(any(Grade.class))).thenReturn(newGrade);

    Grade createdGrade = gradeService.createGradeEntity(newGrade);
    assertEquals(newGrade.getDate(), createdGrade.getDate());
    assertEquals(newGrade.getGrade(), createdGrade.getGrade());
    assertEquals(newGrade.getSubject(), createdGrade.getSubject());
    assertEquals(newGrade.getDescription(), createdGrade.getDescription());
  }

  @Test
  void updateGradeReturnsProperGrade() {
    Grade gradeEntity = getValidGradeEntity();
    Long id = gradeEntity.getId();
    int grade = 5;
    String subject = "Historia";

    when(gradeRepository.findById(id)).thenReturn(Optional.of(gradeEntity));
    when(gradeRepository.save(any(Grade.class))).thenReturn(gradeEntity);

    GradeDTO updatedGrade = gradeService.updateGrade(id, grade, subject);
    assertEquals(gradeEntity.getDate(), updatedGrade.getDate());
    assertEquals(grade, updatedGrade.getGrade());
    assertEquals(subject, updatedGrade.getSubject());
    assertEquals(gradeEntity.getDescription(), updatedGrade.getDescription());
  }

  @Test
  void deleteGrade() {
    Grade gradeEntity = getValidGradeEntity();
    Long id = gradeEntity.getId();
    gradeService.deleteGrade(id);
    verify(gradeRepository, times(1)).deleteById(id);
  }

  private void compareGradeEntityToGradeDto(Grade gradeEntity, GradeDTO gradeDto) {
    assertEquals(gradeEntity.getDate(), gradeDto.getDate());
    assertEquals(gradeEntity.getGrade(), gradeDto.getGrade());
    assertEquals(gradeEntity.getSubject(), gradeDto.getSubject());
    assertEquals(gradeEntity.getDescription(), gradeDto.getDescription());
  }
}
