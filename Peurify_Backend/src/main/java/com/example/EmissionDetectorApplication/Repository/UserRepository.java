package com.example.EmissionDetectorApplication.Repository;

import com.example.EmissionDetectorApplication.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByPhoneNumber(String phoneNumber);
}