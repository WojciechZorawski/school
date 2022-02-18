package com.example.demo.register;

import com.example.demo.student.StudentResponseDTO;
import com.example.demo.teacher.TeacherDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDTO {

  private TeacherDTO teacher;
  private List<StudentResponseDTO> listOfStudents;
}
