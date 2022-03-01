package com.example.demo.grade;

import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
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

  @PastOrPresent
  private LocalDate date;
  @Min(1)
  @Max(6)
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
