package com.example.school.note;

import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface NoteRepository extends JpaRepository<Note, Long> {

  Optional<Note> findByStudentId(Long studentId);
  Optional<Note> findByTeacherId(Long teacherId);
  Optional<List<Note>> findAllByTeacherId(Long id);
  Optional<List<Note>> findAllByStudentId(Long studentId);

}