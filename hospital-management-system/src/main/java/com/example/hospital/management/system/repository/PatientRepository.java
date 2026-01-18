package com.example.hospital.management.system.repository;

import com.example.hospital.management.system.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
