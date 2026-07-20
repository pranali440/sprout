package com.Sprout.app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Sprout.app.Entity.Booking;
import com.Sprout.app.Entity.BookingForm;
import com.Sprout.app.Entity.Driver;
import com.Sprout.app.Entity.Farmer;
import com.Sprout.app.Entity.Owner;
import com.Sprout.app.Entity.Sector;
import com.Sprout.app.Entity.TodaysBooking;
import com.Sprout.app.Entity.Tractor;
import com.Sprout.app.Entity.Village;
import com.Sprout.app.Service.BookingService;
import com.Sprout.app.Service.DriverService;
import com.Sprout.app.Service.FarmerService;
import com.Sprout.app.Service.OwnerService;
import com.Sprout.app.Service.TodaysBookingService;
import com.Sprout.app.Service.TractorService;
import com.Sprout.app.Service.VillageService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BookingController {

	@Autowired
   private  BookingService bookingService;
	@Autowired
    private  FarmerService farmerService;
	@Autowired
    private  OwnerService ownerService;
	@Autowired
    private  DriverService driverService;
	@Autowired
    private  TractorService tractorService;
	@Autowired
    private  VillageService villageService;
	@Autowired
    private  TodaysBookingService todaysBookingService;
    
    	@GetMapping("/booking/{id}/view")
        public String viewBooking(@PathVariable Long id, @RequestParam("location") String villageName, @RequestParam("farmerId") Integer farmerId, Model model, RedirectAttributes redirectAttributes) {
    
            Farmer farmer = farmerService.findById(farmerId);
            TodaysBooking todaysbooking = todaysBookingService.findById(id);

            Village farmerVillage = villageService.findByName(villageName);
            if (farmerVillage == null) {
                // The farmer typed this location freely when booking, so it won't always
                // match a village registered in the system. Without a matching village we
                // can't look up its sector (and therefore can't find nearby owners/drivers),
                // so send the admin back with a clear message instead of a 500 error.
                redirectAttributes.addFlashAttribute("error",
                    "\"" + villageName + "\" isn't a recognized village, so owners and drivers " +
                    "in that area couldn't be found. Ask the farmer to confirm their location, " +
                    "or add \"" + villageName + "\" as a registered village first.");
                return "redirect:/todays-bookings/fetch";
            }
            Sector farmerSector = farmerVillage.getSector();

            List<Owner> ownersInSameSector = ownerService.findOwnersBySector(farmerSector);
        List<Driver> availableDrivers = new ArrayList<>();
        List<Tractor> availableTractors = new ArrayList<>();
        
        for (Owner owner : ownersInSameSector) {
            List<Driver> drivers = driverService.findByOwner(owner);
            List<Tractor> tractors = tractorService.findByOwner(owner);
            for (Driver driver : drivers) {
                if (!driver.isAllocated()) {
                    availableDrivers.add(driver);
                }
            }
            for (Tractor tractor : tractors) {
                if (!tractor.isAllocated()) {
                    availableTractors.add(tractor);
                }
            }
        }
        
        Owner owner = new Owner();
        model.addAttribute("farmer", farmer);
        model.addAttribute("booking", todaysbooking);
        model.addAttribute("owners", ownersInSameSector);
        model.addAttribute("drivers", availableDrivers);
        model.addAttribute("tractors", availableTractors);
        model.addAttribute("owner", owner);
        model.addAttribute("driver", new Driver());
        model.addAttribute("tractor", new Tractor());
        return "viewbooking";
    }

    	@PostMapping("/confirm-booking")
    	public String confirmBooking(@ModelAttribute BookingForm bookingForm, 
    	                             @RequestParam("driverId") Integer driverId, 
    	                             @RequestParam("tractorId") Integer tractorId,
    	                             @RequestParam("ownerId") Long ownerId,
    	                             @RequestParam("farmerId") Integer farmerId,
    	                             @RequestParam LocalDate bookingDate,
                                     @RequestParam LocalTime bookingTime,
    	                             RedirectAttributes redirectAttributes) {
    	
    	    Driver selectedDriver = driverService.findById(driverId);
    	    Tractor selectedTractor = tractorService.findById(tractorId);
    	    Owner selectedOwner = ownerService.findById(ownerId);
    	    
    	    Farmer farmer = farmerService.findById(farmerId);
    	    System.out.println(
    	    	"Farmer details: "	+ farmer.getName());
    	    
    	    Booking booking = new Booking();
    	    booking.setBookingDate(bookingDate); 
    	    booking.setBookingTime(bookingTime);
    	    booking.setFarmer(farmer);
    	    booking.setOwner(selectedOwner);
    	    booking.setTractor(selectedTractor); 
    	    booking.setDriver(selectedDriver);   
    	    
    	    // Save the booking to the database
    	    bookingService.save(booking);
    	    
    	    // Update the allocated status of the selected driver and tractor
    	    selectedDriver.setAllocated(true);
    	    driverService.update(selectedDriver);
    	    
    	    selectedTractor.setAllocated(true);
    	    tractorService.update(selectedTractor);

    	    // Update the status in TodaysBooking table
    	    TodaysBooking todaysBooking = todaysBookingService.findById(bookingForm.getBookingId());
    	    if (todaysBooking != null) {
    	        todaysBooking.setStatus("Completed");
    	        todaysBookingService.update(todaysBooking);
    	    }

    	    // Redirect with success message
    	    redirectAttributes.addFlashAttribute("message", "Booking confirmed successfully!");
    	    return "redirect:/todays-bookings/fetch";
    	}

    @PostMapping("/reject-booking")
    public String rejectBooking(@RequestParam Long todaysBookingId, RedirectAttributes redirectAttributes) {
        TodaysBooking todaysBooking = todaysBookingService.findById(todaysBookingId);
        if (todaysBooking != null) {
            todaysBooking.setStatus("Rejected");
            todaysBookingService.saveTodaysBooking(todaysBooking);
        }

        redirectAttributes.addFlashAttribute("message", "Booking rejected successfully!");
        return "redirect:/todaysBookings"; 
    }
}