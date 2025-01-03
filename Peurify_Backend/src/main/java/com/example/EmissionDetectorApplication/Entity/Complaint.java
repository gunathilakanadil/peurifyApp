package com.example.EmissionDetectorApplication.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "complaints")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Complaint {
    public Complaint(String status, User user, Vehicle vehicle) {
        this.status = status;
        this.user = user;
        this.vehicle = vehicle;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status" )
    private String status;

    @Lob
    @Column(length = 1000000, name="image")
    private byte[] image;

    @JsonBackReference(value = "user-complaints")  // More specific value
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" )
    private User user;



    @JsonBackReference(value = "vehicle-complaint")  // More specific value
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id" )
    private Vehicle vehicle;
}