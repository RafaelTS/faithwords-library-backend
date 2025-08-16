package org.rafaelts.faithwords_library_backend.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Column(name = "forrent", nullable = false)
    @JsonProperty("forRent")
    private Boolean forRent;

    @Column(name = "quantity")
    private int quantity;

    public boolean isAvailable() {
        return quantity > 0;
    }


}
