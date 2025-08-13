package org.rafaelts.faithwords_library_backend.domain.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.rafaelts.faithwords_library_backend.domain.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class BookRepositoryIT {

    @Autowired
    private BookRepository repo;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("findByIsForRent(true) retorna apenas livros para aluguel")
    void shouldFindOnlyRentals() {
        em.persist(Book.builder().title("O Livro dos Espíritos").author("Allan Kardec")
                           .medium("—").quantity(3).isForRent(true).build());
        em.persist(Book.builder().title("Evangelho Segundo o Espiritismo").author("Allan Kardec")
                           .medium("—").quantity(2).isForRent(false).build());
        em.flush();

        List<Book> rentals = repo.findByIsForRent(true);
        assertThat(rentals).extracting(Book::isForRent).containsOnly(true);
    }

    @Test
    @DisplayName("findByTitleContainingIgnoreCase funciona")
    void searchByTitle() {
        em.persist(Book.builder().title("Nosso Lar").author("André Luiz").medium("Chico Xavier")
                           .quantity(1).isForRent(true).build());
        em.flush();

        List<Book> out = repo.findByTitleContainingIgnoreCase("nosso");
        assertThat(out).hasSize(1);
    }
}
