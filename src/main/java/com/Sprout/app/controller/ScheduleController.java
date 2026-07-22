package com.Sprout.app.controller;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Sprout.app.Entity.Booking;
import com.Sprout.app.Entity.Driver;
import com.Sprout.app.Entity.Farmer;
import com.Sprout.app.Entity.Owner;
import com.Sprout.app.Entity.Schedule;
import com.Sprout.app.Entity.ScheduleForm;
import com.Sprout.app.Entity.TodaysBooking;
import com.Sprout.app.Entity.Tractor;
import com.Sprout.app.Service.BookingService;
import com.Sprout.app.Service.DriverService;
import com.Sprout.app.Service.FarmerService;
import com.Sprout.app.Service.OwnerService;
import com.Sprout.app.Service.ScheduleService;
import com.Sprout.app.Service.TractorService;


@RequestMapping("/schedule")
@Controller
public class ScheduleController {
	
	 private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);
	
	@Autowired
	BookingService bookingService;
	@Autowired
	FarmerService farmerService;
	@Autowired
	ScheduleService scheduleService;
	@Autowired
	DriverService driverService;
	@Autowired
	TractorService tractorService;
	@Autowired
	OwnerService ownerService;

	 @GetMapping("/confirm_booking/fetch")
	    public String getTodaysBookings(Model model) {
		 LocalDate bookingDate = LocalDate.now();   
		 List<Booking> booking = bookingService.getBookings(bookingDate);
	        model.addAttribute("Bookings", booking);
	        for (Booking book : booking) {
	            
	            book.getFarmer();
	            book.getDriver();
	            book.getOwner();
	            book.getTractor();
	        }
	 return "Bookings";
	 }
	 
	 
	 @GetMapping("/today-schedules")
	    public String getTodaySchedules(Model model) {
	        LocalDate today = LocalDate.now();
	        List<Schedule> schedules = scheduleService.getPendingSchedulesForDate(today);
	        model.addAttribute("schedules", schedules);
	        return "TodaysSchedule"; 
	    }
	 
	 @GetMapping("/today-weight_schedules")
	    public String getTodayWeightSchedules(Model model) {
	        LocalDate today = LocalDate.now();
	        List<Schedule> schedules = scheduleService.getPendingWeightSchedulesForDate(today);
	        model.addAttribute("schedules", schedules);
	        return "TodaysWeightSchedule"; 
	    }
	 
	 @GetMapping("/today-crushings")
	    public String getTodayCrushingSchedules(Model model) {
	        LocalDate today = LocalDate.now();
	        List<Schedule> schedules = scheduleService.getPendingCrushingSchedulesForDate(today);
	        model.addAttribute("schedules", schedules);
	        return "crushing"; 
	    }
	 
	 @PostMapping("/schedule")
	    public String scheduleBookings(RedirectAttributes redirectAttributes) {
	        scheduleService.updateAndSaveBookings();
	        redirectAttributes.addFlashAttribute("message", "Bookings scheduled successfully");
	        return "redirect:/schedule/today-schedules";
	    }
	 
	 @GetMapping("/schedulebooking/{bookingId}")
	 public String viewBooking(@PathVariable("bookingId") Long bookingId, Model model) {
	     Booking booking = bookingService.getBookingById(bookingId);

	     
	     Farmer farmer = booking.getFarmer();
	     Owner owner = booking.getOwner();
	     Tractor tractor = booking.getTractor();
	     Driver driver = booking.getDriver();

	     model.addAttribute("booking", booking);
	     model.addAttribute("farmer", farmer);
	     model.addAttribute("owner", owner);
	     model.addAttribute("tractor", tractor);
	     model.addAttribute("driver", driver);
	     return "bookingform"; 
	 }
	 
	 @PostMapping("/schedule-confirm")
	 public String confirmBooking(@ModelAttribute ScheduleForm form, Model model, RedirectAttributes redirectAttributes,
			 @RequestParam("driverId") Integer driverId, 
             @RequestParam("tractorId") Integer tractorId,
             @RequestParam("ownerId") Long ownerId,
             @RequestParam("farmerId") Integer farmerId,
             @RequestParam("bookingId") Long bookingId,
             @RequestParam("nooftrolleys") Integer numberoftrolleys,
             @RequestParam("location") String location,
             @RequestParam LocalDate bookingDate,
             @RequestParam LocalTime bookingTime) {
		 
        Driver driver = driverService.findById(driverId);
        Tractor tractor = tractorService.findById(tractorId);
        Owner owner = ownerService.findById(ownerId);
        Farmer farmer = farmerService.findById(farmerId);	 
		 
	   
	    Schedule schedule = new Schedule();
	    schedule.setBookingId(bookingId);
	    schedule.setBookingDate(bookingDate);
	    schedule.setBookingTime(bookingTime);
	    schedule.setFarmer(farmer);
	    schedule.setDriver(driver);
	    schedule.setOwner(owner);
	    schedule.setTractor(tractor);
	    schedule.setLocation(location);
	    schedule.setNumberOfTrolleys(numberoftrolleys);
	    scheduleService.saveSchedule(schedule);
	    
	    Booking booking = bookingService.findById(bookingId);
	    if (booking != null) {
	        booking.setStatus("Completed");
	        bookingService.update(booking);
	    }

	    redirectAttributes.addFlashAttribute("message", "Booking confirmed successfully!");
	    return "redirect:/schedule/confirm_booking/fetch";
	}
}