package com.example.EmissionDetectorApplication.Entity;
import com.example.EmissionDetectorApplication.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Number")
    private String phoneNumber;

    @Column(name = "Rewards")
    private int rewards;

    @Column(name = "Password")
    private String password;

    @Column(name = "NIC")
    private String nic;
}
