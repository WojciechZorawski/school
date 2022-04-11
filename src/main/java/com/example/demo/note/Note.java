package com.example.demo.note;

import com.example.demo.BaseEntity;
import com.example.demo.student.Student;
import com.example.demo.teacher.Teacher;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "note")
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Note extends BaseEntity {

  @CreatedDate
  private LocalDate date;
  @ManyToOne
  @JoinColumn(name = "student_id", referencedColumnName = "id")
  private Student student;
  @ManyToOne
  @JoinColumn(name = "teacher_id", referencedColumnName = "id")
  private Teacher teacher;
  private String description;

  NoteDTO toDto(){
    return NoteDTO.builder()
        .date(date)
        .description(description)
        .build();

  }

}
