package com.example.hospital.management.system.repository;

import com.example.hospital.management.system.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}