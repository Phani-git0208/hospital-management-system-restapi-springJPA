package com.example.hospital.management.system.Service;

import com.example.hospital.management.system.Dto.DoctorRequest;
import com.example.hospital.management.system.Entity.Doctor;
import com.example.hospital.management.system.Entity.User;
import com.example.hospital.management.system.Entity.type.Role;
import com.example.hospital.management.system.repository.DocterRepository;
import com.example.hospital.management.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceimp implements AdminService {

    private final UserRepository userRepository;
    private final DocterRepository docterRepository;
    @Override
    public void assignDoctorRole(Long userId, DoctorRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update role
        user.setRole(Role.DOCTOR);
        userRepository.save(user);

        // Create doctor profile
        Doctor doctor = new Doctor();
        doctor.setSpecialization(request.getSpecialization());
      //  doctor.setExperienceYears(request.getexperienceYears());
        doctor.setPhone(request.getPhone());
        doctor.setUser(user);

        docterRepository.save(doctor);
    }
}
