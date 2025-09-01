package org.rafaelts.faithwords_library_backend.exception;

import org.rafaelts.faithwords_library_backend.exception.book.BookWithoutTitleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookWithoutTitleException.class)
    public ResponseEntity<String> handleBookWithoutTitle(BookWithoutTitleException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
