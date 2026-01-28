package com.example.hospital.management.system.repository;

import com.example.hospital.management.system.Entity.Doctor;
import com.example.hospital.management.system.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
