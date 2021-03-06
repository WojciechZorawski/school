package com.example.school.grade;

import static com.example.school.grade.GradeFactoryFaker.getValidGradeDto;
import static com.example.school.grade.GradeFactoryFaker.getValidGradeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GradeMapperTest {

  @Test
  void shouldMapDtoToEntity() {
    GradeDTO dto = getValidGradeDto();
    Grade entity = dto.toEntity();
    assertEquals(dto.getDate(), entity.getDate());
    assertEquals(dto.getGrade(), entity.getGrade());
    assertEquals(dto.getSubject(), entity.getSubject());
    assertEquals(dto.getDescription(), entity.getDescription());
  }

  @Test
  void shouldMapEntityToDto() {
    Grade entity = getValidGradeEntity();
    GradeDTO dto = entity.toDto();
    assertEquals(entity.getDate(), dto.getDate());
    assertEquals(entity.getGrade(), dto.getGrade());
    assertEquals(entity.getSubject(), dto.getSubject());
    assertEquals(entity.getDescription(), dto.getDescription());
  }
}
