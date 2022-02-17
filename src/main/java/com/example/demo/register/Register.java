package com.example.demo.register;

import com.example.demo.student.Student;
import com.example.demo.student.StudentDTO;
import com.example.demo.teacher.Teacher;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
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

  public RegisterDTO toDto() {
    List<StudentDTO> list = listOfStudents != null
        ? listOfStudents.stream()
                        .map(student -> student.toDto())
                        .collect(Collectors.toList())
        : null;
    return RegisterDTO.builder()
                      .teacher(teacher.toDto())
                      .listOfStudents(list)
                      .build();
  }

}
