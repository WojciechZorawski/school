package com.example.demo.teacher;

import static java.util.UUID.randomUUID;

public class TeacherFactory {

  public static Teacher teacherMaker() {
    Teacher newTeacher = Teacher.builder()
        .id(randomUUID())
        .name("Tadeusz")
        .lastName("Sichocki")
        .age(50)
        .profession("Matematyka")
        .build();
    return newTeacher;
  }

  public static Teacher teacher2Maker() {
    Teacher newTeacher2 = Teacher.builder()
        .id(randomUUID())
        .name("Emilia")
        .lastName("Polonsky")
        .age(61)
        .profession("Jezyk polski")
        .build();
    return newTeacher2;
  }

}
