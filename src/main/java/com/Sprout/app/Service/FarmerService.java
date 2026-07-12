package com.Sprout.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Sprout.app.Entity.Farmer;
import com.Sprout.app.Entity.LoginRequest;
import com.Sprout.app.repository.FarmerRepository;


@Service
public class FarmerService {
	 
    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Integer registerFarmer(Farmer farmer) {
    	 if (farmer.getEmail() == null || farmer.getEmail().isEmpty()) {
             throw new IllegalArgumentException("Email is required.");
         }
        farmer.setPassword(passwordEncoder.encode(farmer.getPassword()));
        Farmer savedFarmer = farmerRepository.save(farmer);
        return savedFarmer.getFarmerId();
    }
    
    public ResponseEntity<?> login(LoginRequest loginRequest) {
    	String email = loginRequest.getEmail();
    	System.out.println(email);
        String password = loginRequest.getPassword();

        Farmer farmer = farmerRepository.findByEmail(email);

        if (farmer != null && passwordEncoder.matches(password, farmer.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
    

    public Farmer findById(Integer farmerId) {
        return farmerRepository.findById(farmerId).orElse(null);
    }

    public Farmer findByEmail(String email) {
        return farmerRepository.findByEmail(email);
    }

    public Farmer updateFarmer(Integer farmerId, Farmer updatedFarmer) {
        Farmer existingFarmer = farmerRepository.findById(farmerId).orElse(null);
        if (existingFarmer != null) {
            updatedFarmer.setFarmerId(farmerId);
            return farmerRepository.save(updatedFarmer);
        } else {
            throw new RuntimeException("Farmer not found with id: " + farmerId);
        }
    }
   }