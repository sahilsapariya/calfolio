package com.example.calfolio.controller;

import com.example.calfolio.service.OtpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    private final OtpService otpService;

    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/send")
    public ResponseEntity<Map<String, Object>> sendOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "message", "Email is required"
            ));
        }

        otpService.generateAndSendOtp(email);
        return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "OTP sent to " + email
        ));
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifyOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");

        if (email == null || otp == null) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "message", "Email and OTP are required"
            ));
        }

        boolean valid = otpService.verifyOtp(email, otp);

        if (valid) {
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "OTP verified successfully"
            ));
        } else {
            return ResponseEntity.status(400).body(Map.of(
                "success", false,
                "message", "Invalid or expired OTP"
            ));
        }
    }
}
