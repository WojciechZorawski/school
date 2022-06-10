package com.example.school.note;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students/{studentId}/notes")
@RequiredArgsConstructor
public class NoteController {

  private final NoteService noteService;

  @PostMapping("/create")
  public NoteDTO createNote(@RequestBody NoteDTO note,@PathVariable Long studentId,@RequestParam Long teacherId){
    return noteService.createNote(note, studentId, teacherId);
  }


}
