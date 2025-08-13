package org.rafaelts.faithwords_library_backend.web.controller;

import org.rafaelts.faithwords_library_backend.domain.model.Book;
import org.rafaelts.faithwords_library_backend.domain.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Book>> searchByTitle(@RequestParam(required = false) String title,
            @RequestParam(required = false) Boolean isForRent) {
        List<Book> books;

        if (title != null) {
            books = bookService.findByTitle(title);
        } else if (isForRent != null) {
            books = bookService.findByIsForRent(isForRent);
        } else {
            books = bookService.findAvailableBooks();
        }

        return ResponseEntity.ok(books);
    }
}
