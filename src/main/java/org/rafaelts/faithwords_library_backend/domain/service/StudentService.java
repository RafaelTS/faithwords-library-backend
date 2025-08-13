package org.rafaelts.faithwords_library_backend.domain.service;

import java.util.List;
import java.util.Optional;

import org.rafaelts.faithwords_library_backend.domain.model.Student;
import org.rafaelts.faithwords_library_backend.domain.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }
}
