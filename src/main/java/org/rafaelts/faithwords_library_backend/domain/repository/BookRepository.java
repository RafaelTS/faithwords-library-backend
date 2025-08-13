package org.rafaelts.faithwords_library_backend.domain.repository;

import org.rafaelts.faithwords_library_backend.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Você pode adicionar métodos customizados aqui, se precisar, tipo:
    // List<Book> findByIsForRent(boolean isForRent);
}

