package com.example.calfolio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinTermDto {
    private Long id;

    @JsonProperty("initial_letter")
    private String initialLetter;
    private String name;
    private String description;
}
