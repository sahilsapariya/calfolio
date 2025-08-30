package com.example.calfolio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinComicDto {

    @NotBlank(message = "Name cannot be blank")
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("photo")
    private MultipartFile photo;

    @JsonProperty("status")
    private String status;

    @JsonProperty("author")
    private String author;

    @JsonProperty("source_data")
    private MultipartFile sourceData;

    // Keep these for backward compatibility if needed
    private String photoBase64;
    private String sourceDataString;
}
