package com.example.medicalmanagement.dto;

import java.time.LocalDateTime;

public class MedicalRecordRequest {

    private Integer patientId;       // 患者ID
    private String diagnosis;        // 诊断信息
    private String treatmentPlan;    // 治疗计划
    private LocalDateTime createdAt; // 创建时间

    // Getters and setters
    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
