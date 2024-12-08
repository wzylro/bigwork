package com.example.medicalmanagement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class textmain {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String rawPassword = "123"; // 明文密码
        String encodedPassword = "$10$01Ev1YtMUJ24tSKBQeDeHuQG8CXKePCOtMU/bxCx7EzpaQkpFLRWq"; // 数据库存储的加密密码

        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
        System.out.println("Password matches: " + matches);
        String newEncodedPassword = passwordEncoder.encode("123456");
        System.out.println(newEncodedPassword);
    }
}

