package com.example.medicalmanagement.repository;

import com.example.medicalmanagement.entity.MedicalRecord;
import com.example.medicalmanagement.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer> {

    // 根据患者ID查找病历记录
    List<MedicalRecord> findByPatientId(Integer patientId);

    List<MedicalRecord> findByAppointment_PatientId(Integer userId);
}
