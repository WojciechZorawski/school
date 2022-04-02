package com.example.demo.student;

import com.example.demo.exception.BusinessLogicException;
import com.example.demo.grade.Grade;
import com.example.demo.grade.GradeService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class StudentService {

  private final GradeService gradeService;
  private final StudentRepository studentRepository;

  StudentResponseDTO getStudentById(Long id) {
    Student takenStudent = findStudentById(id);
    return takenStudent.toResponseDto();
  }

  public List<StudentResponseDTO> getAllStudents() {
    List<StudentResponseDTO> takenStudents = studentRepository.findAll().stream()
                                                              .map(student -> student.toResponseDto())
                                                              .collect(Collectors.toList());
    return takenStudents;
  }

  public StudentResponseDTO createStudent(@Valid final StudentRequestDTO newStudent) {
    Student studentEntity = newStudent.toEntity();
    Student save = createStudentEntity(studentEntity);
    return save.toResponseDto();
  }

  public Student createStudentEntity(Student studentEntity) {
    List<Grade> listOfGrades = studentEntity.getListOfGrades();
    listOfGrades.forEach(grade -> gradeService.createGradeEntity(grade));
    Student save = studentRepository.save(studentEntity);
    return save;
  }

  StudentResponseDTO updateStudent(Long id, String name, String email) {
    Student updatedStudent = findStudentById(id);
    updatedStudent.setName(name);
    updatedStudent.setEmail(email);
    return studentRepository.save(updatedStudent).toResponseDto();
  }

  public void deleteStudent(Long id) {
    studentRepository.deleteById(id);
  }

  public String createFullName(@Nullable String name, @Nullable String lastName) {
    return Objects.toString(name, "") + " " + Objects.toString(lastName, "");
  }

  public Map<String, Double> getStudentWithAverage(Long studentId) {
    Map<String, Double> studentWithAverage = new HashMap<>();
    Student takenStudent = findStudentById(studentId);
    studentWithAverage.put(createFullName(takenStudent.getName(), takenStudent.getLastName()),
                           takenStudent
                               .getListOfGrades()
                               .stream()
                               .mapToDouble(grade -> grade.getGrade())
                               .average()
                               .orElse(0.0));
    return studentWithAverage;
  }

  private Student findStudentById(Long id) {
    return studentRepository.findById(id)
                            .orElseThrow(() -> new BusinessLogicException("Bledne id"));
  }

  public List<Student> findStudentListByIdList(List<Long> studentIds) {
    return studentRepository.findAllById(studentIds);
  }
}
