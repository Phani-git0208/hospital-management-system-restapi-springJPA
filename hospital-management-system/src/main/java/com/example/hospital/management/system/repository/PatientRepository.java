package com.example.hospital.management.system.repository;

import com.example.hospital.management.system.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByUserEmail(String email);

    @Query("select distinct p from Patient p join fetch p.appointment")
    List<Patient> findPatientsWithAppointments();
}
