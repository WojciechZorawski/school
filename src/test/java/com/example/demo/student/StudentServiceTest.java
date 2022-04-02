package com.example.demo.student;

import static com.example.demo.EntityToDtoComparatorHelper.compareGradeEntityToGradeDto;
import static com.example.demo.EntityToDtoComparatorHelper.compareStudentEntityToStudentResponseDto;
import static com.example.demo.grade.GradeFactoryFaker.toEntity;
import static com.example.demo.student.StudentFactoryFaker.getAverageFromEntity;
import static com.example.demo.student.StudentFactoryFaker.getSex;
import static com.example.demo.student.StudentFactoryFaker.getValidStudentEntity;
import static com.example.demo.student.StudentFactoryFaker.getValidStudentRequestDto;
import static com.example.demo.student.StudentFactoryFaker.toEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.grade.Grade;
import com.example.demo.grade.GradeService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

  @InjectMocks
  private StudentService studentService;
  @Mock
  private StudentRepository studentRepository;
  @Mock
  private GradeService gradeService;

  private static Stream<Arguments> provideNameAndLastNameReturnsFullName() {
    Student student = getValidStudentEntity();
    String name = student.getName();
    String lastName = student.getLastName();
    String expectedResult1 = " " + lastName;
    String expectedResult2 = name + " ";
    String expectedResult3 = name + " " + lastName;
    return Stream.of(
        Arguments.of(null, null, " "),
        Arguments.of(null, lastName, expectedResult1),
        Arguments.of(name, null, expectedResult2),
        Arguments.of(name, lastName, expectedResult3)
    );
  }

  @Test
  void getStudentByIdReturnsProperStudent() {
    studentService = new StudentService(gradeService, studentRepository);
    Student student = getValidStudentEntity();
    Long id = student.getId();

    when(studentRepository.findById(id)).thenReturn(Optional.of(student));

    StudentResponseDTO studentResponseDto = studentService.getStudentById(id);
    compareStudentEntityToStudentResponseDto(student, studentResponseDto);
  }

  @Test
  void getAllStudentsReturnsProperStudentList() {
    List<Student> entityList = List.of(getValidStudentEntity());

    when(studentRepository.findAll()).thenReturn(entityList);

    List<StudentResponseDTO> dtoList = studentService.getAllStudents();
    compareStudentEntityToStudentResponseDto(entityList.get(0), dtoList.get(0));
  }

  @Test
  void createStudentReturnsProperStudent() {
    StudentRequestDTO requestDto = getValidStudentRequestDto();
    Grade grade = toEntity(requestDto.getListOfGrades().get(0));

    when(studentRepository.save(any(Student.class))).thenReturn(toEntity(requestDto));
    when(gradeService.createGradeEntity(any(Grade.class))).thenReturn(grade);

    StudentResponseDTO createdStudent = studentService.createStudent(requestDto);
    compareStudentEntityToStudentResponseDto(toEntity(requestDto), createdStudent);
  }

  @Test
  void createStudentEntityReturnsProperStudent() {
    Student studentEntity = getValidStudentEntity();
    Grade grade = studentEntity.getListOfGrades().get(0);

    when(studentRepository.save(any(Student.class))).thenReturn(studentEntity);
    when(gradeService.createGradeEntity(any(Grade.class))).thenReturn(grade);

    Student createdStudent = studentService.createStudentEntity(studentEntity);
    assertEquals(studentEntity.getName(), createdStudent.getName());
    assertEquals(studentEntity.getLastName(), createdStudent.getLastName());
    assertEquals(studentEntity.getEmail(), createdStudent.getEmail());
    assertEquals(studentEntity.getDateOfBirth(), createdStudent.getDateOfBirth());
    assertEquals(studentEntity.getAge(), createdStudent.getAge());
    assertEquals(studentEntity.getListOfGrades().get(0).getDate(), createdStudent.getListOfGrades().get(0).getDate());
    assertEquals(studentEntity.getListOfGrades().get(0).getGrade(), createdStudent.getListOfGrades().get(0).getGrade());
    assertEquals(studentEntity.getListOfGrades().get(0).getSubject(), createdStudent.getListOfGrades().get(0).getSubject());
    assertEquals(studentEntity.getListOfGrades().get(0).getDescription(), createdStudent.getListOfGrades().get(0).getDescription());
    assertEquals(getSex(studentEntity.getName()), createdStudent.getSex());
  }

  @Test
  void updateStudentReturnsProperStudent() {
    Student entity = getValidStudentEntity();
    Long id = entity.getId();
    String name = "Anastasia";
    String email = "anastasia.paczkowska@hotmail.pl";

    when(studentRepository.findById(id)).thenReturn(Optional.of(entity));
    when(studentRepository.save(any(Student.class))).thenReturn(entity);

    StudentResponseDTO updatedStudent = studentService.updateStudent(id, name, email);
    assertEquals(name, updatedStudent.getName());
    assertEquals(entity.getLastName(), updatedStudent.getLastName());
    assertEquals(email, updatedStudent.getEmail());
    assertEquals(entity.getDateOfBirth(), updatedStudent.getDateOfBirth());
    assertEquals(entity.getAge(), updatedStudent.getAge());
    compareGradeEntityToGradeDto(entity.getListOfGrades().get(0), updatedStudent.getListOfGrades().get(0));
    assertEquals(getAverageFromEntity(entity.getListOfGrades()), updatedStudent.getAverage());
    assertEquals(getSex(name), updatedStudent.getSex());
  }

  @Test
  void getStudentWithAverageReturnsProperMap() {
    Student entity = getValidStudentEntity();
    Long id = entity.getId();

    when(studentRepository.findById(id)).thenReturn(Optional.of(entity));

    Map<String, Double> studentWithAverage = studentService.getStudentWithAverage(id);
    var firstEntry = studentWithAverage.entrySet().iterator().next();
    String key = firstEntry.getKey();
    Double value = firstEntry.getValue();
    assertEquals(entity.getName() + " " + entity.getLastName(), key);
    assertEquals(getAverageFromEntity(entity.getListOfGrades()), value);
  }

  @Test
  void deleteStudent() {
    Student studentEntity = getValidStudentEntity();
    Long id = studentEntity.getId();
    studentService.deleteStudent(id);
    verify(studentRepository, times(1)).deleteById(id);
  }

  @Test
  void findStudentListByIdListReturnsProperList() {
    List<Student> listOfStudents = List.of(getValidStudentEntity());
    List<Long> ids = List.of(listOfStudents.get(0).getId());

    when(studentRepository.findAllById(ids)).thenReturn(listOfStudents);

    List<Student> takenStudents = studentService.findStudentListByIdList(ids);
    assertEquals(listOfStudents.get(0).getName(), takenStudents.get(0).getName());
    assertEquals(listOfStudents.get(0).getLastName(), takenStudents.get(0).getLastName());
    assertEquals(listOfStudents.get(0).getEmail(), takenStudents.get(0).getEmail());
    assertEquals(listOfStudents.get(0).getDateOfBirth(), takenStudents.get(0).getDateOfBirth());
    assertEquals(listOfStudents.get(0).getAge(), takenStudents.get(0).getAge());
    assertEquals(listOfStudents.get(0).getListOfGrades().get(0).getDate(), takenStudents.get(0).getListOfGrades().get(0).getDate());
    assertEquals(listOfStudents.get(0).getListOfGrades().get(0).getGrade(), takenStudents.get(0).getListOfGrades().get(0).getGrade());
    assertEquals(listOfStudents.get(0).getListOfGrades().get(0).getSubject(), takenStudents.get(0).getListOfGrades().get(0).getSubject());
    assertEquals(listOfStudents.get(0).getListOfGrades().get(0).getDescription(), takenStudents.get(0).getListOfGrades().get(0).getDescription());
    assertEquals(listOfStudents.get(0).getSex(), takenStudents.get(0).getSex());
  }

  @ParameterizedTest
  @MethodSource("provideNameAndLastNameReturnsFullName")
  void createFullNameReturnsProperString(String name, String lastName, String expected) {
    assertEquals(expected, studentService.createFullName(name, lastName));
  }
}
