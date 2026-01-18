package com.example.hospital.management.system.repository;

import com.example.hospital.management.system.Entity.Docter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocterRepository extends JpaRepository<Docter, Long> {
}