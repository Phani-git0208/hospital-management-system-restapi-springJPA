package com.example.hospital.management.system.Controller;

import com.example.hospital.management.system.Dto.DoctorDto;
import com.example.hospital.management.system.Dto.PatientDto;
import com.example.hospital.management.system.Service.DocterService;
import com.example.hospital.management.system.Service.DoctorServiceimpl;
import com.example.hospital.management.system.Service.PatientServiceimp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class Controller {

    private final DoctorServiceimpl doctorService;
    private final PatientServiceimp patientService;

    @GetMapping("/admin/doctors")
    public List<DoctorDto> getAllDocters() {
        return doctorService.getAllDocters();
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<DoctorDto> getdoctorinfo(@PathVariable Long id){
        return doctorService.getDoctorById(id);
    }

    @GetMapping("/public/patient")
    public List<PatientDto> getAllPatient() {
        return patientService.getAllPatient();

    }

    @GetMapping("/patients-with-appointments")
    public List<PatientDto> getPatientsWithAppointments() {
        return patientService.getPatientsWithAppointments();
    }

    @GetMapping("/doctor-with-dept")
    public List<DoctorDto> getDoctorsAndDept(){
        return doctorService.getDoctorsAndDept();
    }


}