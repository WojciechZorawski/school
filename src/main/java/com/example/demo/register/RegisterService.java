package com.example.demo.register;

import com.example.demo.exception.BusinessLogicException;
import com.example.demo.student.StudentDTO;
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

  public RegisterDTO getRegisterById(UUID id) {
    try {
      Register takenRegister = registerRepositoryFaker.findById(id);
      return takenRegister.toDto();
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Bledne id");
    }
  }

  public List<RegisterDTO> getAllRegisters() {
    List<RegisterDTO> takenRegisters = registerRepositoryFaker.findAll().stream()
                                                              .map(register -> register.toDto())
                                                              .collect(Collectors.toList());
    return takenRegisters;

  }

  public RegisterDTO createRegister(RegisterDTO newRegister) {
    try {
      List<StudentDTO> listOfStudents = newRegister.getListOfStudents();
      if (listOfStudents != null) {
        listOfStudents.stream()
                      .forEach(StudentDTO -> studentService.createStudent(StudentDTO));
      }
      Register save = registerRepositoryFaker.save(newRegister.toEntity());
      return save.toDto();
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Nie udalo sie utworzyc nowego dziennika");
    }
  }

  public RegisterDTO updateRegistersTeacher(UUID id, TeacherDTO teacher) {
    try {
      Register updatedRegister = registerRepositoryFaker.findById(id);
      updatedRegister.setTeacher(teacher.toEntity());
      return updatedRegister.toDto();
    } catch (ClassNotFoundException e) {
    }
    throw new BusinessLogicException("Bledne id");

  }

  public RegisterDTO addStudentToRegister(UUID id, List<StudentDTO> newStudent) {
    newStudent.forEach(studentDTO -> studentService.createStudent(studentDTO));
    List<StudentDTO> allStudents = studentService.getAllStudents();
    Register updatedRegister;
    try {
      updatedRegister = registerRepositoryFaker.findById(id);
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Bledne id");
    }
    updatedRegister.setListOfStudents(allStudents.stream()
                                                 .map(s -> s.toEntity())
                                                 .collect(Collectors.toList()));
    return updatedRegister.toDto();
  }

  public RegisterDTO removeStudentFromRegister(UUID registerId, List<UUID> studentId) {
    studentId.forEach(id -> studentService.deleteStudent(id));
    List<StudentDTO> allStudents = studentService.getAllStudents();
    Register updatedRegister;
    try {
      updatedRegister = registerRepositoryFaker.findById(registerId);
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Bledne id");
    }
    updatedRegister.setListOfStudents(allStudents.stream()
                                                 .map(s -> s.toEntity())
                                                 .collect(Collectors.toList()));
    return updatedRegister.toDto();
  }

  public void deleteById(UUID id) {
    registerRepositoryFaker.deleteById(id);
  }

  private RegisterDTO getRegisterByTeacherId(UUID teacherId) {
    RegisterDTO registerDto = registerRepositoryFaker.findyByTeacherId(teacherId)
                                                     .orElseThrow(() -> new BusinessLogicException("Bledne id"))
                                                     .toDto();
    return registerDto;
  }

  public List<StudentDTO> getStudentsByTeacherId(UUID teacherId) {
    RegisterDTO registerDto = getRegisterByTeacherId(teacherId);
    List<StudentDTO> list = registerDto.getListOfStudents();
    return list;
  }
}
