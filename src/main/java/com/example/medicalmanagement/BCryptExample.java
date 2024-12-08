package com.example.medicalmanagement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptExample {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "123";  // 明文密码
        String encodedPassword = passwordEncoder.encode(rawPassword);  // 加密后的密码
        System.out.println("Encoded password for '123': " + encodedPassword);
    }
}
