package com.example.medicalmanagement.controller;
import com.example.medicalmanagement.entity.AppointmentEntity;
import com.example.medicalmanagement.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private AppointmentService appointmentService;

    /**
     * 显示医生挂号记录（根据登录医生自动获取）
     */
    @GetMapping("/appointments")
    public String getAppointments(Model model) {
        // 获取当前登录的用户名
        String username = getCurrentUsername();
        // 通过用户名查询挂号记录
        List<AppointmentEntity> appointments = appointmentService.getAppointmentsByUsername(username);
        model.addAttribute("appointments", appointments);
        return "doctor_home"; // 对应的 Thymeleaf 模板
    }

    /**
     * 安排诊疗时间
     */
    @PostMapping("/appointments/schedule")
    public String scheduleAppointment(
            @RequestParam Integer appointmentId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime scheduledTime
    ) {
        appointmentService.scheduleAppointment(appointmentId, scheduledTime);
        return "redirect:/doctor/appointments";
    }

    /**
     * 获取当前登录用户名（与 Spring Security 集成）
     */
    private String getCurrentUsername() {
        // 从 SecurityContextHolder 中获取用户名
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
