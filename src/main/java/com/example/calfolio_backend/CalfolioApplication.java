package com.example.calfolio_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CalfolioApplication {

    @GetMapping("/hello")
    public String check() {
        return "Hello World";
    }

    public static void main(String[] args) {
        SpringApplication.run(CalfolioApplication.class, args);
    }
}