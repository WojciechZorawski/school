package com.example.demo.grade;

import java.time.LocalDate;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {

  @PastOrPresent
  private LocalDate date;
  @Min(1)
  @Max(6)
  private int grade;
  private String subject;
  private String description;

  public Grade toEntity() {
    return Grade.builder()
                .date(date)
                .grade(grade)
                .subject(subject)
                .description(description)
                .build();
  }

}
