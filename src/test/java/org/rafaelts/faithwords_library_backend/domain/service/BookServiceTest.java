package org.rafaelts.faithwords_library_backend.domain.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.rafaelts.faithwords_library_backend.domain.model.Book;
import org.rafaelts.faithwords_library_backend.domain.repository.BookRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock BookRepository repo;
    @InjectMocks
    BookService service;

    @Test
    void findByIsForRent_delegatesToRepo() {
        when(repo.findByForRent(true)).thenReturn(
                List.of(Book.builder().title("Teste").forRent(true).quantity(1).build())
        );

        var result = service.findByIsForRent(true);
        assertEquals(1, result.size());
        verify(repo).findByForRent(true);
    }
}