package com.example.hospital.management.system.Service;

import com.example.hospital.management.system.Dto.DoctorDto;
import com.example.hospital.management.system.Entity.Doctor;
import com.example.hospital.management.system.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceimp implements AdminService {

    private final DoctorRepository doctorRepository;

    @Override
    public List<DoctorDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(d -> new DoctorDto(
                        d.getUser().getId(),
                        d.getUser().getName(),
                        d.getUser().getEmail(),
                        d.getSpecialization(),
                        d.getPhone(),
                        d.getExperienceYears()
                ))
                .toList();
    }

}
