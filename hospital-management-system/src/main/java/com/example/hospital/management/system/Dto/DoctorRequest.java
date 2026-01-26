package com.example.hospital.management.system.Dto;

import lombok.Data;

@Data
public class DoctorRequest {
    private String specialization;
    private int experience;
    private String phone;
}
