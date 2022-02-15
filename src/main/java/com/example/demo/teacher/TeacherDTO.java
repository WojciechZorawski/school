package com.example.demo.teacher;

import java.util.UUID;
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
public class TeacherDTO {

  private String name;
  private String lastName;
  private int age;
  private String profession;

  public Teacher toEntity() {
    return Teacher.builder()
        .id(UUID.randomUUID())
        .name(name)
        .lastName(lastName)
        .age(age)
        .profession(profession)
        .build();
  }

}
