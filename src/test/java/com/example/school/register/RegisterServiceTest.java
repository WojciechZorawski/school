package com.example.school.register;

import static com.example.school.EntityToDtoComparatorHelper.compareStudentEntityToStudentResponseDto;
import static com.example.school.EntityToDtoComparatorHelper.compareTeacherEntityToTeacherResponseDto;
import static com.example.school.register.RegisterFactoryFaker.getValidRegisterEntity;
import static com.example.school.register.RegisterFactoryFaker.getValidRegisterRequestDto;
import static com.example.school.register.RegisterFactoryFaker.toEntity;
import static com.example.school.student.StudentFactoryFaker.getAverageFromEntity;
import static com.example.school.student.StudentFactoryFaker.getValidStudentEntity;
import static com.example.school.teacher.TeacherFactoryFaker.getValidTeacherEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.school.student.Student;
import com.example.school.student.StudentResponseDTO;
import com.example.school.student.StudentService;
import com.example.school.teacher.Teacher;
import com.example.school.teacher.TeacherService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
  private RegisterRepository registerRepository;
  @Mock
  private StudentService studentService;
  @Mock
  private TeacherService teacherService;

  @Test
  void getRegisterByIdReturnsProperRegister() {
    Register registerEntity = getValidRegisterEntity();
    Long id = registerEntity.getId();

    when(registerRepository.findById(id)).thenReturn(Optional.of(registerEntity));

    RegisterResponseDTO registerResponseDto = registerService.getRegisterById(id);
    compareRegisterEntityToRegisterResponseDto(registerEntity, registerResponseDto);
  }

  @Test
  void getAllRegistersReturnsProperList() {
    List<Register> entityList = List.of(getValidRegisterEntity());

    when(registerRepository.findAll()).thenReturn(entityList);

    List<RegisterResponseDTO> dtoList = registerService.getAllRegisters();
    compareRegisterEntityToRegisterResponseDto(entityList.get(0), dtoList.get(0));
  }

  @Test
  void createRegisterReturnsProperRegister() {
    RegisterRequestDTO requestDto = getValidRegisterRequestDto();
    Register registerEntity = toEntity(requestDto);
    Teacher teacher = registerEntity.getTeacher();
    Student student = registerEntity.getListOfStudents().get(0);

    when(registerRepository.save(any(Register.class))).thenReturn(toEntity(requestDto));
    when(teacherService.createTeacherEntity(any(Teacher.class))).thenReturn(teacher);
    when(studentService.createStudentEntity(any(Student.class))).thenReturn(student);

    RegisterResponseDTO createdRegister = registerService.createRegister(requestDto);
    compareRegisterEntityToRegisterResponseDto(toEntity(requestDto), createdRegister);
  }

  @Test
  void updateRegisterByTeacherIdReturnsProperRegister() {
    Register registerEntity = getValidRegisterEntity();
    Long registerId = registerEntity.getId();
    Teacher teacher = getValidTeacherEntity();
    Long teacherId = teacher.getId();

    when(registerRepository.findById(registerId)).thenReturn(Optional.of(registerEntity));
    when(registerRepository.save(any(Register.class))).thenReturn(registerEntity);
    when(teacherService.findTeacherById(teacherId)).thenReturn(teacher);

    RegisterResponseDTO updatedRegister = registerService.updateRegisterByTeacherId(registerId, teacherId);
    compareRegisterEntityToRegisterResponseDto(registerEntity, updatedRegister);
  }

  @Test
  void addStudentListToRegisterByStudentIdsReturnsProperRegister() {
    Register registerEntity = getValidRegisterEntity();
    Long id = registerEntity.getId();
    List<Student> listOfStudents = List.of(getValidStudentEntity());
    List<Long> listOfStudentsIds = List.of(listOfStudents.get(0).getId());

    when(registerRepository.findById(id)).thenReturn(Optional.of(registerEntity));
    when(studentService.findStudentListByIdList(listOfStudentsIds)).thenReturn(listOfStudents);
    when(registerRepository.save(any(Register.class))).thenReturn(registerEntity);

    RegisterResponseDTO updatedRegister = registerService.addStudentListToRegisterByStudentIds(id, listOfStudentsIds);
    compareRegisterEntityToRegisterResponseDto(registerEntity, updatedRegister);
  }

  @Test
  void deleteById() {
    Register registerEntity = getValidRegisterEntity();
    Long id = registerEntity.getId();
    registerService.deleteById(id);
    verify(registerRepository, times(1)).deleteById(id);
  }

  @Test
  void getStudentsByTeacherIdReturnsProperList() {
    Register registerEntity = getValidRegisterEntity();
    Teacher teacherEntity = registerEntity.getTeacher();
    Long id = teacherEntity.getId();
    List<Student> entityList = registerEntity.getListOfStudents();

    when(registerRepository.findByTeacherId(id)).thenReturn(Optional.of(registerEntity));

    List<StudentResponseDTO> responseDtoList = registerService.getStudentsByTeacherId(id);
    compareStudentEntityToStudentResponseDto(entityList.get(0), responseDtoList.get(0));
  }

  @Test
  void getStudentsFromRegisterWithAverageReturnsProperMap() {
    Register registerEntity = getValidRegisterEntity();
    Long registerId = registerEntity.getId();
    List<Student> studentEntityList = registerEntity.getListOfStudents();
    Student studentEntity = studentEntityList.get(0);
    Long studentId = studentEntity.getId();
    Teacher teacherEntity = registerEntity.getTeacher();
    Map<String, Double> studentWithAverage = new HashMap<>();
    studentWithAverage.put(studentEntity.getName() + " " + studentEntity.getLastName(), getAverageFromEntity(studentEntity.getListOfGrades()));
    Map<String, List<Map<String, Double>>> studentsFromRegisterWithAverage = new HashMap<>();
    studentsFromRegisterWithAverage.put(teacherEntity.getName() + " " + teacherEntity.getLastName(),
                                        List.of(studentWithAverage));

    when(registerRepository.findById(registerId)).thenReturn(Optional.of(registerEntity));
    when(studentService.getStudentWithAverage(studentId)).thenReturn(studentWithAverage);
    when(studentService.createFullName(any(String.class), any(String.class))).thenReturn(teacherEntity.getName() + " " + teacherEntity.getLastName());

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

  private void compareRegisterEntityToRegisterResponseDto(Register registerEntity, RegisterResponseDTO registerResponseDto) {
    compareTeacherEntityToTeacherResponseDto(registerEntity.getTeacher(), registerResponseDto.getTeacher());
    compareStudentEntityToStudentResponseDto(registerEntity.getListOfStudents().get(0), registerResponseDto.getListOfStudents().get(0));
  }

}

