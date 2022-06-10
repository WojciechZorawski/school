package com.example.school.grade;

import com.example.school.BaseEntity;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "grade")
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Grade extends BaseEntity {

  private LocalDate date;
  private int grade;
  private String subject;
  private String description;

  public GradeDTO toDto() {
    return GradeDTO.builder()
                   .date(date)
                   .grade(grade)
                   .subject(subject)
                   .description(description)
                   .build();
  }
}
