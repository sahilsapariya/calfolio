package com.example.calfolio.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1, nullable = false, name = "initial_letter")
    @JsonProperty("initial_letter")
    @jakarta.validation.constraints.Pattern(
        regexp = "^[A-Z]$",
        message = "Initial letter must be a single uppercase letter (A–Z)"
    )
    private String initialLetter;

    @Column(nullable = false, unique = true)
    @JsonProperty("name")
    private String name;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String description;
}
