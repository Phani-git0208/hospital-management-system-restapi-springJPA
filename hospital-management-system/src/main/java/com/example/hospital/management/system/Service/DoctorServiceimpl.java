package com.example.hospital.management.system.Service;


import com.example.hospital.management.system.Dto.DoctorDto;
import com.example.hospital.management.system.Entity.Doctor;
import org.modelmapper.ModelMapper;   // ✅ CORRECT
import com.example.hospital.management.system.repository.DocterRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.stream;


@Service
@RequiredArgsConstructor
public class DoctorServiceimpl implements DocterService{

    private final DocterRepository doctorRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<DoctorDto> getAllDocters() {
        return  doctorRepository.findAll()
        .stream()
                .map(doc -> modelMapper.map(doc , DoctorDto.class))
                .toList();

    }

    @Override
    public List<DoctorDto> getDoctorsAndDept() {
        return doctorRepository.findAll()
                .stream().map((element) -> modelMapper.map(element, DoctorDto.class))
                .toList();
    }


    @Override
    public ResponseEntity<DoctorDto> getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        DoctorDto dto = new DoctorDto(
                doctor.getId(),
                doctor.getName(),
                doctor.getSpecialization(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getExperienceYears()
        );

        return ResponseEntity.ok(dto);
    }
    }







