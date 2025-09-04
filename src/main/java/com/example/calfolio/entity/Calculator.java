package com.example.calfolio.entity;

import java.util.Map;

import com.example.calfolio.utils.JsonConverter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "calculator")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calculator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "calc_name", nullable = false)
    @JsonProperty("calc_name")
    private String calcName;

    @Column(name = "calc_type")
    @JsonProperty("calc_type")
    private String calcType;

    @Column(columnDefinition = "json")
    @JsonProperty("calc_formula")
    @Convert(converter = JsonConverter.class)
    private Map<String, Object> calcFormula;    

    @Column(name = "status")
    @JsonProperty("status")
    private String status;

    @Column(name = "description", columnDefinition = "LONGTEXT", nullable = true)
    @JsonProperty("description")
    private String description;
}
