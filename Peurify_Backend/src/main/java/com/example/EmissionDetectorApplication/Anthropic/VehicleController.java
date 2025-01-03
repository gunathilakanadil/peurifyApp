package com.example.EmissionDetectorApplication.Anthropic;

import com.example.EmissionDetectorApplication.Entity.Vehicle;
import com.example.EmissionDetectorApplication.Services.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "*")
@Slf4j
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/check-emission")
    public ResponseEntity<EmissionCheckResponse> checkEmission(
            @RequestParam("numberPlate") String numberPlate,
            @RequestParam("image") MultipartFile image,
            @RequestParam("id") Long id) {
        log.info("Received emission check request for vehicle: {}", numberPlate);

        EmissionCheckResponse response = vehicleService.checkEmission(numberPlate, image,id);

        if ("ERROR".equals(response.getStatus())) {
            log.error("Error processing emission check: {}", response.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

        log.info("Emission check completed for vehicle: {}, status: {}",
                numberPlate, response.getStatus());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{numberPlate}/history")
    public ResponseEntity<List<Vehicle>> getVehicleHistory(@PathVariable String numberPlate) {
        List<Vehicle> history = vehicleService.getVehicleHistory(numberPlate);
        return ResponseEntity.ok(history);
    }
}