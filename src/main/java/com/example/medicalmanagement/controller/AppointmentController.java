package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.entity.AppointmentEntity;
import com.example.medicalmanagement.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    // 构造器注入
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    @GetMapping("/list")
    public ResponseEntity<List<AppointmentEntity>> listAppointments(@RequestParam String username) {
        List<AppointmentEntity> appointments = appointmentService.getAppointmentsByUser(username);
        return ResponseEntity.ok(appointments);
    }

    @PostMapping("/cancel")
    public String cancelAppointment(@RequestParam Integer appointmentId, RedirectAttributes redirectAttributes) {
        appointmentService.cancelAppointmentById(appointmentId);
        redirectAttributes.addFlashAttribute("message", "挂号已取消！");
        return "redirect:/user/health-record";
    }
}


