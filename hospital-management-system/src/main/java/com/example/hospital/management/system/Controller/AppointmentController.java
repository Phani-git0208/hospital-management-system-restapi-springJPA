package com.example.hospital.management.system.Controller;

import com.example.hospital.management.system.Dto.AppointmentDto;
import com.example.hospital.management.system.Entity.Appointment;
import com.example.hospital.management.system.Service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
@CrossOrigin
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/appointments")
    public Appointment book(@RequestBody AppointmentDto dto,
                            Authentication authentication) {
        return appointmentService.bookAppointment(authentication.getName(), dto);
    }

    @GetMapping("/appointments")
    public List<Appointment> myAppointments(Authentication authentication) {
        return appointmentService.getMyAppointments(authentication.getName());
    }
}

