package com.example.school.student;

import static com.example.school.grade.GradeFactoryFaker.getValidGradeDto;
import static com.example.school.grade.GradeFactoryFaker.getValidGradeEntity;

import com.example.school.grade.Grade;
import com.example.school.grade.GradeDTO;
import com.example.school.grade.GradeFactoryFaker;
import com.example.school.utils.Sex;
import com.github.javafaker.Faker;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class StudentFactoryFaker {

  private static final Faker FAKER = Faker.instance(new Random(81));

  public static Student toEntity(StudentRequestDTO requestDto) {
    return Student.builder()
                  .name(requestDto.getName())
                  .lastName(requestDto.getLastName())
                  .email(requestDto.getEmail())
                  .dateOfBirth(requestDto.getDateOfBirth())
                  .age(requestDto.getAge())
                  .listOfGrades(requestDto.getListOfGrades().stream()
                                          .map(gradeDto -> GradeFactoryFaker.toEntity(gradeDto))
                                          .collect(Collectors.toList()))
                  .sex(getSex(requestDto.getName()))
                  .build();
  }

  public static StudentResponseDTO toResponseDto(StudentRequestDTO requestDto) {
    return StudentResponseDTO.builder()
                             .name(requestDto.getName())
                             .lastName(requestDto.getLastName())
                             .email(requestDto.getEmail())
                             .dateOfBirth(requestDto.getDateOfBirth())
                             .age(requestDto.getAge())
                             .listOfGrades(requestDto.getListOfGrades())
                             .average(getAverageFromDto(requestDto.getListOfGrades()))
                             .sex(getSex(requestDto.getName()))
                             .build();
  }

  public static List<StudentResponseDTO> toResponseDtoList(List<StudentRequestDTO> requestDtoList) {
    List<StudentResponseDTO> responseDtoList = requestDtoList.stream()
                                                             .map(student -> toResponseDto(student))
                                                             .collect(Collectors.toList());
    return responseDtoList;
  }

  public static Double getAverageFromDto(List<GradeDTO> listGradeDto) {
    return listGradeDto.stream()
                       .mapToDouble(grade -> grade.getGrade())
                       .average()
                       .orElseThrow();
  }

  public static Double getAverageFromEntity(List<Grade> listGrade) {
    return listGrade.stream()
                    .mapToDouble(grade -> grade.getGrade())
                    .average()
                    .orElseThrow();
  }

  public static Sex getSex(String name) {
    return name.endsWith("a") ? Sex.F : Sex.M;
  }

  public static Student getValidStudentEntity() {
    String name = FAKER.name().name();
    return Student.builder()
                  .id(1L)
                  .name(name)
                  .lastName(FAKER.name().lastName())
                  .email("email@cos.pl")
                  .dateOfBirth(FAKER.date().past(2000, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                  .age(FAKER.number().numberBetween(18, 40))
                  .listOfGrades(List.of(getValidGradeEntity()))
                  .sex(getSex(name))
                  .build();
  }

  public static StudentResponseDTO getValidStudentResponseDto() {
    String name = FAKER.name().name();
    List<GradeDTO> listOfGrades = List.of(getValidGradeDto(), getValidGradeDto());
    return StudentResponseDTO.builder()
                             .name(name)
                             .lastName(FAKER.name().lastName())
                             .email("email@cos.pl")
                             .dateOfBirth(FAKER.date().past(2000, TimeUnit.DAYS).toInstant()
                                               .atZone(ZoneId.systemDefault())
                                               .toLocalDate())
                             .age(FAKER.number().numberBetween(18, 40))
                             .listOfGrades(listOfGrades)
                             .average(getAverageFromDto(listOfGrades))
                             .sex(getSex(name))
                             .build();
  }

  public static StudentRequestDTO getValidStudentRequestDto() {
    return StudentRequestDTO.builder()
                            .name(FAKER.name().name())
                            .lastName(FAKER.name().lastName())
                            .email("email@cos.pl")
                            .dateOfBirth(FAKER.date().past(2000, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                            .age(FAKER.number().numberBetween(18, 40))
                            .listOfGrades(List.of(getValidGradeDto()))
                            .build();
  }

  public static StudentRequestDTO getInvalidStudentRequestDto() {
    return StudentRequestDTO.builder()
                            .name(FAKER.name().name())
                            .lastName(FAKER.name().lastName())
                            .email(FAKER.name().bloodGroup())
                            .dateOfBirth(FAKER.date().future(2000, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                            .age(FAKER.number().numberBetween(10, 50))
                            .listOfGrades(List.of(getValidGradeDto()))
                            .build();
  }

}
