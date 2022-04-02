package com.example.demo.student;

import static com.example.demo.student.StudentFactoryFaker.getAverageFromDto;
import static com.example.demo.student.StudentFactoryFaker.getAverageFromEntity;
import static com.example.demo.student.StudentFactoryFaker.getSex;
import static com.example.demo.student.StudentFactoryFaker.getValidStudentEntity;
import static com.example.demo.student.StudentFactoryFaker.getValidStudentRequestDto;
import static com.example.demo.student.StudentFactoryFaker.getValidStudentResponseDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StudentMapperTest {

  @Test
  void shouldMapRequestDtoToEntity() {
    StudentRequestDTO studentRequestDto = getValidStudentRequestDto();
    Student studentEntity = studentRequestDto.toEntity();
    assertEquals(studentRequestDto.getName(), studentEntity.getName());
    assertEquals(studentRequestDto.getLastName(), studentEntity.getLastName());
    assertEquals(studentRequestDto.getEmail(), studentEntity.getEmail());
    assertEquals(studentRequestDto.getDateOfBirth(), studentEntity.getDateOfBirth());
    assertEquals(studentRequestDto.getAge(), studentEntity.getAge());
    assertEquals(studentRequestDto.getListOfGrades().get(0).getDate(), studentEntity.getListOfGrades().get(0).getDate());
    assertEquals(studentRequestDto.getListOfGrades().get(0).getGrade(), studentEntity.getListOfGrades().get(0).getGrade());
    assertEquals(studentRequestDto.getListOfGrades().get(0).getSubject(), studentEntity.getListOfGrades().get(0).getSubject());
  }

  @Test
  void shouldMapEntityToResponseDto() {
    Student studentEntity = getValidStudentEntity();
    StudentResponseDTO studentResponseDto = studentEntity.toResponseDto();
    assertEquals(studentEntity.getName(), studentResponseDto.getName());
    assertEquals(studentEntity.getLastName(), studentResponseDto.getLastName());
    assertEquals(studentEntity.getEmail(), studentResponseDto.getEmail());
    assertEquals(studentEntity.getDateOfBirth(), studentResponseDto.getDateOfBirth());
    assertEquals(studentEntity.getAge(), studentResponseDto.getAge());
    assertEquals(studentEntity.getListOfGrades().get(0).getDate(), studentResponseDto.getListOfGrades().get(0).getDate());
    assertEquals(studentEntity.getListOfGrades().get(0).getGrade(), studentResponseDto.getListOfGrades().get(0).getGrade());
    assertEquals(studentEntity.getListOfGrades().get(0).getSubject(), studentResponseDto.getListOfGrades().get(0).getSubject());
    assertEquals(getAverageFromEntity(studentEntity.getListOfGrades()), studentResponseDto.getAverage());
    assertEquals(getSex(studentEntity.getName()), studentResponseDto.getSex());
  }

  @Test
  void shouldMapResponseDtoToRequestDto() {
    StudentResponseDTO studentResponseDto = getValidStudentResponseDto();
    StudentRequestDTO studentRequestDto = studentResponseDto.toRequestDto();
    assertEquals(studentResponseDto.getName(), studentRequestDto.getName());
    assertEquals(studentResponseDto.getLastName(), studentRequestDto.getLastName());
    assertEquals(studentResponseDto.getEmail(), studentRequestDto.getEmail());
    assertEquals(studentResponseDto.getDateOfBirth(), studentRequestDto.getDateOfBirth());
    assertEquals(studentResponseDto.getAge(), studentRequestDto.getAge());
    assertEquals(studentResponseDto.getListOfGrades().get(0).getDate(), studentRequestDto.getListOfGrades().get(0).getDate());
    assertEquals(studentResponseDto.getListOfGrades().get(0).getGrade(), studentRequestDto.getListOfGrades().get(0).getGrade());
    assertEquals(studentResponseDto.getListOfGrades().get(0).getSubject(), studentRequestDto.getListOfGrades().get(0).getSubject());
    assertEquals(getAverageFromDto(studentResponseDto.getListOfGrades()), getAverageFromDto(studentRequestDto.getListOfGrades()));
    assertEquals(getSex(studentResponseDto.getName()), getSex(studentRequestDto.getName()));
  }

}
