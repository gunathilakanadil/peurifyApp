package com.example.EmissionDetectorApplication.Repository;

import com.example.EmissionDetectorApplication.Entity.Complaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Long> {

}
