package com.example.school.note;

import com.example.school.exception.BusinessLogicException;
import com.example.school.student.Student;
import com.example.school.student.StudentService;
import com.example.school.teacher.Teacher;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {

  private final NoteRepository noteRepository;
  private final StudentService studentService;

  NoteDTO getNoteById(Long noteId) {
    NoteDTO takenNote = findNoteById(noteId).toDto();
    return takenNote;
  }

  Map<String, NoteDTO> getNoteWithTeacherByStudentId(Long studentId) {
    Map<String, NoteDTO> noteWithTeacher = new HashMap<>();
    List<Note> takenNotes = noteRepository.findAllByStudentId(studentId)
                                   .orElseThrow(() -> new BusinessLogicException("Bledne id"));
    takenNotes.forEach(note -> {
      String fullName = studentService.createFullName(note.getTeacher().getName(), note.getTeacher().getLastName());
      noteWithTeacher.put(fullName, note.toDto());
    });
    return noteWithTeacher;
  }

  Map<String, NoteDTO> getNoteWithStudentByTeacherId() {
    Map<String, NoteDTO> noteWithStudent = new HashMap<>();
    Teacher teacher = getLoggedUser();
    List<Note> takenNote = noteRepository.findAllByTeacherId(teacher.getId())
                                         .orElseThrow(() -> new BusinessLogicException("Bledne id"));
    takenNote.forEach(note -> {
      String fullName = studentService.createFullName(note.getStudent().getName(), note.getStudent().getLastName());
      noteWithStudent.put(fullName, note.toDto());
    });
    return noteWithStudent;
  }

  NoteDTO createNote(NoteDTO note, Long studentId) {
    Student student = studentService.findStudentById(studentId);
    Teacher teacher = getLoggedUser();
    Note noteEntity = note.toEntity(student, teacher);
    NoteDTO savedNote = noteRepository.save(noteEntity).toDto();
    return savedNote;
  }

  NoteDTO updateNote(Long id, NoteDTO newNote) {
    Note updatedNote = findNoteById(id);
    LocalDate date = newNote.getDate();
    String description = newNote.getDescription();
    if (date != null) {
      updatedNote.setDate(date);
    }
    if (description != null) {
      updatedNote.setDescription(description);
    }
    return noteRepository.save(updatedNote).toDto();
  }

  public void deleteNote(Long id) {
    noteRepository.deleteById(id);
  }

  private Note findNoteById(Long id) {
    return noteRepository.findById(id)
                         .orElseThrow(() -> new BusinessLogicException("Bledne id"));
  }

  private Teacher getLoggedUser() {
    Teacher loggedTeacher = Teacher.builder()
                                   .id(1L)
                                   .name("Jan")
                                   .lastName("Kowalski")
                                   .age(45)
                                   .profession("WOS")
                                   .build();
    return loggedTeacher;
  }
}
