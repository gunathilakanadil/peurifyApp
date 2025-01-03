package com.example.EmissionDetectorApplication.Repository;

import com.example.EmissionDetectorApplication.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle findByNumberPlate(String numberPlate);

    @Query("SELECT v FROM Vehicle v WHERE v.numberPlate = :numberPlate AND v.statuss = :status")
    List<Vehicle> findByNumberPlateAndStatus(String numberPlate, String status);
}