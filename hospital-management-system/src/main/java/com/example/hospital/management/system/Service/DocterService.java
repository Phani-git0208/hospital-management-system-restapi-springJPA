package com.example.hospital.management.system.Service;

import com.example.hospital.management.system.Dto.DoctorDto;


import java.util.List;

public interface DocterService {

    public List<DoctorDto> getAllDocters();
    public List<DoctorDto> getDoctorsAndDept();
}
