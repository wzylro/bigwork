package com.example.medicalmanagement.service;

import com.example.medicalmanagement.entity.MedicalRecord;
import com.example.medicalmanagement.repository.MedicalRecordRepository;
import com.example.medicalmanagement.dto.MedicalRecordRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // 确保添加了@Service注解
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public boolean createRecord(MedicalRecordRequest request) {
        try {
            MedicalRecord record = new MedicalRecord();
            // 设置病历的各个字段
            record.setDiagnosis(request.getDiagnosis());
            record.setTreatmentPlan(request.getTreatmentPlan());

            // 保存到数据库
            medicalRecordRepository.save(record);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<MedicalRecord> getRecordsByPatient(Integer patientId) {
        return medicalRecordRepository.findByAppointment_PatientId(patientId);
    }
}
