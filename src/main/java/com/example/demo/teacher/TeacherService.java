package com.example.demo.teacher;

import static com.example.demo.student.StudentFactory.studentMaker;
import static com.example.demo.teacher.TeacherRepositoryFaker.findById;
import static com.example.demo.teacher.TeacherRepositoryFaker.teachers;

import com.example.demo.student.Student;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

  public Teacher createTeacher(Teacher newTeacher) {
    Teacher createdTeacher =
    teachers.add(newTeacher);
    return teachers.;

  }

  public Teacher updateTeacher(Long id, String lastName, int age) throws ClassNotFoundException {
    Teacher updatedTeacher = findById(id);
    updatedTeacher.setLastName(lastName);
    updatedTeacher.setAge(age);
    return updatedTeacher;

  }
  public String deleteTeacher(Long id) throws ClassNotFoundException{
    Teacher deletedTeacher = findById(id);
    teachers.remove(deletedTeacher);
    return "Teacher with " + id + "was deleted";
  }

}
