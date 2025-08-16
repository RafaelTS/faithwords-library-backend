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

    public List<Book> findByIsForRent(Boolean forRent) {
        return bookRepository.findByIsForRent(forRent);
    }

    public Book save(Book newBook) {
        return bookRepository.save(newBook);
    }

    public Optional<Book> update(Long id, Book bookToUpdate) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(bookToUpdate.getTitle());
                    existingBook.setAuthor(bookToUpdate.getAuthor());
                    existingBook.setForRent(bookToUpdate.getForRent());
                    return bookRepository.save(existingBook);
                });
    }

    public boolean deleteById(Long id) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    bookRepository.delete(existingBook);
                    return true;
                })
                .orElse(false);
    }
}
