package com.example.hospital.management.system.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    private String doctorName;

    private String reason;


    // Owning side of relationship
    @ManyToOne
    @JoinColumn(name = "patient_id" , nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Docter doctor;
}

