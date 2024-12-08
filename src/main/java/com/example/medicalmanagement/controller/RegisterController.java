package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.entity.UserEntity;
import com.example.medicalmanagement.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    // Show register form
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; // Return the register page (register.html)
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String confirmPassword,
                               @RequestParam String email,
                               @RequestParam String phone) {

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            return "redirect:/register?error=Passwords do not match";  // Redirect if passwords don't match
        }

        // Register the user
        boolean isRegistered = userService.registerUser(username, password, confirmPassword, email, phone);
        if (isRegistered) {
            return "redirect:/login?register=success";  // Redirect to login page if registration is successful
        } else {
            return "redirect:/register?error=User already exists";  // Redirect if user already exists
        }
    }
}
