package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.entity.MedicalRecord;
import com.example.medicalmanagement.service.MedicalRecordService;
import com.example.medicalmanagement.dto.MedicalRecordRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    // 构造函数注入
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createRecord(@RequestBody MedicalRecordRequest request) {
        if (medicalRecordService.createRecord(request)) {
            return ResponseEntity.ok("Medical record created successfully");
        }
        return ResponseEntity.badRequest().body("Failed to create medical record");
    }

    @GetMapping("/view/{patientId}")
    public ResponseEntity<List<MedicalRecord>> viewRecords(@PathVariable Integer patientId) {
        List<MedicalRecord> records = medicalRecordService.getRecordsByPatient(patientId);
        return ResponseEntity.ok(records);
    }
}
