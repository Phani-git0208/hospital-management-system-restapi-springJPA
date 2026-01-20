package com.example.hospital.management.system.Entity;

import com.example.hospital.management.system.Entity.type.bloodgroup;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private int age;

    @Column(unique = true)
    private String email;

    private String gender;

    private String phone;


    @Enumerated(EnumType.STRING)
    private bloodgroup bloodGroup;

    @OneToOne
    @JoinColumn
    @JsonManagedReference
    private Insurance insurance;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointment;


}
