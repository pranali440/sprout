package com.Sprout.app.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.Sprout.app.Entity.TodaysBooking;
import com.Sprout.app.Service.TodaysBookingService;



@Controller
@RequestMapping("/todays-bookings")
public class TodaysBookingController {
    @Autowired
    private TodaysBookingService todaysBookingService;

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
    public String saveTodaysBooking(@RequestParam Long farmerId,
                                                           @RequestParam String farmerName,
                                                           @RequestParam LocalDate bookingDate,
                                                           @RequestParam LocalTime bookingTime,
                                                           @RequestParam String location) {
    	 TodaysBooking todaysBooking = new TodaysBooking();
    	    todaysBooking.setFarmerId(farmerId);
    	    todaysBooking.setFarmerName(farmerName);
    	    todaysBooking.setBookingDate(bookingDate);
    	    todaysBooking.setBookingTime(bookingTime);
    	    todaysBooking.setLocation(location);
        TodaysBooking savedBooking = todaysBookingService.saveTodaysBooking(todaysBooking);
        return "booksuccess";
        
    }
}
