package com.example.hospital.management.system.Service;

import com.example.hospital.management.system.Dto.AppointmentDto;
import com.example.hospital.management.system.Entity.Appointment;
import com.example.hospital.management.system.Entity.Doctor;
import com.example.hospital.management.system.Entity.Patient;
import com.example.hospital.management.system.repository.AppointmentRepository;
import com.example.hospital.management.system.repository.DoctorRepository;
import com.example.hospital.management.system.repository.PatientRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceimp implements AppointmentService{
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public Appointment bookAppointment(String email, AppointmentDto dto) {

        Patient patient = patientRepository
                .findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Doctor doctor = doctorRepository
                .findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setReason(dto.getReason());
        appointment.setDoctor(doctor);
        appointment.setDoctorName(doctor.getName());
        appointment.setPatient(patient);

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getMyAppointments(String email) {
        Patient patient = patientRepository
                .findByUserEmail(email)
                .orElseThrow();

        return appointmentRepository.findByPatientId(patient.getId());
    }
}

