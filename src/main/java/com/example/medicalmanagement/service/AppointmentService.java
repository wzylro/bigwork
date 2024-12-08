package com.example.medicalmanagement.service;

import com.example.medicalmanagement.entity.AppointmentEntity;
import com.example.medicalmanagement.repository.AppointmentRepository;
import com.example.medicalmanagement.entity.UserEntity;
import com.example.medicalmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
    }

    /**
     * 根据用户名获取该用户的所有挂号记录
     * @param username 用户名
     * @return 挂号记录列表
     */
    public List<AppointmentEntity> getAppointmentsByUser(String username) {
        // 查找用户实体，假设 AppointmentEntity 中有一个指向 UserEntity 的 patient 字段
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return appointmentRepository.findByPatient(user);  // 假设 AppointmentEntity 与 UserEntity 有关联
    }

    /**
     * 创建挂号
     * @param appointment 要创建的挂号记录
     * @return 是否创建成功
     */
    public boolean createAppointment(AppointmentEntity appointment) {
        // 如果需要，添加一些业务逻辑，比如检查该患者是否已挂号等
        try {
            appointmentRepository.save(appointment);
            return true;
        } catch (Exception e) {
            // 处理保存失败的情况
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据挂号ID取消挂号
     * @param appointmentId 挂号ID
     * @return 是否取消成功
     */
    public boolean cancelAppointmentById(Integer appointmentId) {
        Optional<AppointmentEntity> appointmentOptional = appointmentRepository.findById(appointmentId);

        if (appointmentOptional.isPresent()) {
            AppointmentEntity appointment = appointmentOptional.get();
            // 根据业务需求，添加取消挂号的操作
            appointmentRepository.delete(appointment);
            return true;
        } else {
            return false;  // 如果找不到挂号记录
        }
    }

    public List<AppointmentEntity> getAppointmentsByUsername(String username) {
        return new ArrayList<>();
    }

    public void scheduleAppointment(Integer appointmentId, LocalDateTime scheduledTime) {
    }
}

