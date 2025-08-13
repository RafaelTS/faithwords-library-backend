package org.rafaelts.faithwords_library_backend.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String medium;

    @Column(name = "quantity")
    private int quantity; // número total de exemplares

    public boolean isAvailable() {
        return quantity > 0;
    }
}
