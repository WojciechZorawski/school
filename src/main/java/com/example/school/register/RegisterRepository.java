package com.example.school.register;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

interface RegisterRepository extends JpaRepository<Register, Long> {

  Optional<Register> findByTeacherId(Long teacherId);
}