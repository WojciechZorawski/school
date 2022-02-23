package com.example.demo.student;

import com.example.demo.grade.Grade;
import com.example.demo.grade.GradeDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
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
public class StudentRequestDTO {

  private String name;
  private String lastName;
  @Email
  private String email;
  @Past
  private LocalDate dateOfBirth;
  @Min(18)
  @Max(40)
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
                  .lastName(lastName)
                  .email(email)
                  .dateOfBirth(dateOfBirth)
                  .age(age)
                  .listOfGrades(list)
                  .build();
  }
}

