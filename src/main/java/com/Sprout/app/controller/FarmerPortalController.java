package com.Sprout.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Sprout.app.Entity.Crops;
import com.Sprout.app.Entity.Farmer;
import com.Sprout.app.Entity.TodaysBooking;
import com.Sprout.app.Entity.Weight;
import com.Sprout.app.Service.CropService;
import com.Sprout.app.Service.FarmerService;
import com.Sprout.app.Service.FertilizerDataService;
import com.Sprout.app.Service.TodaysBookingService;
import com.Sprout.app.Service.WeightService;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	@Autowired
	TodaysBookingService todaysBookingService;

    @GetMapping("/crops")
    public String getCrops(Model model) {
        List<Crops> crops = cropService.getAllCrops();
        model.addAttribute("crops", crops);
        return "crops"; 
    }
    
    @GetMapping("/analytics")
    public String analytics(Model model, HttpSession session) {
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

        List<TodaysBooking> bookings = todaysBookingService.getBookingsByFarmerId(farmer.getFarmerId().longValue());
        List<Weight> bills = weightService.getWeightsByFarmerId(farmer.getFarmerId());

        // Build simple string-keyed maps so Thymeleaf's JS inlining doesn't
        // choke trying to serialize java.time.LocalDate / LocalTime directly.
        List<Map<String, Object>> bookingRows = new ArrayList<>();
        for (TodaysBooking b : bookings) {
            Map<String, Object> row = new HashMap<>();
            row.put("bookingDate", b.getBookingDate() == null ? "" : b.getBookingDate().toString());
            row.put("bookingTime", b.getBookingTime() == null ? "" : b.getBookingTime().toString());
            row.put("location", b.getLocation());
            row.put("status", b.getStatus());
            bookingRows.add(row);
        }

        List<Map<String, Object>> billRows = new ArrayList<>();
        for (Weight w : bills) {
            Map<String, Object> row = new HashMap<>();
            row.put("id", w.getId());
            row.put("totalWeight", w.getTotalWeight());
            billRows.add(row);
        }

        String bookingsJson = "[]";
        String billsJson = "[]";
        try {
            ObjectMapper mapper = new ObjectMapper();
            bookingsJson = mapper.writeValueAsString(bookingRows);
            billsJson = mapper.writeValueAsString(billRows);
        } catch (Exception e) {
            // Fall back to empty arrays rather than breaking the whole page
            e.printStackTrace();
        }

        model.addAttribute("farmerName", farmer.getName());
        model.addAttribute("bookingsJson", bookingsJson);
        model.addAttribute("billsJson", billsJson);
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