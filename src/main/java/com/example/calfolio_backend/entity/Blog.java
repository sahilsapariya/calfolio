package com.example.calfolio_backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "blog")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    @JsonProperty("title")
    private String title;

    @Column(name = "content", nullable = false)
    @JsonProperty("content")
    private String content;

    @Column(name = "author_id", nullable = false)
    @JsonProperty("author_id")
    private Long authorId;

    @Column(name = "status")
    @JsonProperty("status")
    private String status;

    @Lob
    @Column(name = "photo_base64", columnDefinition = "TEXT")
    @JsonProperty("photo")
    private String photoBase64;
}
