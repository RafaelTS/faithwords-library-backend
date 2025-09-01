package org.rafaelts.faithwords_library_backend.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String medium;

    @Column(name = "forrent", nullable = false)
    @JsonProperty("forRent")
    private boolean forRent;

    @Column(name = "quantity")
    private int quantity;

    // Conveniência
    public boolean isAvailable() {
        return quantity > 0;
    }

    // Builder manual
    public static BookBuilder builder() {
        return new BookBuilder();
    }

    public static class BookBuilder {
        private Long id;
        private String title;
        private String author;
        private String medium;
        private boolean forRent;
        private int quantity;

        public BookBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BookBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder medium(String medium) {
            this.medium = medium;
            return this;
        }

        public BookBuilder forRent(boolean forRent) {
            this.forRent = forRent;
            return this;
        }

        public BookBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Book build() {
            return new Book(id, title, author, medium, forRent, quantity);
        }
    }
}
