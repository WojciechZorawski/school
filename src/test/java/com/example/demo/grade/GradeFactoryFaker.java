package com.example.demo.grade;

import com.github.javafaker.Faker;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GradeFactoryFaker {

  private static final Faker FAKER = Faker.instance(new Random(81));

  static GradeDTO toDto(Grade entity) {
    return GradeDTO.builder()
                   .date(entity.getDate())
                   .grade(entity.getGrade())
                   .subject(entity.getSubject())
                   .description(entity.getDescription())
                   .build();
  }

  public static Grade toEntity(GradeDTO dto) {
    return Grade.builder()
                .date(dto.getDate())
                .grade(dto.getGrade())
                .subject(dto.getSubject())
                .description(dto.getDescription())
                .build();
  }

  public static GradeDTO getValidGradeDto() {
    return GradeDTO.builder()
                   .date(FAKER.date().past(2021, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                   .grade(Integer.parseInt(FAKER.regexify("([1-6]){1}")))
                   .subject(FAKER.name().name())
                   .description(FAKER.name().title())
                   .build();
  }

  public static Grade getValidGradeEntity() {
    return Grade.builder()
                .id(1L)
                .date(FAKER.date().past(2021, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .grade(Integer.parseInt(FAKER.regexify("([1-6]){1}")))
                .subject(FAKER.name().name())
                .description(FAKER.name().title())
                .build();
  }

  static GradeDTO getInvalidGradeDto() {
    return GradeDTO.builder()
                   .date(FAKER.date().future(2021, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                   .grade(Integer.parseInt(FAKER.numerify("##")))
                   .subject(FAKER.name().name())
                   .description(FAKER.name().title())
                   .build();
  }

}

