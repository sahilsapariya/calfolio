package com.example.calfolio_backend.controller;

import com.example.calfolio_backend.dto.LoginDto;
import com.example.calfolio_backend.dto.UserDto;
import com.example.calfolio_backend.entity.User;
import com.example.calfolio_backend.service.UserService;

import lombok.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginDto loginDto) {
        User user = userService.loginUser(loginDto.getEmail(), loginDto.getPassword());
        return ResponseEntity.ok(user);
    }
}
