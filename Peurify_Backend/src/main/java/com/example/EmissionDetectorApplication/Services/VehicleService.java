package com.example.EmissionDetectorApplication.Services;

import com.example.EmissionDetectorApplication.Anthropic.ImageAnalysisService;
import com.example.EmissionDetectorApplication.Entity.Complaint;
import com.example.EmissionDetectorApplication.Entity.User;
import com.example.EmissionDetectorApplication.Entity.Vehicle;
import com.example.EmissionDetectorApplication.Anthropic.EmissionCheckResponse;
import com.example.EmissionDetectorApplication.Anthropic.EmissionResponse;
import com.example.EmissionDetectorApplication.Repository.UserRepository;
import com.example.EmissionDetectorApplication.Repository.VehicleRepository;
import com.example.EmissionDetectorApplication.TwilloSMS.SmsService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ImageAnalysisService imageAnalysisService;
    private final UserService userService;
    private final ComplaintService complaintService;

    private final SmsService smsService;

    private final UserRepository userRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository,
                          ImageAnalysisService imageAnalysisService,
                          UserService userService,
                          ComplaintService complaintService, SmsService smsService, UserRepository userRepository) {
        this.vehicleRepository = vehicleRepository;
        this.imageAnalysisService = imageAnalysisService;
        this.userService = userService;
        this.complaintService = complaintService;
        this.smsService = smsService;
        this.userRepository = userRepository;
    }

    public EmissionCheckResponse checkEmission(String numberPlate, MultipartFile image, Long id) {
        try {
            // Validate input
            if (numberPlate == null || numberPlate.trim().isEmpty()) {
                throw new IllegalArgumentException("Number plate cannot be empty");
            }

            if (image == null || image.isEmpty()) {
                throw new IllegalArgumentException("Image file cannot be empty");
            }

            // Analyze image for emissions
            EmissionResponse emissionResponse = imageAnalysisService.analyzeImage(image.getBytes());

            if (emissionResponse.getHasEmission() == -1) {
                throw new RuntimeException("Error analyzing image: " + emissionResponse.getMessage());
            }

            // Determine status based on emission detection
            String status = (emissionResponse.getHasEmission() == 1) ? "FAIL" : "PASS";
            System.out.println("NEAR IF");
            // Create or update vehicle record
            if (status.equals("FAIL")) {
                System.out.println("Inside FAIL");
                // Create or update vehicle record
                Vehicle vehicle = vehicleRepository.findByNumberPlate(numberPlate);


                // Get user by ID
                User user = userService.getUserById(id);

                // Create and set up complaint
                Complaint complaint = new Complaint();
                complaint.setStatus("FAIL");
                complaint.setImage(image.getBytes());
                complaint.setVehicle(vehicle);
                complaint.setUser(user);

                // Save complaint
                complaintService.createComplaint(complaint);
                //Send user to msg
             smsService.sendSms("+94"+user.getPhoneNumber(),"Congradulation ✨ You Got 1000 reward Collect it");
            //updating user rewards
                int rewards=user.getRewards()+100;
                user.setRewards(rewards);

                userRepository.save(user);
                System.out.println(vehicle.getMnumber());
                //Send VehicleOwner msg
               smsService.sendSms("+94"+vehicle.getMnumber(),"🚫🚫Your Vehicle Licene "+vehicle.getLicense()+" has been canceled due to an excessive emission level. As a result, a penalty fee of Rs.4000 is required to be paid within the next two weeks to reinstate your license."+"this is the image"+image);

                vehicle.setStatuss("FAILED");
                vehicleRepository.save(vehicle);

                return EmissionCheckResponse.builder()
                        .numberPlate(vehicle.getNumberPlate())
                        .status("FAIL")
                        .message("Emission check completed successfully")
                        .build();
            }



            else if (status.equals("PASS")) {
                System.out.println("Inside PASS");
                // Create or update vehicle record
                Vehicle vehicle = vehicleRepository.findByNumberPlate(numberPlate);



                // Get user by ID
                User user = userService.getUserById(id);

                // Create and set up complaint
                Complaint complaint = new Complaint();
                complaint.setStatus("PASS");
                complaint.setVehicle(vehicle);
                complaint.setUser(user);
                complaint.setImage(image.getBytes());
                vehicle.setStatuss("PASSED");
                vehicleRepository.save(vehicle);
                // Save complaint


                smsService.sendSms("+94"+user.getPhoneNumber(),"Sorry Your Complaint is ignored because that vehicle doesnt exist Emision");

                return EmissionCheckResponse.builder()
                        .numberPlate(vehicle.getNumberPlate())
                        .status("PASS")
                        .message("Emission check completed successfully")
                        .build();
            }


        } catch (Exception e) {
            log.error("Error processing emission check for vehicle {}: {}", numberPlate, e.getMessage());
            return EmissionCheckResponse.builder()
                    .numberPlate(numberPlate)
                    .status("ERROR")
                    .message("Error processing emission check: " + e.getMessage())
                    .build();
        }
        return EmissionCheckResponse.builder()
                .numberPlate(numberPlate)
                .status("UNKNOWN")
                .message("Emission check status could not be determined.")
                .build();
    }

    public List<Vehicle> getVehicleHistory(String numberPlate) {
        return vehicleRepository.findByNumberPlateAndStatus(numberPlate, "FAIL");
    }
}
