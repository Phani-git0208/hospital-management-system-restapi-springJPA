package com.example.hospital.management.system.Controller;

import com.example.hospital.management.system.Dto.PatientDto;
import com.example.hospital.management.system.Service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
@CrossOrigin
public class PatientController {

    private final PatientService patientService;

    // ✅ GET logged-in patient's profile
    @GetMapping("/me")
    public PatientDto getMyProfile(Authentication authentication) {
        String email = authentication.getName();
        return patientService.getMyProfile(email);
    }

    // ✅ PUT update logged-in patient's profile
    @PutMapping("/me")
    public PatientDto updateMyProfile(Authentication authentication,
                                      @RequestBody PatientDto dto) {
        String email = authentication.getName();
        return patientService.updateMyProfile(email, dto);
    }
}


