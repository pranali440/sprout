package com.Sprout.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Sprout.app.Entity.Farmer;
import com.Sprout.app.Entity.LoginRequest;
import com.Sprout.app.Service.FarmerService;

import jakarta.servlet.http.HttpSession;



@Controller
public class FarmerController {
    @Autowired
    private FarmerService farmerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Integer> registerFarmer(@RequestBody Farmer farmer) {
        Integer registrationId = farmerService.registerFarmer(farmer);
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationId);
    }

    @GetMapping("/farmerRegister")
    public String registerPage() {
        return "farmerRegister";
    }

    @PostMapping("/farmerRegister")
    public String registerFarmerForm(@ModelAttribute Farmer farmer, RedirectAttributes redirectAttributes, Model model) {
        try {
            Integer farmerId = farmerService.registerFarmer(farmer);
            redirectAttributes.addFlashAttribute("farmerId", farmerId);
            return "redirect:/registration-success";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "farmerRegister";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "This email is already registered. Please log in instead.");
            return "farmerRegister";
        }
    }
    
    @GetMapping("/farmerLogin")
    public String login() {
    	return "farmerLogin";
    }
    
    @PostMapping("/farmerlogin")
    public String login(@RequestParam String email, 
    		                       @RequestParam String password,
    		                       Model model,
    		                       HttpSession session) {
    	Farmer farmer = farmerService.findByEmail(email);
    	if (farmer != null && passwordEncoder.matches(password, farmer.getPassword())) {
            session.setAttribute("farmerEmail", farmer.getEmail());
            session.setAttribute("farmerName", farmer.getName());
            return "redirect:/farmerportaldash";
        } else {
            model.addAttribute("error", "Invalid email or password. Please try again.");
            return "farmerLogin";
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