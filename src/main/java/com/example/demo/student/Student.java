package com.example.demo.student;

import com.example.demo.grade.Grade;
import java.time.LocalDate;
import java.util.List;
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

  private Long id;
  private String name;
  private String email;
  private LocalDate dateOfBirth;
  private int age;
  private List<Grade> listOfGrades;

}
