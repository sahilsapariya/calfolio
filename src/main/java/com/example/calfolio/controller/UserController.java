package com.example.calfolio.controller;

import com.example.calfolio.dto.LoginDto;
import com.example.calfolio.dto.UserDto;
import com.example.calfolio.entity.User;
import com.example.calfolio.service.UserService;

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
