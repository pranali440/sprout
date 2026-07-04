package com.Sprout.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Sprout.app.Entity.Farmer;
import com.Sprout.app.Entity.LoginRequest;
import com.Sprout.app.Service.FarmerService;



@Controller
public class FarmerController {
    @Autowired
    private FarmerService farmerService;

    @PostMapping("/register")
    public ResponseEntity<Integer> registerFarmer(@RequestBody Farmer farmer) {
        Integer registrationId = farmerService.registerFarmer(farmer);
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationId);
    }
    
    @GetMapping("/farmerLogin")
    public String login() {
    	return "farmerLogin";
    }
    
    @PostMapping("/farmerlogin")
    public String login(@RequestParam Integer farmerId, 
    		                       @RequestParam String Password) {
    	Farmer farmer = farmerService.findById(farmerId);
    	if (farmer != null &&  farmer.getPassword().equals(Password)) {
            return "farmerportaldash";
        } else {
            return "redirect:/loginerror";
        }
    }
    
    @GetMapping("fetch/{farmerId}")
    public ResponseEntity<Farmer> getFarmerById(@PathVariable Integer farmerId) {
        Farmer farmer = farmerService.findById(farmerId);
        if (farmer != null) {
            return ResponseEntity.ok(farmer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("update/{farmerId}")
    public ResponseEntity<Farmer> updateFarmer(@PathVariable Integer farmerId, @RequestBody Farmer updatedFarmer) {
        Farmer farmer = farmerService.updateFarmer(farmerId, updatedFarmer);
        return ResponseEntity.ok(farmer);
    }
}


