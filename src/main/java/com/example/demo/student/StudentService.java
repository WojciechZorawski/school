package com.example.demo.student;

import com.example.demo.exception.BusinessLogicException;
import com.example.demo.grade.GradeDTO;
import com.example.demo.grade.GradeService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class StudentService {

  StudentRepositoryFaker studentRepositoryFaker = new StudentRepositoryFaker();
  private final GradeService gradeService;

  @Autowired
  public StudentService(GradeService gradeService) {
    this.gradeService = gradeService;
  }

  public StudentResponseDTO getStudentById(UUID id) {
    try {
      Student takenStudent = studentRepositoryFaker.findById(id);
      return takenStudent.toResponseDto();
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Bledne id");
    }
  }

  public List<StudentResponseDTO> getAllStudents() {
    List<StudentResponseDTO> takenStudents = studentRepositoryFaker.findAll().stream()
                                                                  .map(student -> student.toResponseDto())
                                                                  .collect(Collectors.toList());
    return takenStudents;
  }

  public StudentResponseDTO createStudent(@Valid final StudentRequestDTO newStudent) {
    try {
      List<GradeDTO> listOfGradesDto = newStudent.getListOfGrades();
      if (listOfGradesDto != null) {
        listOfGradesDto
            .forEach(gradeDTO -> gradeService.createGrade(gradeDTO));
      }
      Student save = studentRepositoryFaker.save(newStudent.toEntity());
      return save.toResponseDto();
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Nie udalo sie utworzyc nowego studenta");
    }
  }

  public StudentResponseDTO updateStudent(UUID id, String name, String email) {
    try {
      Student updatedStudent = studentRepositoryFaker.findById(id);
      updatedStudent.setName(name);
      updatedStudent.setEmail(email);
      return updatedStudent.toResponseDto();
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Bledne id");
    }
  }

  public void deleteStudent(UUID id) {
    studentRepositoryFaker.deleteById(id);
  }

}
