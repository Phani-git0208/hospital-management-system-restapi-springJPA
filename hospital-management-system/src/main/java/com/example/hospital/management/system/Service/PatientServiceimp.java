package com.example.hospital.management.system.Service;

import com.example.hospital.management.system.Dto.AppointmentDto;
import com.example.hospital.management.system.Dto.PatientDto;
import com.example.hospital.management.system.Entity.Patient;
import com.example.hospital.management.system.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceimp implements PatientService{

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PatientDto> getAllPatient() {
        return patientRepository.findAll()
                .stream()
                .map(dto  -> modelMapper.map(dto, PatientDto.class))
                .toList();


    }

    @Override
    public List<PatientDto> getPatientsWithAppointments() {
        return patientRepository.findPatientsWithAppointments()
                .stream()
                .map(patient -> {
                    PatientDto dto = modelMapper.map(patient, PatientDto.class);

                    // manual mapping for appointments
                    List<AppointmentDto> appointmentDtos = patient.getAppointment()
                            .stream()
                            .map(a -> modelMapper.map(a, AppointmentDto.class))
                            .toList();

                    dto.setAppointments(appointmentDtos);
                    return dto;
                })
                .toList();
    }
}

