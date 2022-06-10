package com.example.school.register;

import com.example.school.student.StudentResponseDTO;
import java.util.List;
import java.util.Map;
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
  public RegisterResponseDTO getRegisterById(@PathVariable Long id) {
    RegisterResponseDTO register = registerService.getRegisterById(id);
    return register;
  }

  @GetMapping("/list")
  public List<RegisterResponseDTO> getAllRegisters() {
    List<RegisterResponseDTO> list = registerService.getAllRegisters();
    return list;
  }

  @GetMapping("/teacher/{teacherId}")
  public List<StudentResponseDTO> getStudentsByTeacherId(@PathVariable Long teacherId) {
    List<StudentResponseDTO> list = registerService.getStudentsByTeacherId(teacherId);
    return list;
  }

  @GetMapping("/{registerId}/average")
  public Map<String, List<Map<String, Double>>> getStudentsFromRegisterWithAverage(@PathVariable Long registerId) {
    Map<String, List<Map<String, Double>>> studentsFromRegisterWithAverage = registerService.getStudentsFromRegisterWithAverage(registerId);
    return studentsFromRegisterWithAverage;
  }

  @PostMapping
  public RegisterResponseDTO createRegister(@RequestBody RegisterRequestDTO newRegister) {
    RegisterResponseDTO createdRegister = registerService.createRegister(newRegister);
    return createdRegister;
  }

  @PutMapping("/{registerId}/update")
  public RegisterResponseDTO updateRegisterByTeacherId(@PathVariable Long registerId, @RequestParam Long teacherId) {
    RegisterResponseDTO updatedRegistersTeacher = registerService.updateRegisterByTeacherId(registerId, teacherId);
    return updatedRegistersTeacher;
  }

  @PutMapping("/{registerId}/addStudent")
  public RegisterResponseDTO addStudentListToRegisterByStudentIds(@PathVariable Long registerId, @RequestParam List<Long> studentIds) {
    RegisterResponseDTO registerWithNewStudents = registerService.addStudentListToRegisterByStudentIds(registerId, studentIds);
    return registerWithNewStudents;
  }

  @DeleteMapping("/{id}")
  public void deleteRegister(@PathVariable Long id) {
    registerService.deleteById(id);
  }
}
