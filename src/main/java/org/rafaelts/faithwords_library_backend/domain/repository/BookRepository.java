package org.rafaelts.faithwords_library_backend.domain.repository;

import org.rafaelts.faithwords_library_backend.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findById(Long id);

    List<Book> findAll();

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByQuantityGreaterThan(int quantity);

    List<Book> findByForRent(Boolean forRent);

}

