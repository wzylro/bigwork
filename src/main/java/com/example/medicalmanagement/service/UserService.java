package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.UserUpdateRequest;
import com.example.medicalmanagement.entity.UserEntity;
import com.example.medicalmanagement.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 用户注册方法
    public boolean registerUser(String username, String password, String confirmPassword, String email, String phone) {
        if (!password.equals(confirmPassword)) {
            return false;  // 密码不匹配
        }

        if (userRepository.existsByUsername(username)) {
            return false;  // 用户名已存在
        }

        String encodedPassword = passwordEncoder.encode(password);
        UserEntity newUser = new UserEntity();
        newUser.setUsername(username);
        newUser.setPassword(encodedPassword);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setRole(UserEntity.Role.PATIENT);  // 默认角色为 PATIENT

        userRepository.save(newUser);
        return true;  // 注册成功
    }

    // 实现 UserDetailsService 的方法
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("用户未找到: " + username));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())  // 添加用户角色
                .build();
    }
    public UserEntity updateUser(UserUpdateRequest request) {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        return userRepository.save(user);
    }
}
