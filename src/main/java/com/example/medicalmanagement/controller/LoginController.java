package com.example.medicalmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // 对应 templates/login.html
    }


    @GetMapping("/admin/admin_home")
    public String adminHome() {
        return "admin_home"; // 对应 templates/admin_home.html
    }

    @GetMapping("/doctor/doctor_home")
    public String doctorHome() {
        return "doctor_home"; // 对应 templates/doctor_home.html
    }

    @GetMapping("/home")
    public String defaultHome() {
        return "home"; // 对应 templates/home.html
    }
}

