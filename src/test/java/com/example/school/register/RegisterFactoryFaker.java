package com.example.school.register;

import static com.example.school.student.StudentFactoryFaker.getInvalidStudentRequestDto;
import static com.example.school.student.StudentFactoryFaker.getValidStudentEntity;
import static com.example.school.student.StudentFactoryFaker.getValidStudentRequestDto;
import static com.example.school.student.StudentFactoryFaker.getValidStudentResponseDto;
import static com.example.school.teacher.TeacherFactoryFaker.getInvalidTeacherDto;
import static com.example.school.teacher.TeacherFactoryFaker.getValidTeacherDto;
import static com.example.school.teacher.TeacherFactoryFaker.getValidTeacherEntity;

import com.example.school.student.Student;
import com.example.school.student.StudentFactoryFaker;
import com.example.school.teacher.TeacherFactoryFaker;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class RegisterFactoryFaker {

  static Register toEntity(RegisterRequestDTO requestDto) {
    return Register.builder()
                   .teacher(TeacherFactoryFaker.toEntity(requestDto.getTeacher()))
                   .listOfStudents(requestDto.getListOfStudents().stream()
                                             .map(studentRequestDto -> StudentFactoryFaker.toEntity(studentRequestDto))
                                             .collect(Collectors.toList()))
                   .build();
  }

  static Register getValidRegisterEntity() {
    List<Student> listOfStudent = new ArrayList<>();
    listOfStudent.add(getValidStudentEntity());
    return Register.builder()
                   .id(1L)
                   .teacher(getValidTeacherEntity())
                   .listOfStudents(listOfStudent)
                   .build();
  }

  static RegisterResponseDTO getValidRegisterResponseDto() {
    return RegisterResponseDTO.builder()
                              .teacher(getValidTeacherDto())
                              .listOfStudents(List.of(getValidStudentResponseDto()))
                              .build();
  }

  static RegisterRequestDTO getValidRegisterRequestDto() {
    return RegisterRequestDTO.builder()
                             .teacher(getValidTeacherDto())
                             .listOfStudents(List.of(getValidStudentRequestDto()))
                             .build();
  }

  static RegisterRequestDTO getInvalidRegisterRequestDto() {
    return RegisterRequestDTO.builder()
                             .teacher(getInvalidTeacherDto())
                             .listOfStudents(List.of(getInvalidStudentRequestDto()))
                             .build();
  }
}
