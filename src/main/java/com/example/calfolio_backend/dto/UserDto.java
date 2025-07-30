package com.example.calfolio_backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String username;
    private String email;
    private String password;
}
