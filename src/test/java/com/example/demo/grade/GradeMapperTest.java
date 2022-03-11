package com.example.demo.grade;

import static com.example.demo.grade.GradeFactoryFaker.getValidGradeDto;
import static com.example.demo.grade.GradeFactoryFaker.getValidGradeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class GradeMapperTest {

  @Test
  void shouldMapDtoToEntity() {
    GradeDTO dto = getValidGradeDto();
    Grade entity = dto.toEntity();
    assertEquals(dto.getDate(), entity.getDate());
    assertEquals(dto.getGrade(), entity.getGrade());
    assertEquals(dto.getSubject(), entity.getSubject());
    assertNotNull(entity.getId());
  }

  @Test
  void shouldMapEntityToDto() {
    Grade entity = getValidGradeEntity();
    GradeDTO dto = entity.toDto();
    assertEquals(entity.getDate(), dto.getDate());
    assertEquals(entity.getGrade(), dto.getGrade());
    assertEquals(entity.getSubject(), dto.getSubject());

  }
}
