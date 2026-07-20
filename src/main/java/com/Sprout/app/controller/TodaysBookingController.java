package com.Sprout.app.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Sprout.app.Entity.Farmer;
import com.Sprout.app.Entity.TodaysBooking;
import com.Sprout.app.Service.FarmerService;
import com.Sprout.app.Service.TodaysBookingService;
import com.Sprout.app.Service.VillageService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/todays-bookings")
public class TodaysBookingController {
    @Autowired
    private TodaysBookingService todaysBookingService;

    @Autowired
    private FarmerService farmerService;

    @Autowired
    private VillageService villageService;

    @GetMapping("/fetch")
    public String getTodaysBookings(Model model) {
    	  LocalDate bookingDate = LocalDate.now();
        List<TodaysBooking> todaysBookings = todaysBookingService.getTodaysBookings(bookingDate);
        
        model.addAttribute("Bookings", todaysBookings);
        return "todaysBookings"; 
    }

   @GetMapping("/book")
   public String showBookForm(Model model, HttpSession session) {
	   String farmerEmail = (String) session.getAttribute("farmerEmail");
	   if (farmerEmail == null) {
		   // Not logged in as a farmer - send them to log in first, then back here
		   return "redirect:/farmerLogin";
	   }
	   model.addAttribute("todaysBooking", new TodaysBooking());
	   model.addAttribute("farmerEmail", farmerEmail);
	   model.addAttribute("farmerName", session.getAttribute("farmerName"));
	   model.addAttribute("villages", villageService.getAllVillages());
	   return "book";
   }
    
   @PostMapping("/book")
   public String saveTodaysBooking(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bookingDate,
                                                          @RequestParam(required = false) @DateTimeFormat(pattern = "HH:mm") LocalTime bookingTime,
                                                          @RequestParam String location,
                                                          Model model,
                                                          HttpSession session) {

       String email = (String) session.getAttribute("farmerEmail");
       if (email == null) {
           // Session expired or they navigated here directly without logging in
           return "redirect:/farmerLogin";
       }

       String loc = location == null ? "" : location.trim();

       // Keep whatever the person typed so the form can be redisplayed on error
       model.addAttribute("farmerEmail", email);
       model.addAttribute("farmerName", session.getAttribute("farmerName"));
       model.addAttribute("location", loc);
       model.addAttribute("villages", villageService.getAllVillages());
       model.addAttribute("bookingDate", bookingDate);
       model.addAttribute("bookingTime", bookingTime);

       Farmer farmer = farmerService.findByEmail(email);
       if (farmer == null) {
           // Their account was removed after logging in - clear the stale session
           session.invalidate();
           return "redirect:/farmerLogin";
       }

       if (loc.isEmpty()) {
           model.addAttribute("error", "Please enter a pickup location.");
           return "book";
       }

       if (bookingDate == null) {
           model.addAttribute("error", "Please choose a booking date.");
           return "book";
       }

       if (bookingDate.isBefore(LocalDate.now())) {
           model.addAttribute("error", "Booking date can't be in the past.");
           return "book";
       }

       if (bookingTime == null) {
           model.addAttribute("error", "Please choose a booking time.");
           return "book";
       }

       TodaysBooking todaysBooking = new TodaysBooking();
       todaysBooking.setFarmerId(farmer.getFarmerId().longValue());
       todaysBooking.setFarmerName(farmer.getName());
       todaysBooking.setBookingDate(bookingDate);
       todaysBooking.setBookingTime(bookingTime);
       todaysBooking.setLocation(loc);
       todaysBookingService.saveTodaysBooking(todaysBooking);

       model.addAttribute("farmerName", farmer.getName());
       model.addAttribute("location", loc);
       model.addAttribute("bookingDate", bookingDate);
       model.addAttribute("bookingTime", bookingTime);
       return "booksuccess";
   }
}