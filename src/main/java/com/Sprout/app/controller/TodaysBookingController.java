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



@Controller
@RequestMapping("/todays-bookings")
public class TodaysBookingController {
    @Autowired
    private TodaysBookingService todaysBookingService;

    @Autowired
    private FarmerService farmerService;

    @GetMapping("/fetch")
    public String getTodaysBookings(Model model) {
    	  LocalDate bookingDate = LocalDate.now();
        List<TodaysBooking> todaysBookings = todaysBookingService.getTodaysBookings(bookingDate);
        
        model.addAttribute("Bookings", todaysBookings);
        return "todaysBookings"; 
    }

   @GetMapping("/book")
   public String showBookForm(Model model) {
	   model.addAttribute("todaysBooking", new TodaysBooking());
	   return "book";
   }
    
    @PostMapping("/book")
    public String saveTodaysBooking(@RequestParam String farmerEmail,
                                                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bookingDate,
                                                           @RequestParam(required = false) @DateTimeFormat(pattern = "HH:mm") LocalTime bookingTime,
                                                           @RequestParam String location,
                                                           Model model) {

        String email = farmerEmail == null ? "" : farmerEmail.trim();
        String loc = location == null ? "" : location.trim();

        // Keep whatever the person typed so the form can be redisplayed on error
        model.addAttribute("farmerEmail", email);
        model.addAttribute("location", loc);
        model.addAttribute("bookingDate", bookingDate);
        model.addAttribute("bookingTime", bookingTime);

        if (email.isEmpty() || !email.contains("@")) {
            model.addAttribute("error", "Please enter a valid email address.");
            return "book";
        }

        Farmer farmer = farmerService.findByEmail(email);
        if (farmer == null) {
            model.addAttribute("error", "We couldn't find a farmer account with that email. Please check and try again, or register first.");
            return "book";
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
        return "booksuccess";
    }
}