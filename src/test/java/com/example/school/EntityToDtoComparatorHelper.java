package com.example.school;

import static com.example.school.student.StudentFactoryFaker.getAverageFromEntity;
import static com.example.school.student.StudentFactoryFaker.getSex;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.school.grade.Grade;
import com.example.school.grade.GradeDTO;
import com.example.school.student.Student;
import com.example.school.student.StudentResponseDTO;
import com.example.school.teacher.Teacher;
import com.example.school.teacher.TeacherDTO;

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
