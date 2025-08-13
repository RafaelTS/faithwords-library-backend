package org.rafaelts.faithwords_library_backend.domain.repository;

import org.rafaelts.faithwords_library_backend.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // Aqui também pode incluir buscas personalizadas se precisar
}
