package com.example.medicalmanagement.service;

import com.example.medicalmanagement.entity.AppointmentEntity;
import com.example.medicalmanagement.entity.UserEntity;
import com.example.medicalmanagement.repository.AppointmentRepository;
import com.example.medicalmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;

    public PatientService(UserRepository userRepository, AppointmentRepository appointmentRepository) {
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public UserEntity getPatientProfile(String username) {
        // 通过用户名获取患者信息
        UserEntity patient = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 查询患者的挂号记录
        List<AppointmentEntity> appointments = appointmentRepository.findByPatient(patient);

        // 将挂号记录设置到患者信息中
        patient.setAppointments(appointments);

        return patient;
    }

    public List<AppointmentEntity> getAllAppointmentsWithMedicalRecords() {
        return appointmentRepository.findAllWithMedicalRecords();
    }
}
