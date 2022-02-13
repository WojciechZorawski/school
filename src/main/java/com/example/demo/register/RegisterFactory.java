package com.example.demo.register;

import static com.example.demo.student.StudentFactory.student2Maker;
import static com.example.demo.student.StudentFactory.student3Maker;
import static com.example.demo.student.StudentFactory.studentMaker;
import static com.example.demo.teacher.TeacherFactory.teacher2Maker;
import static com.example.demo.teacher.TeacherFactory.teacherMaker;

import java.util.List;
import java.util.UUID;

public class RegisterFactory {

  public static Register registerMaker() {
    Register newRegister = Register.builder()
        .id(UUID.randomUUID())
        .teacher(teacherMaker())
        .listOfStudents(List.of(studentMaker(), student2Maker(), student3Maker()))
        .build();
    return newRegister;

  }

  public static Register register2Maker() {
    Register newRegister2 = Register.builder()
        .id(UUID.randomUUID())
        .teacher(teacher2Maker())
        .listOfStudents(List.of(studentMaker(), student3Maker()))
        .build();
    return newRegister2;

  }

}


