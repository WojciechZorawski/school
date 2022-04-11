package com.example.demo.note;

import com.example.demo.student.Student;
import com.example.demo.teacher.Teacher;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {

  private LocalDate date;
  private String description;

  public Note toEntity(Student student, Teacher teacher) {
    return Note.builder()
               .date(date)
               .student(student)
               .teacher(teacher)
               .description(description)
               .build();

  }

}

