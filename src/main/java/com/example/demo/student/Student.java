package com.example.demo.student;

import com.example.demo.grade.Grade;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
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

  public StudentDTO toDto() {
    return StudentDTO.builder()
        .name(name)
        .email(email)
        .dateOfBirth(dateOfBirth)
        .age(age)
        .listOfGrades(listOfGrades)
        .build();
  }

}
