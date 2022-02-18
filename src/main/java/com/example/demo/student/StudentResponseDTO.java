package com.example.demo.student;

import com.example.demo.grade.GradeDTO;
import com.example.demo.utils.Sex;
import java.time.LocalDate;
import java.util.List;
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
public class StudentResponseDTO {

  private String name;
  private String email;
  private LocalDate dateOfBirth;
  private int age;
  private List<GradeDTO> listOfGrades;
  private Sex sex;

  public StudentRequestDTO toRequestDto(){
    return StudentRequestDTO.builder()
                             .name(name)
                             .email(email)
                             .dateOfBirth(dateOfBirth)
                             .age(age)
                             .listOfGrades(listOfGrades)
                             .build();
  }
}
