package com.example.calfolio.entity;

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

    @Lob
    @Column(name = "content", nullable = false, columnDefinition = "LONGTEXT")
    @JsonProperty("content")
    private String content;

    @JsonProperty("author_id")
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(name = "status")
    @JsonProperty("status")
    private String status;

    @Lob
    @Column(name = "photo_base64", columnDefinition = "LONGBLOB")
    @JsonProperty("photo")
    private String photoBase64;
}
