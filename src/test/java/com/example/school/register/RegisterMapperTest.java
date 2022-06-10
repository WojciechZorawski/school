package com.example.school.register;

import static com.example.school.register.RegisterFactoryFaker.getValidRegisterEntity;
import static com.example.school.register.RegisterFactoryFaker.getValidRegisterRequestDto;
import static com.example.school.student.StudentFactoryFaker.getAverageFromEntity;
import static com.example.school.student.StudentFactoryFaker.getSex;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RegisterMapperTest {

  @Test
  void shouldMapRequestDtoToEntity() {
    RegisterRequestDTO registerRequestDto = getValidRegisterRequestDto();
    Register registerEntity = registerRequestDto.toEntity();
    assertEquals(registerRequestDto.getTeacher().getName(), registerEntity.getTeacher().getName());
    assertEquals(registerRequestDto.getTeacher().getLastName(), registerEntity.getTeacher().getLastName());
    assertEquals(registerRequestDto.getTeacher().getAge(), registerEntity.getTeacher().getAge());
    assertEquals(registerRequestDto.getTeacher().getProfession(), registerEntity.getTeacher().getProfession());
    assertEquals(registerRequestDto.getListOfStudents().get(0).getName(), registerEntity.getListOfStudents().get(0).getName());
    assertEquals(registerRequestDto.getListOfStudents().get(0).getLastName(), registerEntity.getListOfStudents().get(0).getLastName());
    assertEquals(registerRequestDto.getListOfStudents().get(0).getEmail(), registerEntity.getListOfStudents().get(0).getEmail());
    assertEquals(registerRequestDto.getListOfStudents().get(0).getDateOfBirth(), registerEntity.getListOfStudents().get(0).getDateOfBirth());
    assertEquals(registerRequestDto.getListOfStudents().get(0).getAge(), registerEntity.getListOfStudents().get(0).getAge());
    assertEquals(registerRequestDto.getListOfStudents().get(0).getListOfGrades().get(0).getDate(), registerEntity.getListOfStudents()
                                                                                                                 .get(0)
                                                                                                                 .getListOfGrades()
                                                                                                                 .get(0)
                                                                                                                 .getDate());
    assertEquals(registerRequestDto.getListOfStudents().get(0).getListOfGrades().get(0).getGrade(), registerEntity.getListOfStudents()
                                                                                                                  .get(0)
                                                                                                                  .getListOfGrades()
                                                                                                                  .get(0)
                                                                                                                  .getGrade());
    assertEquals(registerRequestDto.getListOfStudents().get(0).getListOfGrades().get(0).getSubject(), registerEntity.getListOfStudents()
                                                                                                                    .get(0)
                                                                                                                    .getListOfGrades()
                                                                                                                    .get(0)
                                                                                                                    .getSubject());
    assertEquals(registerRequestDto.getListOfStudents().get(0).getListOfGrades().get(0).getDescription(), registerEntity.getListOfStudents()
                                                                                                                        .get(0)
                                                                                                                        .getListOfGrades()
                                                                                                                        .get(0)
                                                                                                                        .getDescription());
  }

  @Test
  void shouldMapEntityToResponseDto() {
    Register registerEntity = getValidRegisterEntity();
    RegisterResponseDTO registerResponseDto = registerEntity.toResponseDto();
    assertEquals(registerEntity.getTeacher().getName(), registerResponseDto.getTeacher().getName());
    assertEquals(registerEntity.getTeacher().getLastName(), registerResponseDto.getTeacher().getLastName());
    assertEquals(registerEntity.getTeacher().getAge(), registerResponseDto.getTeacher().getAge());
    assertEquals(registerEntity.getTeacher().getProfession(), registerResponseDto.getTeacher().getProfession());
    assertEquals(registerEntity.getListOfStudents().get(0).getName(), registerResponseDto.getListOfStudents().get(0).getName());
    assertEquals(registerEntity.getListOfStudents().get(0).getLastName(), registerResponseDto.getListOfStudents().get(0).getLastName());
    assertEquals(registerEntity.getListOfStudents().get(0).getEmail(), registerResponseDto.getListOfStudents().get(0).getEmail());
    assertEquals(registerEntity.getListOfStudents().get(0).getDateOfBirth(), registerResponseDto.getListOfStudents().get(0).getDateOfBirth());
    assertEquals(registerEntity.getListOfStudents().get(0).getAge(), registerResponseDto.getListOfStudents().get(0).getAge());
    assertEquals(registerEntity.getListOfStudents().get(0).getListOfGrades().get(0).getDate(), registerResponseDto.getListOfStudents()
                                                                                                                  .get(0)
                                                                                                                  .getListOfGrades()
                                                                                                                  .get(0)
                                                                                                                  .getDate());
    assertEquals(registerEntity.getListOfStudents().get(0).getListOfGrades().get(0).getGrade(), registerResponseDto.getListOfStudents()
                                                                                                                   .get(0)
                                                                                                                   .getListOfGrades()
                                                                                                                   .get(0)
                                                                                                                   .getGrade());
    assertEquals(registerEntity.getListOfStudents().get(0).getListOfGrades().get(0).getSubject(), registerResponseDto.getListOfStudents()
                                                                                                                     .get(0)
                                                                                                                     .getListOfGrades()
                                                                                                                     .get(0)
                                                                                                                     .getSubject());
    assertEquals(registerEntity.getListOfStudents().get(0).getListOfGrades().get(0).getDescription(), registerResponseDto.getListOfStudents()
                                                                                                                         .get(0)
                                                                                                                         .getListOfGrades()
                                                                                                                         .get(0)
                                                                                                                         .getDescription());
    assertEquals(getSex(registerEntity.getListOfStudents().get(0).getName()), registerResponseDto.getListOfStudents().get(0).getSex());
    assertEquals(getAverageFromEntity(registerEntity.getListOfStudents().get(0).getListOfGrades()),
                 registerResponseDto.getListOfStudents().get(0).getAverage());
  }

}
