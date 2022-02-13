package com.example.demo.teacher;

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
public class Teacher {

  private UUID id;
  private String name;
  private String lastName;
  private int age;
  private String profession;

  public TeacherDTO toDto() {
    return TeacherDTO.builder()
        .name(name)
        .lastName(lastName)
        .age(age)
        .profession(profession)
        .build();

  }

}
