package org.rafaelts.faithwords_library_backend.web.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.rafaelts.faithwords_library_backend.domain.model.Book;
import org.rafaelts.faithwords_library_backend.domain.service.BookService;
import org.rafaelts.faithwords_library_backend.exception.book.BookWithoutTitleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(BookController.class)
@WithMockUser(username = "test", roles = {"USER"})
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService service;

    @Test
    void getById_returns200() throws Exception {
        Mockito.when(service.findById(1L)).thenReturn(Optional.of(
                Book.builder().id(1L).title("Nosso Lar").quantity(1).forRent(true).build()
        ));

        mvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Nosso Lar"))
                .andExpect(jsonPath("$.forRent").value(true));
    }

    @Test
    void shouldReturnBadRequestWhenSavingBookWithoutTitle() throws Exception {
        String bookJson = """
        {
            "author": "Autor de Teste"
        }
        """;

        Mockito.when(service.save(Mockito.any(Book.class)))
                .thenThrow(new BookWithoutTitleException());

        mvc.perform(post("/books")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bookJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Não é possível incluir um livro sem título."));
    }


    @Test
    @Disabled
    void searchByTitle_returnsBooks() throws Exception {
        Mockito.when(service.findByTitle("lar")).thenReturn(
                List.of(Book.builder().title("Nosso Lar").forRent(false).quantity(2).build())
        );

        mvc.perform(get("/books?title=lar"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Nosso Lar"))
                .andExpect(jsonPath("$[0].forRent").value(false));
    }

    @Test
    @Disabled
    void searchByForRent_returnsBooks() throws Exception {
        Mockito.when(service.findByIsForRent(true)).thenReturn(
                List.of(Book.builder()
                                .title("Aluguel")
                                .forRent(true)
                                .quantity(1)
                                .build())
        );

        mvc.perform(get("/books?forRent=true"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Aluguel"))
                .andExpect(jsonPath("$[0].forRent").value(true));
    }
}
