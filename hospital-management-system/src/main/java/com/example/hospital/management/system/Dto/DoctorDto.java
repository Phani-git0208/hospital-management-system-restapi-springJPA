package com.example.hospital.management.system.Dto;

import com.example.hospital.management.system.Entity.type.Specialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {

    private Long id;
    private String name;
    private String specialization;
    private String email;
    private String phone;
    private int experienceYears;
}
