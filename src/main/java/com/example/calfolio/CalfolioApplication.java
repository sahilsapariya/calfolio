package com.example.calfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CalfolioApplication {

	@GetMapping("/check")
    public String check() {
        return "Calfolio Backend is running!";
    }

	public static void main(String[] args) {
		SpringApplication.run(CalfolioApplication.class, args);
	}

}
