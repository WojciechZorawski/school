package com.example.school.student;

import com.example.school.grade.Grade;
import com.example.school.grade.GradeDTO;
import com.example.school.utils.Sex;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
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
  @Valid
  private List<GradeDTO> listOfGrades;

  public Student toEntity() {
    Sex setSex = name.endsWith("a") ? Sex.F : Sex.M;
    List<Grade> list = listOfGrades != null
        ? listOfGrades.stream()
                      .map(grade -> grade.toEntity())
                      .collect(Collectors.toList())
        : new ArrayList<>();
    return Student.builder()
                  .name(name)
                  .lastName(lastName)
                  .email(email)
                  .dateOfBirth(dateOfBirth)
                  .age(age)
                  .listOfGrades(list)
                  .sex(setSex)
                  .build();
  }
}

