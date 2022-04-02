package com.example.demo;

import static com.example.demo.student.StudentFactoryFaker.getAverageFromEntity;
import static com.example.demo.student.StudentFactoryFaker.getSex;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.grade.Grade;
import com.example.demo.grade.GradeDTO;
import com.example.demo.student.Student;
import com.example.demo.student.StudentResponseDTO;
import com.example.demo.teacher.Teacher;
import com.example.demo.teacher.TeacherDTO;

public class EntityToDtoComparatorHelper {

  public static void compareStudentEntityToStudentResponseDto(Student student, StudentResponseDTO studentResponseDto) {
    assertEquals(student.getName(), studentResponseDto.getName());
    assertEquals(student.getLastName(), studentResponseDto.getLastName());
    assertEquals(student.getEmail(), studentResponseDto.getEmail());
    assertEquals(student.getDateOfBirth(), studentResponseDto.getDateOfBirth());
    assertEquals(student.getAge(), studentResponseDto.getAge());
    compareGradeEntityToGradeDto(student.getListOfGrades().get(0),
                                 studentResponseDto.getListOfGrades().get(0));
    assertEquals(getSex(student.getName()), studentResponseDto.getSex());
    assertEquals(getAverageFromEntity(student.getListOfGrades()),
                 studentResponseDto.getAverage());
  }

  public static void compareGradeEntityToGradeDto(Grade grade, GradeDTO gradeDto) {
    assertEquals(grade.getDate(), gradeDto.getDate());
    assertEquals(grade.getGrade(), gradeDto.getGrade());
    assertEquals(grade.getSubject(), gradeDto.getSubject());
    assertEquals(grade.getDescription(), gradeDto.getDescription());
  }

  public static void compareTeacherEntityToTeacherResponseDto(Teacher registerEntity, TeacherDTO registerResponseDto) {
    assertEquals(registerEntity.getName(), registerResponseDto.getName());
    assertEquals(registerEntity.getLastName(), registerResponseDto.getLastName());
    assertEquals(registerEntity.getAge(), registerResponseDto.getAge());
    assertEquals(registerEntity.getProfession(), registerResponseDto.getProfession());
  }

}
