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
public class PatientServiceimp implements PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PatientDto> getAllPatient() {
        return patientRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public List<PatientDto> getPatientsWithAppointments() {
        return patientRepository.findPatientsWithAppointments()
                .stream()
                .map(this::mapToDtoWithAppointments)
                .toList();
    }

    // ============================
    // Get logged-in patient profile
    // ============================
    @Override
    public PatientDto getMyProfile(String email) {
        Patient patient = patientRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Patient profile not found"));

        return mapToDtoWithAppointments(patient);
    }

    // ============================
    // Update logged-in patient
    // ============================
    @Override
    public PatientDto updateMyProfile(String email, PatientDto dto) {

        Patient patient = patientRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Patient profile not found"));

        // Only update allowed fields
        patient.setAge(dto.getAge());
        patient.setPhone(dto.getPhone());
        patient.setGender(dto.getGender());
        patient.setBloodGroup(dto.getBloodGroup());
        patient.setInsurance(dto.getInsurance());

        Patient saved = patientRepository.save(patient);

        return mapToDto(saved);
    }

    // ============================
    // Mapping helpers
    // ============================
    private PatientDto mapToDto(Patient patient) {
        PatientDto dto = modelMapper.map(patient, PatientDto.class);

        // Map from User entity
        dto.setName(patient.getUser().getName());
        dto.setEmail(patient.getUser().getEmail());

        return dto;
    }

    private PatientDto mapToDtoWithAppointments(Patient patient) {
        PatientDto dto = mapToDto(patient);

        if (patient.getAppointment() != null) {
            List<AppointmentDto> appointmentDtos = patient.getAppointment()
                    .stream()
                    .map(a -> modelMapper.map(a, AppointmentDto.class))
                    .toList();

            dto.setAppointments(appointmentDtos);
        }

        return dto;
    }
}



