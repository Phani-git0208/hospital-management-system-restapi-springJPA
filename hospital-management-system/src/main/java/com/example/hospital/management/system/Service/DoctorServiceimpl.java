package com.example.hospital.management.system.Service;

import com.example.hospital.management.system.Dto.DoctorDto;
import com.example.hospital.management.system.Entity.Doctor;
import com.example.hospital.management.system.Entity.User;
import com.example.hospital.management.system.repository.DoctorRepository;
import com.example.hospital.management.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class DoctorServiceimpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    public Doctor saveDoctorProfile(DoctorDto dto, Principal principal) {

        User user = userRepository
                .findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Doctor doctor = new Doctor();
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setPhone(dto.getPhone());
        doctor.setExperienceYears(dto.getExperienceYears());
        doctor.setUser(user);

        return doctorRepository.save(doctor);
    }

    public Doctor getDoctorProfile(Principal principal) {

        User user = userRepository
                .findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return doctorRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Doctor profile not found"));
    }
}






