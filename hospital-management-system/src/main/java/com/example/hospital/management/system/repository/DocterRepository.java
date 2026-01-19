package com.example.hospital.management.system.repository;

import com.example.hospital.management.system.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocterRepository extends JpaRepository<Doctor, Long> {

    @Query
            ("select distinct d from Doctor d join fetch d.department")
    List<Doctor> getDoctorsAndDept();
}