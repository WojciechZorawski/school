package com.example.school.teacher;

import com.example.school.exception.BusinessLogicException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

  private final TeacherRepository teacherRepository;

  TeacherDTO getTeacherById(Long id) {
    Teacher takenTeacher = findTeacherById(id);
    return takenTeacher.toDto();

  }

  List<TeacherDTO> getAllTeachers() {
    List<TeacherDTO> takenTeachers = teacherRepository.findAll().stream()
                                                      .map(teacher -> teacher.toDto())
                                                      .collect(Collectors.toList());
    return takenTeachers;
  }

  TeacherDTO createTeacher(TeacherDTO newTeacher) {
    Teacher save = createTeacherEntity(newTeacher.toEntity());
    return save.toDto();
  }

  public Teacher createTeacherEntity(Teacher teacherEntity) {
    Teacher save = teacherRepository.save(teacherEntity);
    return save;
  }

  TeacherDTO updateTeacher(Long id, String lastName, int age) {
    Teacher updatedTeacher = findTeacherById(id);
    updatedTeacher.setLastName(lastName);
    updatedTeacher.setAge(age);
    return teacherRepository.save(updatedTeacher).toDto();
  }

  void deleteTeacher(Long id) {
    teacherRepository.deleteById(id);
  }

  public Teacher findTeacherById(Long id) {
    return teacherRepository.findById(id)
                            .orElseThrow(() -> new BusinessLogicException("Bledne id"));
  }
}
