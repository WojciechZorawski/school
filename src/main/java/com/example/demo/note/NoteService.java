package com.example.demo.note;

import com.example.demo.student.Student;
import com.example.demo.student.StudentService;
import com.example.demo.teacher.Teacher;
import com.example.demo.teacher.TeacherService;
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
