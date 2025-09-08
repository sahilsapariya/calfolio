package com.example.calfolio.service;

import com.example.calfolio.entity.EmailOtp;
import com.example.calfolio.repository.EmailOtpRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OtpService {

    private final EmailOtpRepository otpRepository;
    private final JavaMailSender mailSender;


    public OtpService(EmailOtpRepository otpRepository, JavaMailSender mailSender) {
        this.otpRepository = otpRepository;
        this.mailSender = mailSender;
    }

    @Transactional
    public String generateAndSendOtp(String email) {
        String otp = String.format("%06d", new Random().nextInt(999999));
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(5);

        // Replace existing if exists
        otpRepository.deleteByEmail(email);

        EmailOtp emailOtp = EmailOtp.builder()
                .email(email)
                .otp(otp)
                .expiryTime(expiry)
                .build();

        otpRepository.save(emailOtp);

        sendOtpEmail(email, otp);

        return otp;
    }

    private void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dirgh.tech@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otp + "\nThis code will expire in 5 minutes.");
        mailSender.send(message);
    }
    

    public boolean verifyOtp(String email, String otp) {
        Optional<EmailOtp> emailOtp = otpRepository.findByEmail(email);

        if (emailOtp.isEmpty()) return false;

        EmailOtp storedOtp = emailOtp.get();

        if (storedOtp.getExpiryTime().isBefore(LocalDateTime.now())) {
            otpRepository.delete(storedOtp);
            return false; // expired
        }

        boolean isValid = storedOtp.getOtp().equals(otp);

        if (isValid) {
            otpRepository.delete(storedOtp); // OTP one-time use
        }

        return isValid;
    }
}
