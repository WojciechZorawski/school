package com.example.demo.teacher;

import com.example.demo.exception.BusinessLogicException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

  TeacherRepositoryFaker teacherRepository = new TeacherRepositoryFaker();

  public TeacherDTO getTeacherById(UUID id) {
    Teacher takenTeacher;
    try {
      takenTeacher = teacherRepository.findById(id);
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Bledne id");
    }
    return takenTeacher.toDto();
  }

  public List<TeacherDTO> getAllTeachers() {
    List<TeacherDTO> takenTeachers = teacherRepository.findAll().stream()
        .map(teacher -> teacher.toDto())
        .collect(Collectors.toList());
    return takenTeachers;
  }

  public TeacherDTO createTeacher(TeacherDTO newTeacher) {
    try {
      Teacher save = teacherRepository.save(newTeacher.toEntity());
      return save.toDto();
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Nie udalo sie utworzyc nowego nauczyciela");
    }
  }

  public TeacherDTO updateTeacher(UUID id, String lastName, int age) {
    Teacher updatedTeacher = null;
    try {
      updatedTeacher = teacherRepository.findById(id);
    } catch (ClassNotFoundException e) {
      throw new BusinessLogicException("Bledne id");
    }
    updatedTeacher.setLastName(lastName);
    updatedTeacher.setAge(age);
    return updatedTeacher.toDto();

  }

  public void deleteTeacher(UUID id){
    teacherRepository.deleteById(id);
  }

}
