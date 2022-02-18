package com.example.demo.register;

import com.example.demo.student.StudentRequestDTO;
import com.example.demo.student.StudentResponseDTO;
import com.example.demo.teacher.TeacherDTO;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registers")
@RequiredArgsConstructor
public class RegisterController {

  private final RegisterService registerService;

  @GetMapping("/{id}")
  public RegisterResponseDTO getRegisterById(@PathVariable UUID id) {
    RegisterResponseDTO register = registerService.getRegisterById(id);
    return register;
  }

  @GetMapping("/list")
  public List<RegisterResponseDTO> getAllRegisters() {
    List<RegisterResponseDTO> list = registerService.getAllRegisters();
    return list;
  }

  @GetMapping("/teacher/{teacherId}")
  public List<StudentResponseDTO> getStudentsByTeacherId(@PathVariable UUID teacherId) {
    List<StudentResponseDTO> list = registerService.getStudentsByTeacherId(teacherId);
    return list;
  }

  @PostMapping
  public RegisterResponseDTO createRegister(@RequestBody RegisterRequestDTO newRegister) {
    RegisterResponseDTO createdRegister = registerService.createRegister(newRegister);
    return createdRegister;
  }

  @PutMapping("/update/{registerId}")
  public RegisterResponseDTO updateRegistersTeacher(@PathVariable UUID registerId, @RequestBody TeacherDTO newTeacher) {
    RegisterResponseDTO updatedRegistersTeacher = registerService.updateRegistersTeacher(registerId, newTeacher);
    return updatedRegistersTeacher;
  }

  @PutMapping("/add/{registerId}")
  public RegisterResponseDTO addStudentToRegister(@PathVariable UUID registerId, @RequestBody List<StudentRequestDTO> students) {
    RegisterResponseDTO registerWithNewStudents = registerService.addStudentToRegister(registerId, students);
    return registerWithNewStudents;
  }

  @PutMapping("/remove/{registerId}")
  public RegisterResponseDTO removeStudentFromRegister(@PathVariable UUID registerId, @RequestBody List<UUID> studentId) {
    RegisterResponseDTO registerWithRemovedStudents = registerService.removeStudentFromRegister(registerId, studentId);
    return registerWithRemovedStudents;
  }

  @DeleteMapping("/{id}")
  public void deleteRegister(@PathVariable UUID id){
    registerService.deleteById(id);
  }
}
