package com.example.demo.student;

import static com.example.demo.student.StudentFactoryFaker.getAverageFromDto;
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

import com.example.demo.grade.GradeService;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

  @InjectMocks
  private StudentService studentService;
  @Mock
  private StudentRepositoryFaker studentRepositoryFaker;
  @Mock
  private GradeService gradeService;

  @Test
  void getStudentByIdReturnsProperStudent() throws ClassNotFoundException {
    studentService = new StudentService(gradeService,studentRepositoryFaker);
    Student student = getValidStudentEntity();
    UUID id = student.getId();

    when(studentRepositoryFaker.findById(id)).thenReturn(student);

    StudentResponseDTO studentResponseDto = studentService.getStudentById(id);
    assertEquals(student.getName(), studentResponseDto.getName());
    assertEquals(student.getLastName(), studentResponseDto.getLastName());
    assertEquals(student.getEmail(), studentResponseDto.getEmail());
    assertEquals(student.getDateOfBirth(), studentResponseDto.getDateOfBirth());
    assertEquals(student.getAge(), studentResponseDto.getAge());
    assertEquals(student.getListOfGrades().get(0).getDate(), studentResponseDto.getListOfGrades().get(0).getDate());
    assertEquals(student.getListOfGrades().get(0).getGrade(), studentResponseDto.getListOfGrades().get(0).getGrade());
    assertEquals(student.getListOfGrades().get(0).getSubject(), studentResponseDto.getListOfGrades().get(0).getSubject());
    assertEquals(getAverageFromEntity(student.getListOfGrades()), studentResponseDto.getAverage());
    assertEquals(getSex(student.getName()), studentResponseDto.getSex());
  }

  @Test
  void getAllStudentsReturnsProperStudentList(){
    List<Student> entityList = List.of(getValidStudentEntity(), getValidStudentEntity());

    when(studentRepositoryFaker.findAll()).thenReturn(entityList);

    List<StudentResponseDTO> dtoList = studentService.getAllStudents();
    assertEquals(entityList.get(0).getName(), dtoList.get(0).getName());
    assertEquals(entityList.get(0).getLastName(), dtoList.get(0).getLastName());
    assertEquals(entityList.get(0).getEmail(), dtoList.get(0).getEmail());
    assertEquals(entityList.get(0).getDateOfBirth(), dtoList.get(0).getDateOfBirth());
    assertEquals(entityList.get(0).getAge(), dtoList.get(0).getAge());
    assertEquals(entityList.get(0).getAge(), dtoList.get(0).getAge());
    assertEquals(entityList.get(0).getListOfGrades().get(0).getDate(), dtoList.get(0).getListOfGrades().get(0).getDate());
    assertEquals(entityList.get(0).getListOfGrades().get(0).getGrade(), dtoList.get(0).getListOfGrades().get(0).getGrade());
    assertEquals(entityList.get(0).getListOfGrades().get(0).getSubject(), dtoList.get(0).getListOfGrades().get(0).getSubject());
    assertEquals(getAverageFromEntity(entityList.get(0).getListOfGrades()), dtoList.get(0).getAverage());
    assertEquals(getSex(entityList.get(0).getName()), dtoList.get(0).getSex());
  }

  @Test
  void createStudentReturnsProperStudent() throws ClassNotFoundException {
    StudentRequestDTO requestDto = getValidStudentRequestDto();

    when(studentRepositoryFaker.save(any(Student.class))).thenReturn(toEntity(requestDto));

    StudentResponseDTO createdStudent = studentService.createStudent(requestDto);
    assertEquals(requestDto.getName(), createdStudent.getName());
    assertEquals(requestDto.getLastName(), createdStudent.getLastName());
    assertEquals(requestDto.getEmail(), createdStudent.getEmail());
    assertEquals(requestDto.getDateOfBirth(), createdStudent.getDateOfBirth());
    assertEquals(requestDto.getAge(), createdStudent.getAge());
    assertEquals(requestDto.getListOfGrades().get(0).getDate(), createdStudent.getListOfGrades().get(0).getDate());
    assertEquals(requestDto.getListOfGrades().get(0).getGrade(), createdStudent.getListOfGrades().get(0).getGrade());
    assertEquals(requestDto.getListOfGrades().get(0).getSubject(), createdStudent.getListOfGrades().get(0).getSubject());
    assertEquals(getAverageFromDto(requestDto.getListOfGrades()), createdStudent.getAverage());
    assertEquals(getSex(requestDto.getName()), createdStudent.getSex());

  }

  @Test
  void updateStudentReturnsProperStudent() throws ClassNotFoundException {
    Student entity = getValidStudentEntity();
    UUID id = entity.getId();
    String name = "Anastasia";
    String email = "anastasia.paczkowska@hotmail.pl";

    when(studentRepositoryFaker.findById(id)).thenReturn(entity);

    StudentResponseDTO updatedStudent = studentService.updateStudent(id, name, email);
    assertEquals(name, updatedStudent.getName());
    assertEquals(entity.getLastName(), updatedStudent.getLastName());
    assertEquals(email, updatedStudent.getEmail());
    assertEquals(entity.getDateOfBirth(), updatedStudent.getDateOfBirth());
    assertEquals(entity.getAge(), updatedStudent.getAge());
    assertEquals(entity.getAge(), updatedStudent.getAge());
    assertEquals(entity.getListOfGrades().get(0).getDate(), updatedStudent.getListOfGrades().get(0).getDate());
    assertEquals(entity.getListOfGrades().get(0).getGrade(), updatedStudent.getListOfGrades().get(0).getGrade());
    assertEquals(entity.getListOfGrades().get(0).getSubject(), updatedStudent.getListOfGrades().get(0).getSubject());
    assertEquals(getAverageFromEntity(entity.getListOfGrades()), updatedStudent.getAverage());
    assertEquals(getSex(name), updatedStudent.getSex());
  }

  @Test
  void getStudentWithAverageReturnsProperMap() throws ClassNotFoundException {
    Student entity = getValidStudentEntity();
    UUID id = entity.getId();


    when(studentRepositoryFaker.findById(id)).thenReturn(entity);

    Map<String, Double> studentWithAverage = studentService.getStudentWithAverage(id);
    var firstEntry = studentWithAverage.entrySet().iterator().next();
    String key = firstEntry.getKey();
    Double value = firstEntry.getValue();
    assertEquals(entity.getName() + " " + entity.getLastName(), key);
    assertEquals(getAverageFromEntity(entity.getListOfGrades()), value);
  }

  @Test
  void deleteStudent(){
    Student studentEntity = getValidStudentEntity();
    UUID id = studentEntity.getId();
    studentService.deleteStudent(id);
    verify(studentRepositoryFaker, times(1)).deleteById(id);
  }

}
