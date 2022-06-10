package com.example.school.note;

import com.example.school.exception.BusinessLogicException;
import com.example.school.student.Student;
import com.example.school.student.StudentService;
import com.example.school.teacher.Teacher;
import com.example.school.teacher.TeacherService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoteService {

  private final NoteRepository noteRepository;
  private final StudentService studentService;
  private final TeacherService teacherService;

  NoteDTO createNote(NoteDTO note, Long studentId, Long teacherId){
    Student student =  studentService.findStudentById(studentId);
    Teacher teacher = teacherService.findTeacherById(teacherId);
    Note noteEntity = note.toEntity(student, teacher);
    NoteDTO savedNote = noteRepository.save(noteEntity).toDto();
    return savedNote;
  }

  public Note getNoteById(Long noteId){
    Note note = noteRepository.getById(noteId);
    return note;
  }
}
