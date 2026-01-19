package com.example.hospital.management.system.Dto;

import com.example.hospital.management.system.Entity.Appointment;
import com.example.hospital.management.system.Entity.Insurance;
import com.example.hospital.management.system.Entity.type.bloodgroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private Long id;
    private String name;
    private int age;
    private String email;
    private String gender;
    private String phone;
    private bloodgroup bloodGroup;
    private Insurance insurance;
    private List<AppointmentDto> appointments;


}
