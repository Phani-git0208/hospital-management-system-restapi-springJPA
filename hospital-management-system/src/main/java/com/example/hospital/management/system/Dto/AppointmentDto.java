package com.example.hospital.management.system.Dto;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentDto {

    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String reason;
    private Long doctorId;
    }


