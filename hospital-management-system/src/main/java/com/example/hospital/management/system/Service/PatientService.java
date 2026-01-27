package com.example.hospital.management.system.Service;

import com.example.hospital.management.system.Dto.PatientDto;


import java.util.List;

public interface PatientService  {
    public List<PatientDto> getAllPatient();
    public List<PatientDto> getPatientsWithAppointments();

    // ✅ NEW
    PatientDto getMyProfile(String email);

    // ✅ NEW
    PatientDto updateMyProfile(String email, PatientDto patientDto);

}
