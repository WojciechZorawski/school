package com.example.demo.register;

import static com.example.demo.student.StudentFactoryFaker.getInvalidStudentRequestDto;
import static com.example.demo.student.StudentFactoryFaker.getValidStudentEntity;
import static com.example.demo.student.StudentFactoryFaker.getValidStudentRequestDto;
import static com.example.demo.student.StudentFactoryFaker.getValidStudentResponseDto;
import static com.example.demo.teacher.TeacherFactoryFaker.getInvalidTeacherDto;
import static com.example.demo.teacher.TeacherFactoryFaker.getValidTeacherDto;
import static com.example.demo.teacher.TeacherFactoryFaker.getValidTeacherEntity;

import com.example.demo.student.StudentFactoryFaker;
import com.example.demo.teacher.TeacherFactoryFaker;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

class RegisterFactoryFaker {

  static Register toEntity(RegisterRequestDTO requestDto) {
    return Register.builder()
        .id(UUID.randomUUID())
        .teacher(TeacherFactoryFaker.toEntity(requestDto.getTeacher()))
        .listOfStudents(requestDto.getListOfStudents().stream()
                            .map(studentRequestDto -> StudentFactoryFaker.toEntity(studentRequestDto))
                            .collect(Collectors.toList()))
        .build();
  }

  static Register getValidRegisterEntity() {
    return Register.builder()
                   .id(UUID.randomUUID())
                   .teacher(getValidTeacherEntity())
                   .listOfStudents(List.of(getValidStudentEntity(), getValidStudentEntity()))
                   .build();
  }

  static RegisterResponseDTO getValidRegisterResponseDto() {
    return RegisterResponseDTO.builder()
                              .teacher(getValidTeacherDto())
                              .listOfStudents(List.of(getValidStudentResponseDto(), getValidStudentResponseDto(), getValidStudentResponseDto()))
                              .build();
  }

  static RegisterRequestDTO getValidRegisterRequestDto() {
    return RegisterRequestDTO.builder()
                             .teacher(getValidTeacherDto())
                             .listOfStudents(List.of(getValidStudentRequestDto(), getValidStudentRequestDto(), getValidStudentRequestDto()))
                             .build();
  }

  static RegisterRequestDTO getInvalidRegisterRequestDto() {
    return RegisterRequestDTO.builder()
                             .teacher(getInvalidTeacherDto())
                             .listOfStudents(List.of(getInvalidStudentRequestDto(), getInvalidStudentRequestDto()))
                             .build();
  }
}
