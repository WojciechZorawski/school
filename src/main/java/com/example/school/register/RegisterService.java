package com.example.school.register;

import com.example.school.exception.BusinessLogicException;
import com.example.school.student.Student;
import com.example.school.student.StudentResponseDTO;
import com.example.school.student.StudentService;
import com.example.school.teacher.Teacher;
import com.example.school.teacher.TeacherService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RegisterService {

  private final RegisterRepository registerRepository;
  private final StudentService studentService;
  private final TeacherService teacherService;

  RegisterResponseDTO getRegisterById(Long id) {
    RegisterResponseDTO takenRegister = findRegisterById(id).toResponseDto();
    return takenRegister;
  }

  List<RegisterResponseDTO> getAllRegisters() {
    List<RegisterResponseDTO> takenRegisters = registerRepository.findAll().stream()
                                                                 .map(register -> register.toResponseDto())
                                                                 .collect(Collectors.toList());
    return takenRegisters;
  }

  RegisterResponseDTO createRegister(RegisterRequestDTO newRegister) {
    Register registerEntity = newRegister.toEntity();
    Teacher teacherEntity = teacherService.createTeacherEntity(registerEntity.getTeacher());
    List<Student> listOfStudents = registerEntity.getListOfStudents();
    List<Student> studentList = listOfStudents.stream()
                                              .map(student -> studentService.createStudentEntity(student))
                                              .collect(Collectors.toList());
    registerEntity.addAllStudents(studentList);
    registerEntity.setTeacher(teacherEntity);
    Register save = registerRepository.save(registerEntity);
    return save.toResponseDto();
  }

  RegisterResponseDTO updateRegisterByTeacherId(Long registerId, Long teacherId) {
    Register register = findRegisterById(registerId);
    register.setTeacher(teacherService.findTeacherById(teacherId));
    Register updatedRegister = registerRepository.save(register);
    return updatedRegister.toResponseDto();
  }

  RegisterResponseDTO addStudentListToRegisterByStudentIds(Long registerId, List<Long> studentIds) {
    List<Student> studentListByIdList = studentService.findStudentListByIdList(studentIds);
    Register register = findRegisterById(registerId);
    studentListByIdList.forEach(newStudent -> register.addStudent(newStudent));
    Register updatedRegister = registerRepository.save(register);
    return updatedRegister.toResponseDto();
  }

  void deleteById(Long id) {
    registerRepository.deleteById(id);
  }

  List<StudentResponseDTO> getStudentsByTeacherId(Long teacherId) {
    RegisterResponseDTO registerResponseDto = getRegisterByTeacherId(teacherId);
    List<StudentResponseDTO> list = registerResponseDto.getListOfStudents();
    return list;
  }

  Map<String, List<Map<String, Double>>> getStudentsFromRegisterWithAverage(Long registerId) {
    Register takenRegister = findRegisterById(registerId);
    List<Student> takenStudents = takenRegister.getListOfStudents();
    Map<String, List<Map<String, Double>>> studentsFromRegister = new HashMap<>();
    String teacherFullName = studentService.createFullName(takenRegister.getTeacher().getName(), takenRegister.getTeacher().getLastName());
    studentsFromRegister.put(teacherFullName, getStudentsWithAverageList(takenStudents));
    return studentsFromRegister;
  }

  private Register findRegisterById(Long id) {
    return registerRepository.findById(id)
                             .orElseThrow(() -> new BusinessLogicException("Bledne id"));
  }

  private RegisterResponseDTO getRegisterByTeacherId(Long teacherId) {
    RegisterResponseDTO registerResponseDto = registerRepository.findByTeacherId(teacherId)
                                                                .orElseThrow(() -> new BusinessLogicException("Bledne id"))
                                                                .toResponseDto();
    return registerResponseDto;
  }

  private List<Map<String, Double>> getStudentsWithAverageList(List<Student> studentsList) {
    List<Map<String, Double>> studentsWithAverageList = studentsList.stream()
                                                                    .map(student -> student.getId())
                                                                    .map(studentId -> studentService.getStudentWithAverage(studentId))
                                                                    .collect(Collectors.toList());
    return studentsWithAverageList;
  }
}