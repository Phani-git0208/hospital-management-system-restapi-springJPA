package com.example.hospital.management.system.Controller;

import com.example.hospital.management.system.Dto.DoctorDto;
import com.example.hospital.management.system.Entity.Doctor;
import com.example.hospital.management.system.Service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/profile")
    public Doctor saveProfile(@RequestBody DoctorDto dto, Principal principal) {
        return doctorService.saveDoctorProfile(dto, principal);
    }

    @GetMapping("/profile")
    public Doctor getProfile(Principal principal) {
        return doctorService.getDoctorProfile(principal);
    }
}

