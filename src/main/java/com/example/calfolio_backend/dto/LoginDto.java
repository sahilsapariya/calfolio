package com.example.calfolio_backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String email;
    private String password;
}
