package com.example.calfolio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_otp")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailOtp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String otp;

    @Column(nullable = false)
    private LocalDateTime expiryTime;
}
