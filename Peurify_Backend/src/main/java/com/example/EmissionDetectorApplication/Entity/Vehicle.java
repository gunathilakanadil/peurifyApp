package com.example.EmissionDetectorApplication.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numberPlate;

    @Column(nullable = false)
    private String statuss;  // "PASS" or "FAIL" based on emission



    @Column(name = "License")
    private String License;

    @Column(name = "Mobile_Number")
    private String Mnumber;


}