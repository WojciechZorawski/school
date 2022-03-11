package com.example.demo.teacher;

import com.example.demo.exception.BusinessLogicException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class TeacherService {

  TeacherRepositoryFaker teacherRepository = new TeacherRepositoryFaker();

  TeacherDTO getTeacherById(UUID id) {
    try {
      Teacher takenTeacher = teacherRepository.findById(id);
      return takenTeacher.toDto();
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Bledne id");
    }
  }

  List<TeacherDTO> getAllTeachers() {
    List<TeacherDTO> takenTeachers = teacherRepository.findAll().stream()
                                                      .map(teacher -> teacher.toDto())
                                                      .collect(Collectors.toList());
    return takenTeachers;
  }

  TeacherDTO createTeacher(TeacherDTO newTeacher) {
    try {
      Teacher save = teacherRepository.save(newTeacher.toEntity());
      return save.toDto();
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Nie udalo sie utworzyc nowego nauczyciela");
    }
  }

  TeacherDTO updateTeacher(UUID id, String lastName, int age) {
    try {
      Teacher updatedTeacher = teacherRepository.findById(id);
      updatedTeacher.setLastName(lastName);
      updatedTeacher.setAge(age);
      return updatedTeacher.toDto();
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Bledne id");
    }
  }

  void deleteTeacher(UUID id) {
    teacherRepository.deleteById(id);
  }

}
