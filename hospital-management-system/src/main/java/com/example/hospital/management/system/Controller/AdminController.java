package com.example.hospital.management.system.Controller;

import com.example.hospital.management.system.Dto.DoctorRequest;
import com.example.hospital.management.system.Entity.User;
import com.example.hospital.management.system.Entity.type.Role;
import com.example.hospital.management.system.Service.AdminService;
import com.example.hospital.management.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/assign-doctor/{userId}")
    public String promoteToDoctor(@PathVariable Long userId,
                                    @RequestBody DoctorRequest request) {

        adminService.assignDoctorRole(userId, request);
        return "User promoted to DOCTOR";
    }


}