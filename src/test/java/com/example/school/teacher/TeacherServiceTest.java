package com.example.school.teacher;

import static com.example.school.EntityToDtoComparatorHelper.compareTeacherEntityToTeacherResponseDto;
import static com.example.school.teacher.TeacherFactoryFaker.getValidTeacherEntity;
import static com.example.school.teacher.TeacherFactoryFaker.toDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {

  @InjectMocks
  private TeacherService teacherService;
  @Mock
  private TeacherRepository teacherRepository;

  @Test
  void getTeacherByIdReturnsProperTeacher() {
    Teacher entity = getValidTeacherEntity();
    Long id = entity.getId();

    when(teacherRepository.findById(id)).thenReturn(Optional.of(entity));

    TeacherDTO dto = teacherService.getTeacherById(id);
    compareTeacherEntityToTeacherResponseDto(entity, dto);
  }

  @Test
  void getAllTeachersReturnsProperTeacherList() {
    List<Teacher> entityList = List.of(getValidTeacherEntity());

    when(teacherRepository.findAll()).thenReturn(entityList);

    List<TeacherDTO> dtoList = teacherService.getAllTeachers();
    assertEquals(1, dtoList.size());
    compareTeacherEntityToTeacherResponseDto(entityList.get(0), dtoList.get(0));
  }

  @Test
  void createTeacherReturnsProperTeacher() {
    Teacher newTeacher = getValidTeacherEntity();

    when(teacherRepository.save(any(Teacher.class))).thenReturn(newTeacher);

    TeacherDTO createdTeacher = teacherService.createTeacher(toDto(newTeacher));
    compareTeacherEntityToTeacherResponseDto(newTeacher, createdTeacher);
  }

  @Test
  void createTeacherEntityReturnsProperTeacher() {
    Teacher newTeacher = getValidTeacherEntity();

    when(teacherRepository.save(any(Teacher.class))).thenReturn(newTeacher);

    Teacher createdTeacher = teacherService.createTeacherEntity(newTeacher);
    compareTeacherEntityToTeacherResponseDto(newTeacher, toDto(createdTeacher));
  }

  @Test
  void updateTeacherReturnsProperTeacher() {
    Teacher teacherEntity = getValidTeacherEntity();
    Long id = teacherEntity.getId();
    String lastName = "Zagrozny";
    int age = 59;

    when(teacherRepository.findById(id)).thenReturn(Optional.of(teacherEntity));
    when(teacherRepository.save(any(Teacher.class))).thenReturn(teacherEntity);

    TeacherDTO updatedTeacher = teacherService.updateTeacher(id, lastName, age);
    assertEquals(teacherEntity.getName(), updatedTeacher.getName());
    assertEquals(lastName, updatedTeacher.getLastName());
    assertEquals(age, updatedTeacher.getAge());
    assertEquals(teacherEntity.getProfession(), updatedTeacher.getProfession());
  }

  @Test
  void deleteTeacher() {
    Teacher teacherEntity = getValidTeacherEntity();
    Long id = teacherEntity.getId();
    teacherService.deleteTeacher(id);
    verify(teacherRepository, times(1)).deleteById(id);
  }

  @Test
  void findTeacherByIdReturnsProperTeacher() {
    Teacher teacherEntity = getValidTeacherEntity();
    Long id = teacherEntity.getId();

    when(teacherRepository.findById(id)).thenReturn(Optional.of(teacherEntity));

    Teacher teacher = teacherService.findTeacherById(id);
    compareTeacherEntityToTeacherResponseDto(teacherEntity, toDto(teacher));
  }
}
