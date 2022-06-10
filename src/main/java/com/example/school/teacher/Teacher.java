package com.example.school.teacher;

import com.example.school.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "teacher")
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends BaseEntity {

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
