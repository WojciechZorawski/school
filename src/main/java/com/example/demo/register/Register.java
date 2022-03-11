package com.example.demo.register;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRequestDTO;
import com.example.demo.student.StudentResponseDTO;
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

  RegisterResponseDTO toDto() {
    List<StudentResponseDTO> list = listOfStudents != null
        ? listOfStudents.stream()
                        .map(student -> student.toResponseDto())
                        .collect(Collectors.toList())
        : null;
    return RegisterResponseDTO.builder()
                             .teacher(teacher.toDto())
                             .listOfStudents(list)
                             .build();
  }

}
