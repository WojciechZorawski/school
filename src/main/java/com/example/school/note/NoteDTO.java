package com.example.school.note;

import com.example.school.student.Student;
import com.example.school.teacher.Teacher;
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

