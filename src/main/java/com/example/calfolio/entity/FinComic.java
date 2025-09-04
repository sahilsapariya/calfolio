package com.example.calfolio.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fin_comic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinComic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Name cannot be blank")
    @JsonProperty("name")
    private String name;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    @JsonProperty("description")
    private String description;

    @Lob
    @Column(name = "photo_base64", columnDefinition = "LONGBLOB", nullable = true)
    @JsonProperty("photo")
    private String photoBase64;

    @Column(name = "status", nullable = true)
    @JsonProperty("status")
    private String status;

    @Column(name = "author", nullable = true)
    @JsonProperty("author")
    private String author;

    @Column(name = "source_data", nullable = true, columnDefinition = "LONGBLOB")
    @JsonProperty("source_data")
    private String sourceData;
}
