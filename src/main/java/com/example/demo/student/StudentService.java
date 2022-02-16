package com.example.demo.student;

import com.example.demo.exception.BusinessLogicException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  StudentRepositoryFaker studentRepositoryFaker = new StudentRepositoryFaker();

  public StudentDTO getStudentById(UUID id) {
    try {
      Student takenStudent = studentRepositoryFaker.findById(id);
      return takenStudent.toDto();
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Bledne id");
    }
  }

  public List<StudentDTO> getAllStudents() {
    List<StudentDTO> takenStudents = studentRepositoryFaker.findAll().stream()
        .map(student -> student.toDto())
        .collect(Collectors.toList());
    return takenStudents;
  }

  public StudentDTO createStudent(StudentDTO newStudent) {
    try {
      Student save = studentRepositoryFaker.save(newStudent.toEntity());
      return save.toDto();
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Nie udalo sie utworzyc nowego studenta");
    }
  }

  public StudentDTO updateStudent(UUID id, String name, String email) {
    try {
      Student updatedStudent = studentRepositoryFaker.findById(id);
      updatedStudent.setName(name);
      updatedStudent.setEmail(email);
      return updatedStudent.toDto();
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Bledne id");
    }
  }

  public void deleteStudent(UUID id) {
    studentRepositoryFaker.deleteById(id);
  }

}
