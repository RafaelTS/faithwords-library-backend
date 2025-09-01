package org.rafaelts.faithwords_library_backend.exception.book;

public class BookWithoutTitleException extends RuntimeException{

    public BookWithoutTitleException() {
        super("Não é possível incluir um livro sem título.");
    }
}
