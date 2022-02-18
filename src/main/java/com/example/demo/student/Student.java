package com.example.demo.student;

import com.example.demo.grade.Grade;
import com.example.demo.grade.GradeDTO;
import com.example.demo.utils.Sex;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student {

  private UUID id;
  private String name;
  private String email;
  private LocalDate dateOfBirth;
  private int age;
  private List<Grade> listOfGrades;

  public StudentResponseDTO toResponseDto() {
    Sex setSex = name.endsWith("a") ? Sex.F : Sex.M;
    List<GradeDTO> list = listOfGrades != null
        ? listOfGrades.stream()
                      .map(grade -> grade.toDto())
                      .collect(Collectors.toList())
        : null;
    return StudentResponseDTO.builder()
                            .name(name)
                            .email(email)
                            .dateOfBirth(dateOfBirth)
                            .age(age)
                            .listOfGrades(list)
                            .sex(setSex)
                            .build();
  }

}
