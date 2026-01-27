package com.example.hospital.management.system.Controller;

import com.example.hospital.management.system.Dto.DoctorDto;
import com.example.hospital.management.system.Entity.Doctor;
import com.example.hospital.management.system.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // ✅ Admin can view all doctors
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/doctors")
    public List<DoctorDto> getAllDoctors() {
        return adminService.getAllDoctors();
    }
}
