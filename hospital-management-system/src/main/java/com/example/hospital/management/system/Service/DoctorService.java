package com.example.hospital.management.system.Service;

import com.example.hospital.management.system.Dto.DoctorDto;
import com.example.hospital.management.system.Entity.Doctor;


import java.security.Principal;

public interface DoctorService {

    Doctor saveDoctorProfile(DoctorDto dto, Principal principal);
    Doctor getDoctorProfile(Principal principal);
}
