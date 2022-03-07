package com.example.demo.teacher;

import static com.example.demo.teacher.TeacherFactoryFaker.getValidTeacherDto;
import static com.example.demo.teacher.TeacherFactoryFaker.getValidTeacherEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class TeacherMapperTest {

  @Test
  void shouldMapDtoToEntity(){
    TeacherDTO dto = getValidTeacherDto();
    Teacher entity = dto.toEntity();
    assertEquals(dto.getName(), entity.getName());
    assertEquals(dto.getLastName(), entity.getLastName());
    assertEquals(dto.getAge(), entity.getAge());
    assertEquals(dto.getProfession(), entity.getProfession());
    assertNotNull(entity.getId());
  }

  @Test
  void shouldMapEntityToDto(){
    Teacher entity = getValidTeacherEntity();
    TeacherDTO dto = entity.toDto();
    assertEquals(entity.getName(), dto.getName());
    assertEquals(entity.getLastName(), dto.getLastName());
    assertEquals(entity.getAge(), dto.getAge());
    assertEquals(entity.getProfession(), dto.getProfession());
  }
}
