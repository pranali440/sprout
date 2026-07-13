package com.Sprout.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Sprout.app.Entity.Crops;
import com.Sprout.app.Entity.Farmer;
import com.Sprout.app.Entity.Weight;
import com.Sprout.app.Service.CropService;
import com.Sprout.app.Service.FarmerService;
import com.Sprout.app.Service.FertilizerDataService;
import com.Sprout.app.Service.WeightService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FarmerPortalController {
	
	@Autowired
	CropService cropService;
	
	@Autowired
	FertilizerDataService fertilizerDataService;

	@Autowired
	FarmerService farmerService;

	@Autowired
	WeightService weightService;

    @GetMapping("/crops")
    public String getCrops(Model model) {
        List<Crops> crops = cropService.getAllCrops();
        model.addAttribute("crops", crops);
        return "crops"; 
    }
    
    @GetMapping("/analytics")
    public String analytics() {
        return "analytics"; 
    }
    
    @GetMapping("/fertilizer")
    public String showFertilizerData(Model model) {
        model.addAttribute("fertilizerData", fertilizerDataService.getAllFertilizerData());
        return "fertilizer";
    }

    @GetMapping("/farmer-bills")
    public String showFarmerBills(Model model, HttpSession session) {
        String farmerEmail = (String) session.getAttribute("farmerEmail");
        if (farmerEmail == null) {
            // Not logged in as a farmer - send them to log in first, then back here
            return "redirect:/farmerLogin";
        }

        Farmer farmer = farmerService.findByEmail(farmerEmail);
        if (farmer == null) {
            session.invalidate();
            return "redirect:/farmerLogin";
        }

        List<Weight> bills = weightService.getWeightsByFarmerId(farmer.getFarmerId());
        model.addAttribute("farmerName", farmer.getName());
        model.addAttribute("bills", bills);
        return "farmerBills";
    }
    
}