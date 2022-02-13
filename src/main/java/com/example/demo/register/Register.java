package com.example.demo.register;

import java.util.List;
import java.util.UUID;

import com.example.demo.student.Student;
import com.example.demo.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Register {

  private UUID id;
  private Teacher teacher;
  private List<Student> listOfStudents;

}
