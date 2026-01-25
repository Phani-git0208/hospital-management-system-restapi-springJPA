package com.example.hospital.management.system.Service;

import com.example.hospital.management.system.Dto.DoctorDto;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface DocterService {

    public List<DoctorDto> getAllDocters();
    public List<DoctorDto> getDoctorsAndDept();


    public ResponseEntity<DoctorDto> getDoctorById(Long id);
}
