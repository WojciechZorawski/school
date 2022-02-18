package com.example.demo.register;

import com.example.demo.exception.BusinessLogicException;
import com.example.demo.student.StudentRequestDTO;
import com.example.demo.student.StudentResponseDTO;
import com.example.demo.student.StudentService;
import com.example.demo.teacher.TeacherDTO;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

  RegisterRepositoryFaker registerRepositoryFaker = new RegisterRepositoryFaker();
  private final StudentService studentService;

  @Autowired
  public RegisterService(StudentService studentService) {
    this.studentService = studentService;
  }

  public RegisterResponseDTO getRegisterById(UUID id) {
    try {
      Register takenRegister = registerRepositoryFaker.findById(id);
      return takenRegister.toDto();
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Bledne id");
    }
  }

  public List<RegisterResponseDTO> getAllRegisters() {
    List<RegisterResponseDTO> takenRegisters = registerRepositoryFaker.findAll().stream()
                                                                     .map(register -> register.toDto())
                                                                     .collect(Collectors.toList());
    return takenRegisters;

  }

  public RegisterResponseDTO createRegister(RegisterRequestDTO newRegister) {
    try {
      List<StudentRequestDTO> listOfStudents = newRegister.getListOfStudents();
      if (listOfStudents != null) {
        listOfStudents.stream()
                      .forEach(StudentRequestDTO -> studentService.createStudent(StudentRequestDTO));
      }
      Register save = registerRepositoryFaker.save(newRegister.toEntity());
      return save.toDto();
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Nie udalo sie utworzyc nowego dziennika");
    }
  }

  public RegisterResponseDTO updateRegistersTeacher(UUID id, TeacherDTO teacher) {
    try {
      Register updatedRegister = registerRepositoryFaker.findById(id);
      updatedRegister.setTeacher(teacher.toEntity());
      return updatedRegister.toDto();
    } catch (ClassNotFoundException e) {
    }
    throw new BusinessLogicException("Bledne id");

  }

  public RegisterResponseDTO addStudentToRegister(UUID id, List<StudentRequestDTO> newStudent) {
    newStudent.forEach(studentDTO -> studentService.createStudent(studentDTO));
    List<StudentResponseDTO> allStudents = studentService.getAllStudents();
    Register updatedRegister;
    try {
      updatedRegister = registerRepositoryFaker.findById(id);
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Bledne id");
    }
    updatedRegister.setListOfStudents(allStudents.stream()
                                                 .map(s -> s.toRequestDto())
                                                 .map(s -> s.toEntity())
                                                 .collect(Collectors.toList()));
    return updatedRegister.toDto();
  }

  public RegisterResponseDTO removeStudentFromRegister(UUID registerId, List<UUID> studentId) {
    studentId.forEach(id -> studentService.deleteStudent(id));
    List<StudentResponseDTO> allStudents = studentService.getAllStudents();
    Register updatedRegister;
    try {
      updatedRegister = registerRepositoryFaker.findById(registerId);
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Bledne id");
    }
    updatedRegister.setListOfStudents(allStudents.stream()
                                                 .map(s -> s.toRequestDto())
                                                 .map(s -> s.toEntity())
                                                 .collect(Collectors.toList()));
    return updatedRegister.toDto();
  }

  public void deleteById(UUID id) {
    registerRepositoryFaker.deleteById(id);
  }

  private RegisterResponseDTO getRegisterByTeacherId(UUID teacherId) {
    RegisterResponseDTO registerResponseDto = registerRepositoryFaker.findyByTeacherId(teacherId)
                                                                   .orElseThrow(() -> new BusinessLogicException("Bledne id"))
                                                                   .toDto();
    return registerResponseDto;
  }

  public List<StudentResponseDTO> getStudentsByTeacherId(UUID teacherId) {
    RegisterResponseDTO registerResponseDto = getRegisterByTeacherId(teacherId);
    List<StudentResponseDTO> list = registerResponseDto.getListOfStudents();
    return list;
  }
}
