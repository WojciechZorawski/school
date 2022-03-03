package com.example.demo.teacher;

import static com.example.demo.teacher.TeacherFactoryFaker.getValidTeacherEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {

  @InjectMocks
  private TeacherService teacherService;
  @Mock
  private TeacherRepositoryFaker teacherRepositoryFaker;

  @Test
  void getTeacherByIdReturnsProperTeacher() throws ClassNotFoundException {
    Teacher entity = getValidTeacherEntity();
    UUID id = entity.getId();

    when(teacherRepositoryFaker.findById(id)).thenReturn(entity);

    TeacherDTO dto = teacherService.getTeacherById(id);
    assertEquals(entity.getName(), dto.getName());
    assertEquals(entity.getLastName(), dto.getLastName());
    assertEquals(entity.getAge(), dto.getAge());
    assertEquals(entity.getProfession(), dto.getProfession());
  }

  @Test
  void getAllTeachersReturnsProperTeacherList() {
    List<Teacher> entityList = List.of(getValidTeacherEntity());

    when(teacherRepositoryFaker.findAll()).thenReturn(entityList);

    List<TeacherDTO> dtoList = teacherService.getAllTeachers();
    assertEquals(1, dtoList.size());
    assertEquals(entityList.get(0).getName(), dtoList.get(0).getName());
    assertEquals(entityList.get(0).getLastName(), dtoList.get(0).getLastName());
    assertEquals(entityList.get(0).getAge(), dtoList.get(0).getAge());
    assertEquals(entityList.get(0).getProfession(), dtoList.get(0).getProfession());
  }

  @Test
  void createTeacherReturnsProperTeacher() throws ClassNotFoundException {
    Teacher newTeacher = getValidTeacherEntity();

    when(teacherRepositoryFaker.save(any(Teacher.class))).thenReturn(newTeacher);

    TeacherDTO createdTeacher = teacherService.createTeacher(newTeacher.toDto());
    assertEquals(newTeacher.getName(), createdTeacher.getName());
    assertEquals(newTeacher.getLastName(), createdTeacher.getLastName());
    assertEquals(newTeacher.getAge(), createdTeacher.getAge());
    assertEquals(newTeacher.getProfession(), createdTeacher.getProfession());
  }

  @Test
  void updateTeacherReturnsProperTeacher() throws ClassNotFoundException {
    Teacher teacherEntity = getValidTeacherEntity();
    UUID id = teacherEntity.getId();
    String lastName = "Zagrozny";
    int age = 59;

    when(teacherRepositoryFaker.findById(id)).thenReturn(teacherEntity);

    TeacherDTO updatedTeacher = teacherService.updateTeacher(id, lastName, age);
    assertEquals(teacherEntity.getName(), updatedTeacher.getName());
    assertEquals(lastName, updatedTeacher.getLastName());
    assertEquals(age, updatedTeacher.getAge());
    assertEquals(teacherEntity.getProfession(), updatedTeacher.getProfession());
  }

}
