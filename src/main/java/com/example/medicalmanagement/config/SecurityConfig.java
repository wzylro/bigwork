package com.example.medicalmanagement.config;

import com.example.medicalmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserService userService;

    // 在构造函数中指定注入的 UserDetailsService
    public SecurityConfig(@Qualifier("userService") UserService userService) {
        this.userService = userService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/static/**", "/css/**", "/register", "/js/**", "/img/**", "/login", "/error")
                        .permitAll()  // 静态资源和公共页面
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/doctor/**").hasRole("DOCTOR")
                        .requestMatchers("/patient/**").hasRole("PATIENT")
                        .anyRequest().authenticated()  // 其他请求需要认证
                )
                .formLogin(form -> form
                        .loginPage("/login")  // 自定义登录页面
                        .permitAll()
                        .defaultSuccessUrl("/home", true)  // 登录成功默认跳转
                        .successHandler((request, response, authentication) -> {
                            // 登录成功后按角色跳转
                            String role = authentication.getAuthorities().toString();
                            if (role.contains("ADMIN")) {
                                response.sendRedirect("/admin/admin_home");
                            } else if (role.contains("DOCTOR")) {
                                response.sendRedirect("/doctor/doctor_home");
                            } else {
                                response.sendRedirect("/home");  // 默认跳转
                            }
                        })
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                );
        return http.build();
    }
}
