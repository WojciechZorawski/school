package com.example.school.register;

import com.example.school.student.StudentResponseDTO;
import com.example.school.teacher.TeacherDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDTO {

  private TeacherDTO teacher;
  private List<StudentResponseDTO> listOfStudents;

}
