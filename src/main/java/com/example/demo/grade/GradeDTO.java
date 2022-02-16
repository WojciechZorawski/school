package com.example.demo.grade;

import java.time.LocalDate;
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
public class GradeDTO {

  private LocalDate date;
  private int grade;
  private String subject;

  public Grade toEntity() {
    return Grade.builder()
        .id(UUID.randomUUID())
        .date(date)
        .grade(grade)
        .subject(subject)
        .build();
  }

}
