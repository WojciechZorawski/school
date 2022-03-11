package com.example.demo.grade;

import static com.example.demo.grade.GradeFactoryFaker.getValidGradeEntity;
import static com.example.demo.grade.GradeFactoryFaker.toDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;
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
  private GradeRepositoryFaker gradeRepositoryFaker;

  @Test
  void getGradeByIdReturnsProperGrade() throws ClassNotFoundException {
    Grade gradeEntity = getValidGradeEntity();
    UUID id = gradeEntity.getId();

    when(gradeRepositoryFaker.findById(id)).thenReturn(gradeEntity);

    GradeDTO gradeDto = gradeService.getGradeById(id);
    assertEquals(gradeEntity.getDate(), gradeDto.getDate());
    assertEquals(gradeEntity.getGrade(), gradeDto.getGrade());
    assertEquals(gradeEntity.getSubject(), gradeDto.getSubject());
  }

  @Test
  void getAllGradesReturnsProperGradeList() {
    List<Grade> entityList = List.of(getValidGradeEntity());

    when(gradeRepositoryFaker.findAll()).thenReturn(entityList);

    List<GradeDTO> dtoList = gradeService.getAllGrades();
    assertEquals(1, dtoList.size());
    assertEquals(entityList.get(0).getDate(), dtoList.get(0).getDate());
    assertEquals(entityList.get(0).getGrade(), dtoList.get(0).getGrade());
    assertEquals(entityList.get(0).getSubject(), dtoList.get(0).getSubject());
  }

  @Test
  void createGradeReturnsProperGrade() throws ClassNotFoundException {
    Grade newGrade = getValidGradeEntity();

    when(gradeRepositoryFaker.save( any(Grade.class))).thenReturn(newGrade);

    GradeDTO createdGrade = gradeService.createGrade(toDto(newGrade));
    assertEquals(newGrade.getDate(), createdGrade.getDate());
    assertEquals(newGrade.getGrade(), createdGrade.getGrade());
    assertEquals(newGrade.getSubject(), createdGrade.getSubject());
  }

  @Test
  void updateGradeReturnsProperGrade() throws ClassNotFoundException {
    Grade gradeEntity = getValidGradeEntity();
    UUID id = gradeEntity.getId();
    int grade = 5;
    String subject = "Historia";

    when(gradeRepositoryFaker.findById(id)).thenReturn(gradeEntity);

    GradeDTO updatedGrade = gradeService.updateGrade(id, grade, subject);
    assertEquals(gradeEntity.getDate(), updatedGrade.getDate());
    assertEquals(grade, updatedGrade.getGrade());
    assertEquals(subject, updatedGrade.getSubject());
  }

  @Test
  void deleteGrade(){
    Grade gradeEntity = getValidGradeEntity();
    UUID id = gradeEntity.getId();
    gradeService.deleteGrade(id);
    verify(gradeRepositoryFaker, times(1)).deleteById(id);
  }
}
