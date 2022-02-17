package com.example.demo.student;

import com.example.demo.grade.Grade;
import com.example.demo.grade.GradeDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

  private String name;
  private String email;
  private LocalDate dateOfBirth;
  private int age;
  private List<GradeDTO> listOfGrades;

  public Student toEntity() {
    List<Grade> list = listOfGrades != null
        ? listOfGrades.stream()
                      .map(grade -> grade.toEntity())
                      .collect(Collectors.toList())
        : null;
    return Student.builder()
                  .id(UUID.randomUUID())
                  .name(name)
                  .email(email)
                  .dateOfBirth(dateOfBirth)
                  .age(age)
                  .listOfGrades(list)
                  .build();
  }

}
