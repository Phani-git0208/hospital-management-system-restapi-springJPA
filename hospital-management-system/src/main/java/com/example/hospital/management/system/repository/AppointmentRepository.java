package com.example.hospital.management.system.repository;

import com.example.hospital.management.system.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  AppointmentRepository extends JpaRepository<Appointment, Long> {
}