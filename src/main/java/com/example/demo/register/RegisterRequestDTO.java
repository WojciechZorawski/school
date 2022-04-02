package com.example.demo.register;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRequestDTO;
import com.example.demo.teacher.TeacherDTO;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
class RegisterRequestDTO {

  @Valid
  private TeacherDTO teacher;
  @Valid
  private List<StudentRequestDTO> listOfStudents;

  Register toEntity() {
    List<Student> list = listOfStudents != null
        ? listOfStudents.stream()
                        .map(student -> student.toEntity())
                        .collect(Collectors.toList())
        : null;
    return Register.builder()
                   .teacher(teacher.toEntity())
                   .listOfStudents(list)
                   .build();
  }
}
