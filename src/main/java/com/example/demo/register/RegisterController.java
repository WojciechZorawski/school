package com.example.demo.register;

import com.example.demo.student.StudentDTO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registers")
@RequiredArgsConstructor
public class RegisterController {

  private final RegisterService registerService;

  @GetMapping("/{id}")
  public RegisterDTO getRegisterById(@PathVariable UUID id) {
    RegisterDTO register = registerService.getRegisterById(id);
    return register;
  }

  @GetMapping("/list")
  public List<RegisterDTO> getAllRegisters() {
    List<RegisterDTO> list = registerService.getAllRegisters();
    return list;
  }

  @GetMapping("/teacher/{teacherId}")
  public List<StudentDTO> getStudentsByTeacherId(@PathVariable UUID teacherId) {
    List<StudentDTO> list = registerService.getStudentsByTeacherId(teacherId);
    return list;
  }

  @PostMapping
  public RegisterDTO createRegister(@RequestBody RegisterDTO newRegister) {
    RegisterDTO createdRegister = registerService.createRegister(newRegister);
    return createdRegister;
  }

  @PutMapping("/update/{registerId}")
  public RegisterDTO updateRegistersTeacher(@PathVariable UUID registerId, @RequestBody TeacherDTO newTeacher) {
    RegisterDTO updatedRegistersTeacher = registerService.updateRegistersTeacher(registerId, newTeacher);
    return updatedRegistersTeacher;
  }

  @PutMapping("/add/{registerId}")
  public RegisterDTO addStudentToRegister(@PathVariable UUID registerId, @RequestBody List<StudentDTO> students) {
    RegisterDTO registerWithNewStudents = registerService.addStudentToRegister(registerId, students);
    return registerWithNewStudents;
  }

  @PutMapping("/remove/{registerId}")
  public RegisterDTO removeStudentFromRegister(@PathVariable UUID registerId, @RequestBody List<UUID> studentId) {
    RegisterDTO registerWithRemovedStudents = registerService.removeStudentFromRegister(registerId, studentId);
    return registerWithRemovedStudents;
  }

  @DeleteMapping("/{id}")
  public void deleteRegister(@PathVariable UUID id){
    registerService.deleteById(id);
  }
}
