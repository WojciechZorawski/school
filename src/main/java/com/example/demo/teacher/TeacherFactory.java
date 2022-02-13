package com.example.demo.teacher;

public class TeacherFactory {

  public static Teacher teacherMaker(Long id) {
    Teacher newTeacher = Teacher.builder()
        .id(id)
        .name("Tadeusz")
        .lastName("Sichocki")
        .age(50)
        .profession("Matematyka")
        .build();
    return newTeacher;
  }

  public static Teacher teacher2Maker(Long id) {
    Teacher newTeacher2 = Teacher.builder()
        .id(id)
        .name("Emilia")
        .lastName("Polonsky")
        .age(61)
        .profession("Jezyk polski")
        .build();
    return newTeacher2;
  }

}
