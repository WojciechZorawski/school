package com.example.school.student;

import com.example.school.BaseEntity;
import com.example.school.exception.BusinessLogicException;
import com.example.school.grade.Grade;
import com.example.school.grade.GradeDTO;
import com.example.school.utils.Sex;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "student")
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends BaseEntity {

  private String name;
  private String lastName;
  private String email;
  private LocalDate dateOfBirth;
  private int age;
  @Default
  @OneToMany
  @JoinColumn(name = "student_id")
  private List<Grade> listOfGrades = new ArrayList<>();
  @Enumerated(EnumType.STRING)
  private Sex sex;

  public StudentResponseDTO toResponseDto() {
    Sex setSex = name.endsWith("a") ? Sex.F : Sex.M;
    List<GradeDTO> dtoList = listOfGrades != null
        ? listOfGrades.stream()
                      .map(grade -> grade.toDto())
                      .collect(Collectors.toList())
        : new ArrayList<>();
    Double average = listOfGrades != null && !listOfGrades.isEmpty()
        ? listOfGrades.stream()
                      .mapToDouble(grade -> grade.getGrade())
                      .average().orElseThrow(() -> new BusinessLogicException("Bledne dane"))
        : null;
    return StudentResponseDTO.builder()
                             .name(name)
                             .lastName(lastName)
                             .email(email)
                             .dateOfBirth(dateOfBirth)
                             .age(age)
                             .listOfGrades(dtoList)
                             .average(average)
                             .sex(setSex)
                             .build();
  }
}
