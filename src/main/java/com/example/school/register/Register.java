package com.example.school.register;

import com.example.school.BaseEntity;
import com.example.school.student.Student;
import com.example.school.student.StudentResponseDTO;
import com.example.school.teacher.Teacher;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "register")
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Register extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "teacher_id", referencedColumnName = "id")
  private Teacher teacher;
  @Default
  @OneToMany
  @JoinColumn(name = "register_id")
  private List<Student> listOfStudents = new ArrayList<>();

  RegisterResponseDTO toResponseDto() {
    List<StudentResponseDTO> list = listOfStudents != null
        ? listOfStudents.stream()
                        .map(student -> student.toResponseDto())
                        .collect(Collectors.toList())
        : new ArrayList<>();
    return RegisterResponseDTO.builder()
                              .teacher(teacher.toDto())
                              .listOfStudents(list)
                              .build();
  }

  public void addAllStudents(List<Student> newStudent) {
    listOfStudents.clear();
    listOfStudents.addAll(newStudent);
  }

  public void addStudent(Student student) {
    listOfStudents.add(student);
  }
}
