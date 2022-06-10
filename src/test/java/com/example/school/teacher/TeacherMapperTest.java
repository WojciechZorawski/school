package com.example.school.teacher;

import static com.example.school.teacher.TeacherFactoryFaker.getValidTeacherDto;
import static com.example.school.teacher.TeacherFactoryFaker.getValidTeacherEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TeacherMapperTest {

  @Test
  void shouldMapDtoToEntity() {
    TeacherDTO dto = getValidTeacherDto();
    Teacher entity = dto.toEntity();
    assertEquals(dto.getName(), entity.getName());
    assertEquals(dto.getLastName(), entity.getLastName());
    assertEquals(dto.getAge(), entity.getAge());
    assertEquals(dto.getProfession(), entity.getProfession());
  }

  @Test
  void shouldMapEntityToDto() {
    Teacher entity = getValidTeacherEntity();
    TeacherDTO dto = entity.toDto();
    assertEquals(entity.getName(), dto.getName());
    assertEquals(entity.getLastName(), dto.getLastName());
    assertEquals(entity.getAge(), dto.getAge());
    assertEquals(entity.getProfession(), dto.getProfession());
  }
}
