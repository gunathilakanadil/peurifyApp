package com.example.EmissionDetectorApplication.Services;

import com.example.EmissionDetectorApplication.Entity.Complaint;
import com.example.EmissionDetectorApplication.Repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintService {

    @Autowired
    private final ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    // Constructor-based injection


    public Complaint createComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }
}
