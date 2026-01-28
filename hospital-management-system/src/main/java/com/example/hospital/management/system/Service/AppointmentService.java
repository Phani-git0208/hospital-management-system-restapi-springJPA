package com.example.hospital.management.system.Service;

import com.example.hospital.management.system.Dto.AppointmentDto;
import com.example.hospital.management.system.Entity.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment bookAppointment(String email, AppointmentDto dto);

    List<Appointment> getMyAppointments(String email);
}
