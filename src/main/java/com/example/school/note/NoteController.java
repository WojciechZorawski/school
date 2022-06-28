package com.example.school.note;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NoteController {

  private final NoteService noteService;

  @GetMapping("/notes/{id}")
  public NoteDTO getNoteById(@PathVariable Long id) {
    NoteDTO note = noteService.getNoteById(id);
    return note;
  }

  @GetMapping("/students/{studentId}/notes")
  public Map<String, NoteDTO> getNoteWithTeacherByStudentId(@PathVariable Long studentId) {
    Map<String, NoteDTO> noteWithTeacher = noteService.getNoteWithTeacherByStudentId(studentId);
    return noteWithTeacher;
  }

  @GetMapping("/notes/list")
  public Map<String, NoteDTO> getNoteWithStudentByTeacherId() {
    Map<String, NoteDTO> noteWithStudentByTeacherId = noteService.getNoteWithStudentByTeacherId();
    return noteWithStudentByTeacherId;
  }

  @PostMapping("/students/{studentId}/create")
  public NoteDTO createNoteToStudent(@RequestBody NoteDTO note, @PathVariable Long studentId) {
    NoteDTO createdNote = noteService.createNote(note, studentId);
    return createdNote;
  }

  @PutMapping("/students/{studentId}/notes/{noteId}/update")
  public NoteDTO updateNote(@PathVariable Long noteId, @RequestBody NoteDTO note) {
    NoteDTO updatedNote = noteService.updateNote(noteId, note);
    return updatedNote;
  }

  @DeleteMapping("/students/{studentId}/notes/{id}/delete")
  public void deleteNote(@PathVariable Long id) {
    noteService.deleteNote(id);
  }
}
