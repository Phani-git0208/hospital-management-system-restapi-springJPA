package com.example.hospital.management.system.Service;

import com.example.hospital.management.system.Dto.DoctorRequest;

public interface AdminService {
    public void assignDoctorRole(Long userId, DoctorRequest request) ;

}
