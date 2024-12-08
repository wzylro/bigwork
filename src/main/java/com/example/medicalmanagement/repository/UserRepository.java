package com.example.medicalmanagement.repository;

import com.example.medicalmanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    // 根据用户名查询用户
    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);  // 检查用户名是否已存在
}
