package com.example.hospital.management.system.Controller;

import com.example.hospital.management.system.Entity.Doctor;
import com.example.hospital.management.system.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/doctors")
@RequiredArgsConstructor
@CrossOrigin
public class DoctorController {

    private final DoctorRepository doctorRepository;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}

