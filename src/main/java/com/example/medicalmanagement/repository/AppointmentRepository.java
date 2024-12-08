package com.example.medicalmanagement.repository;
import com.example.medicalmanagement.entity.AppointmentEntity;
import com.example.medicalmanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {
    List<AppointmentEntity> findByUsername(String username);

    List<AppointmentEntity> findByPatient(UserEntity user);

    List<AppointmentEntity> findAllWithMedicalRecords();
}

