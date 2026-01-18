package com.example.hospital.management.system.repository;

import com.example.hospital.management.system.Entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}