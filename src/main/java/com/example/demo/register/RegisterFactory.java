package com.example.demo.register;

import static com.example.demo.teacher.TeacherFactory.teacher2Maker;
import static com.example.demo.teacher.TeacherFactory.teacherMaker;
import static com.example.demo.student.StudentFactory.student2Maker;
import static com.example.demo.student.StudentFactory.student3Maker;
import static com.example.demo.student.StudentFactory.studentMaker;

import java.util.List;

public class RegisterFactory {

  public static Register registerMaker() {
    Register newRegister = Register.builder()
        .id(1L)
        .teacher(teacherMaker(1L))
        .listOfStudents(List.of(studentMaker(1L), student2Maker(2L), student3Maker(3L)))
        .build();
    return newRegister;

  }

  public static Register register2Maker() {
    Register newRegister2 = Register.builder()
        .id(2L)
        .teacher(teacher2Maker(2L))
        .listOfStudents(List.of(studentMaker(1L), student3Maker(3L)))
        .build();
    return newRegister2;

  }

}


