package com.example.demo.teacher;

import java.util.UUID;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

  @NotNull
  private String name;
  @NotNull
  private String lastName;
  @Min(18)
  @Max(99)
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
