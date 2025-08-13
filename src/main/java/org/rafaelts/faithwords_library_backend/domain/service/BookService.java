package org.rafaelts.faithwords_library_backend.domain.service;

import java.util.List;
import java.util.Optional;

import org.rafaelts.faithwords_library_backend.domain.model.Book;
import org.rafaelts.faithwords_library_backend.domain.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> findAvailableBooks() {
        return bookRepository.findByQuantityGreaterThan(0);
    }

    public List<Book> findByIsForRent(boolean isForRent) {
        return bookRepository.findByIsForRent(isForRent);
    }
}
