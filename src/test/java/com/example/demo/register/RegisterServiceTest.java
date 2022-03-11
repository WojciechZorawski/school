package com.example.demo.register;

import static com.example.demo.register.RegisterFactoryFaker.getValidRegisterEntity;
import static com.example.demo.register.RegisterFactoryFaker.getValidRegisterRequestDto;
import static com.example.demo.register.RegisterFactoryFaker.toEntity;
import static com.example.demo.student.StudentFactoryFaker.getAverageFromDto;
import static com.example.demo.student.StudentFactoryFaker.getAverageFromEntity;
import static com.example.demo.student.StudentFactoryFaker.getSex;
import static com.example.demo.student.StudentFactoryFaker.getValidStudentRequestDto;
import static com.example.demo.student.StudentFactoryFaker.toResponseDto;
import static com.example.demo.student.StudentFactoryFaker.toResponseDtoList;
import static com.example.demo.teacher.TeacherFactoryFaker.getValidTeacherEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRequestDTO;
import com.example.demo.student.StudentResponseDTO;
import com.example.demo.student.StudentService;
import com.example.demo.teacher.Teacher;
import com.example.demo.teacher.TeacherFactoryFaker;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegisterServiceTest {

  @InjectMocks
  private RegisterService registerService;
  @Mock
  private RegisterRepositoryFaker registerRepositoryFaker;
  @Mock
  private StudentService studentService;

  @Test
  void getRegisterByIdReturnsProperRegister() throws ClassNotFoundException {
    registerService = new RegisterService(studentService, registerRepositoryFaker);
    Register registerEntity = getValidRegisterEntity();
    UUID id = registerEntity.getId();

    when(registerRepositoryFaker.findById(id)).thenReturn(registerEntity);

    RegisterResponseDTO registerResponseDto = registerService.getRegisterById(id);
    assertEquals(registerEntity.getTeacher().getName(), registerResponseDto.getTeacher().getName());
    assertEquals(registerEntity.getTeacher().getLastName(), registerResponseDto.getTeacher().getLastName());
    assertEquals(registerEntity.getTeacher().getAge(), registerResponseDto.getTeacher().getAge());
    assertEquals(registerEntity.getTeacher().getProfession(), registerResponseDto.getTeacher().getProfession());
    assertEquals(registerEntity.getListOfStudents().get(0).getName(), registerResponseDto.getListOfStudents().get(0).getName());
    assertEquals(registerEntity.getListOfStudents().get(0).getLastName(), registerResponseDto.getListOfStudents().get(0).getLastName());
    assertEquals(registerEntity.getListOfStudents().get(0).getEmail(), registerResponseDto.getListOfStudents().get(0).getEmail());
    assertEquals(registerEntity.getListOfStudents().get(0).getDateOfBirth(), registerResponseDto.getListOfStudents().get(0).getDateOfBirth());
    assertEquals(registerEntity.getListOfStudents().get(0).getAge(), registerResponseDto.getListOfStudents().get(0).getAge());
    assertEquals(registerEntity.getListOfStudents().get(0).getListOfGrades().get(0).getDate(), registerResponseDto.getListOfStudents()
                                                                                                                  .get(0)
                                                                                                                  .getListOfGrades()
                                                                                                                  .get(0)
                                                                                                                  .getDate());
    assertEquals(registerEntity.getListOfStudents().get(0).getListOfGrades().get(0).getGrade(), registerResponseDto.getListOfStudents()
                                                                                                                   .get(0)
                                                                                                                   .getListOfGrades()
                                                                                                                   .get(0)
                                                                                                                   .getGrade());
    assertEquals(registerEntity.getListOfStudents().get(0).getListOfGrades().get(0).getSubject(), registerResponseDto.getListOfStudents()
                                                                                                                     .get(0)
                                                                                                                     .getListOfGrades()
                                                                                                                     .get(0)
                                                                                                                     .getSubject());
    assertEquals(getSex(registerEntity.getListOfStudents().get(0).getName()), registerResponseDto.getListOfStudents().get(0).getSex());
    assertEquals(getAverageFromEntity(registerEntity.getListOfStudents().get(0).getListOfGrades()),
                 registerResponseDto.getListOfStudents().get(0).getAverage());
  }

  @Test
  void getAllRegistersReturnsProperList() {
    List<Register> entityList = List.of(getValidRegisterEntity(), getValidRegisterEntity());

    when(registerRepositoryFaker.findAll()).thenReturn(entityList);

    List<RegisterResponseDTO> dtoList = registerService.getAllRegisters();
    assertEquals(entityList.get(0).getTeacher().getName(), dtoList.get(0).getTeacher().getName());
    assertEquals(entityList.get(0).getTeacher().getLastName(), dtoList.get(0).getTeacher().getLastName());
    assertEquals(entityList.get(0).getTeacher().getAge(), dtoList.get(0).getTeacher().getAge());
    assertEquals(entityList.get(0).getTeacher().getProfession(), dtoList.get(0).getTeacher().getProfession());
    assertEquals(entityList.get(0).getListOfStudents().get(0).getName(), dtoList.get(0).getListOfStudents().get(0).getName());
    assertEquals(entityList.get(0).getListOfStudents().get(0).getLastName(), dtoList.get(0).getListOfStudents().get(0).getLastName());
    assertEquals(entityList.get(0).getListOfStudents().get(0).getEmail(), dtoList.get(0).getListOfStudents().get(0).getEmail());
    assertEquals(entityList.get(0).getListOfStudents().get(0).getDateOfBirth(), dtoList.get(0).getListOfStudents().get(0).getDateOfBirth());
    assertEquals(entityList.get(0).getListOfStudents().get(0).getAge(), dtoList.get(0).getListOfStudents().get(0).getAge());
    assertEquals(entityList.get(0).getListOfStudents().get(0).getListOfGrades().get(0).getDate(), dtoList.get(0)
                                                                                                         .getListOfStudents()
                                                                                                         .get(0)
                                                                                                         .getListOfGrades()
                                                                                                         .get(0)
                                                                                                         .getDate());
    assertEquals(entityList.get(0).getListOfStudents().get(0).getListOfGrades().get(0).getGrade(), dtoList.get(0)
                                                                                                          .getListOfStudents()
                                                                                                          .get(0)
                                                                                                          .getListOfGrades()
                                                                                                          .get(0)
                                                                                                          .getGrade());
    assertEquals(entityList.get(0).getListOfStudents().get(0).getListOfGrades().get(0).getSubject(), dtoList.get(0)
                                                                                                            .getListOfStudents()
                                                                                                            .get(0)
                                                                                                            .getListOfGrades()
                                                                                                            .get(0)
                                                                                                            .getSubject());
    assertEquals(getSex(entityList.get(0).getListOfStudents().get(0).getName()), dtoList.get(0).getListOfStudents().get(0).getSex());
    assertEquals(getAverageFromEntity(entityList.get(0).getListOfStudents().get(0).getListOfGrades()), dtoList.get(0).getListOfStudents().get(0).getAverage());
  }

  @Test
  void createRegisterReturnsProperRegister() throws ClassNotFoundException {
    RegisterRequestDTO requestDto = getValidRegisterRequestDto();

    when(registerRepositoryFaker.save(any(Register.class))).thenReturn(toEntity(requestDto));

    RegisterResponseDTO createdRegister = registerService.createRegister(requestDto);
    assertEquals(requestDto.getTeacher().getName(), createdRegister.getTeacher().getName());
    assertEquals(requestDto.getTeacher().getLastName(), createdRegister.getTeacher().getLastName());
    assertEquals(requestDto.getTeacher().getAge(), createdRegister.getTeacher().getAge());
    assertEquals(requestDto.getTeacher().getProfession(), createdRegister.getTeacher().getProfession());
    assertEquals(requestDto.getListOfStudents().get(0).getName(), createdRegister.getListOfStudents().get(0).getName());
    assertEquals(requestDto.getListOfStudents().get(0).getLastName(), createdRegister.getListOfStudents().get(0).getLastName());
    assertEquals(requestDto.getListOfStudents().get(0).getEmail(), createdRegister.getListOfStudents().get(0).getEmail());
    assertEquals(requestDto.getListOfStudents().get(0).getDateOfBirth(), createdRegister.getListOfStudents().get(0).getDateOfBirth());
    assertEquals(requestDto.getListOfStudents().get(0).getAge(), createdRegister.getListOfStudents().get(0).getAge());
    assertEquals(requestDto.getListOfStudents().get(0).getListOfGrades().get(0).getDate(), createdRegister
        .getListOfStudents()
        .get(0)
        .getListOfGrades()
        .get(0)
        .getDate());
    assertEquals(requestDto.getListOfStudents().get(0).getListOfGrades().get(0).getGrade(), createdRegister
        .getListOfStudents()
        .get(0)
        .getListOfGrades()
        .get(0)
        .getGrade());
    assertEquals(requestDto.getListOfStudents().get(0).getListOfGrades().get(0).getSubject(), createdRegister
        .getListOfStudents()
        .get(0)
        .getListOfGrades()
        .get(0)
        .getSubject());
    assertEquals(getSex(requestDto.getListOfStudents().get(0).getName()), createdRegister.getListOfStudents().get(0).getSex());
    assertEquals(getAverageFromDto(requestDto.getListOfStudents().get(0).getListOfGrades()), createdRegister.getListOfStudents().get(0).getAverage());
  }

  @Test
  void updateRegistersTeacherReturnsProperRegister() throws ClassNotFoundException {
    Register registerEntity = getValidRegisterEntity();
    UUID id = registerEntity.getId();
    Teacher teacher = getValidTeacherEntity();

    when(registerRepositoryFaker.findById(id)).thenReturn(registerEntity);

    RegisterResponseDTO updatedRegister = registerService.updateRegistersTeacher(id, TeacherFactoryFaker.toDto(teacher));
    assertEquals(registerEntity.getTeacher().getName(), updatedRegister.getTeacher().getName());
    assertEquals(registerEntity.getTeacher().getLastName(), updatedRegister.getTeacher().getLastName());
    assertEquals(registerEntity.getTeacher().getAge(), updatedRegister.getTeacher().getAge());
    assertEquals(registerEntity.getTeacher().getProfession(), updatedRegister.getTeacher().getProfession());
    assertEquals(registerEntity.getListOfStudents().get(0).getName(), updatedRegister.getListOfStudents().get(0).getName());
    assertEquals(registerEntity.getListOfStudents().get(0).getLastName(), updatedRegister.getListOfStudents().get(0).getLastName());
    assertEquals(registerEntity.getListOfStudents().get(0).getEmail(), updatedRegister.getListOfStudents().get(0).getEmail());
    assertEquals(registerEntity.getListOfStudents().get(0).getDateOfBirth(), updatedRegister.getListOfStudents().get(0).getDateOfBirth());
    assertEquals(registerEntity.getListOfStudents().get(0).getAge(), updatedRegister.getListOfStudents().get(0).getAge());
    assertEquals(registerEntity.getListOfStudents().get(0).getListOfGrades().get(0).getDate(), updatedRegister.getListOfStudents()
                                                                                                              .get(0)
                                                                                                              .getListOfGrades()
                                                                                                              .get(0)
                                                                                                              .getDate());
    assertEquals(registerEntity.getListOfStudents().get(0).getListOfGrades().get(0).getGrade(), updatedRegister.getListOfStudents()
                                                                                                               .get(0)
                                                                                                               .getListOfGrades()
                                                                                                               .get(0)
                                                                                                               .getGrade());
    assertEquals(registerEntity.getListOfStudents().get(0).getListOfGrades().get(0).getSubject(), updatedRegister.getListOfStudents()
                                                                                                                 .get(0)
                                                                                                                 .getListOfGrades()
                                                                                                                 .get(0)
                                                                                                                 .getSubject());
    assertEquals(getSex(registerEntity.getListOfStudents().get(0).getName()), updatedRegister.getListOfStudents().get(0).getSex());
    assertEquals(getAverageFromEntity(registerEntity.getListOfStudents().get(0).getListOfGrades()),
                 updatedRegister.getListOfStudents().get(0).getAverage());

  }

  @Test
  void addStudentToRegisterReturnsProperRegister() throws ClassNotFoundException {
    Register registerEntity = getValidRegisterEntity();
    UUID id = registerEntity.getId();
    List<StudentRequestDTO> listOfStudents = List.of(getValidStudentRequestDto());

    when(registerRepositoryFaker.findById(id)).thenReturn(registerEntity);
    when(studentService.createStudent(listOfStudents.get(0))).thenReturn(toResponseDto(listOfStudents.get(0)));
    when(studentService.getAllStudents()).thenReturn(toResponseDtoList(listOfStudents));

    RegisterResponseDTO updatedRegister = registerService.addStudentToRegister(id, listOfStudents);
    assertEquals(registerEntity.getTeacher().getName(), updatedRegister.getTeacher().getName());
    assertEquals(registerEntity.getTeacher().getLastName(), updatedRegister.getTeacher().getLastName());
    assertEquals(registerEntity.getTeacher().getAge(), updatedRegister.getTeacher().getAge());
    assertEquals(registerEntity.getTeacher().getProfession(), updatedRegister.getTeacher().getProfession());
    assertEquals(registerEntity.getListOfStudents().get(0).getName(), updatedRegister.getListOfStudents().get(0).getName());
    assertEquals(registerEntity.getListOfStudents().get(0).getLastName(), updatedRegister.getListOfStudents().get(0).getLastName());
    assertEquals(registerEntity.getListOfStudents().get(0).getEmail(), updatedRegister.getListOfStudents().get(0).getEmail());
    assertEquals(registerEntity.getListOfStudents().get(0).getDateOfBirth(), updatedRegister.getListOfStudents().get(0).getDateOfBirth());
    assertEquals(registerEntity.getListOfStudents().get(0).getAge(), updatedRegister.getListOfStudents().get(0).getAge());
    assertEquals(registerEntity.getListOfStudents().get(0).getListOfGrades().get(0).getDate(), updatedRegister.getListOfStudents()
                                                                                                              .get(0)
                                                                                                              .getListOfGrades()
                                                                                                              .get(0)
                                                                                                              .getDate());
    assertEquals(registerEntity.getListOfStudents().get(0).getListOfGrades().get(0).getGrade(), updatedRegister.getListOfStudents()
                                                                                                               .get(0)
                                                                                                               .getListOfGrades()
                                                                                                               .get(0)
                                                                                                               .getGrade());
    assertEquals(registerEntity.getListOfStudents().get(0).getListOfGrades().get(0).getSubject(), updatedRegister.getListOfStudents()
                                                                                                                 .get(0)
                                                                                                                 .getListOfGrades()
                                                                                                                 .get(0)
                                                                                                                 .getSubject());
    assertEquals(getSex(registerEntity.getListOfStudents().get(0).getName()), updatedRegister.getListOfStudents().get(0).getSex());
    assertEquals(getAverageFromEntity(registerEntity.getListOfStudents().get(0).getListOfGrades()),
                 updatedRegister.getListOfStudents().get(0).getAverage());

  }

  @Test
  void deleteById() {
    Register registerEntity = getValidRegisterEntity();
    UUID id = registerEntity.getId();
    registerService.deleteById(id);
    verify(registerRepositoryFaker, times(1)).deleteById(id);
  }

  @Test
  void getStudentsByTeacherIdReturnsProperList() {
    Register registerEntity = getValidRegisterEntity();
    Teacher teacherEntity = registerEntity.getTeacher();
    UUID id = teacherEntity.getId();
    List<Student> entityList = registerEntity.getListOfStudents();

    when(registerRepositoryFaker.findByTeacherId(id)).thenReturn(Optional.of(registerEntity));

    List<StudentResponseDTO> responseDtoList = registerService.getStudentsByTeacherId(id);
    assertEquals(entityList.get(0).getName(), responseDtoList.get(0).getName());
    assertEquals(entityList.get(0).getLastName(), responseDtoList.get(0).getLastName());
    assertEquals(entityList.get(0).getEmail(), responseDtoList.get(0).getEmail());
    assertEquals(entityList.get(0).getDateOfBirth(), responseDtoList.get(0).getDateOfBirth());
    assertEquals(entityList.get(0).getAge(), responseDtoList.get(0).getAge());
    assertEquals(entityList.get(0).getListOfGrades().get(0).getDate(), responseDtoList.get(0).getListOfGrades().get(0).getDate());
    assertEquals(entityList.get(0).getListOfGrades().get(0).getGrade(), responseDtoList.get(0).getListOfGrades().get(0).getGrade());
    assertEquals(entityList.get(0).getListOfGrades().get(0).getSubject(), responseDtoList.get(0).getListOfGrades().get(0).getSubject());
    assertEquals(getSex(entityList.get(0).getName()), responseDtoList.get(0).getSex());
    assertEquals(getAverageFromEntity(entityList.get(0).getListOfGrades()), responseDtoList.get(0).getAverage());
  }

  @Test
  void getStudentsFromRegisterWithAverageReturnsProperMap() throws ClassNotFoundException {
    Register registerEntity = getValidRegisterEntity();
    UUID registerId = registerEntity.getId();
    List<Student> studentEntityList = registerEntity.getListOfStudents();
    Student studentEntity = studentEntityList.get(0);
    UUID studentId = studentEntity.getId();
    Teacher teacherEntity = registerEntity.getTeacher();
    Map<String, Double> studentWithAverage = new HashMap<>();
    studentWithAverage.put(studentEntity.getName() + " " + studentEntity.getLastName(), getAverageFromEntity(studentEntity.getListOfGrades()));
    Map<String, List<Map<String, Double>>> studentsFromRegisterWithAverage = new HashMap<>();
    studentsFromRegisterWithAverage.put(teacherEntity.getName() + " " + teacherEntity.getLastName(),
                             List.of(studentWithAverage));

    when(registerRepositoryFaker.findById(registerId)).thenReturn(registerEntity);
    when(studentService.getStudentWithAverage(studentId)).thenReturn(studentWithAverage);

    Map<String, List<Map<String, Double>>> takenStudentsFromRegisterWithAverage = registerService.getStudentsFromRegisterWithAverage(registerId);
    var firstEntry = studentsFromRegisterWithAverage.entrySet().iterator().next();
    String studentsFromRegisterWithAverageKey = firstEntry.getKey();
    String studentsFromRegisterWithAverageValueString = firstEntry.getValue().get(0).entrySet().iterator().next().getKey();
    Double studentsFromRegisterWithAverageValueDouble = firstEntry.getValue().get(0).entrySet().iterator().next().getValue();
    var secondEntry = takenStudentsFromRegisterWithAverage.entrySet().iterator().next();
    String takenStudentsFromRegisterWithAverageKey = secondEntry.getKey();
    String takenStudentsFromRegisterWithAverageValueString = secondEntry.getValue().get(0).entrySet().iterator().next().getKey();
    Double takenStudentsFromRegisterWithAverageValueDouble = secondEntry.getValue().get(0).entrySet().iterator().next().getValue();
    assertEquals(studentsFromRegisterWithAverageKey, takenStudentsFromRegisterWithAverageKey);
    assertEquals(studentsFromRegisterWithAverageValueString, takenStudentsFromRegisterWithAverageValueString);
    assertEquals(studentsFromRegisterWithAverageValueDouble, takenStudentsFromRegisterWithAverageValueDouble);
  }
}

