package com.Sprout.app.controller;

import com.Sprout.app.Entity.Driver;
import com.Sprout.app.Entity.Farmer;
import com.Sprout.app.Entity.Owner;
import com.Sprout.app.Entity.Schedule;
import com.Sprout.app.Entity.Tractor;
import com.Sprout.app.Entity.Weight;
import com.Sprout.app.Service.DriverService;
import com.Sprout.app.Service.FarmerService;
import com.Sprout.app.Service.OwnerService;
import com.Sprout.app.Service.ScheduleService;
import com.Sprout.app.Service.TractorService;
import com.Sprout.app.Service.WeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class WeightController {

    @Autowired
    private WeightService weightService;

    @Autowired
    private ScheduleService scheduleService;
    
    @Autowired
    private FarmerService farmerService;
    
    @Autowired
    private DriverService driverService;
    
    @Autowired
    private TractorService tractorService;
    
    @Autowired
    private OwnerService ownerService;

    
   
  
    
    @PostMapping("/weighingForm")
    public String showWeighingForm(Model model,
    		@RequestParam Integer farmerId,
    		@RequestParam Integer driverId,
    		@RequestParam Integer tractorId,
    		@RequestParam Long ownerId) {
    	
        Schedule schedule = scheduleService.getScheduleByFarmerId(farmerId);
        Farmer farmer = farmerService.findById(farmerId); 
        Driver driver = driverService.findById(driverId); 
        Tractor tractor = tractorService.findById(tractorId); 
        Owner owner = ownerService.findById(ownerId); 
        
        model.addAttribute("farmer", farmer);
        model.addAttribute("driver", driver);
        model.addAttribute("tractor", tractor);
        model.addAttribute("owner", owner);
        model.addAttribute("schedule",schedule);
        
        return "weighingform"; 
    }

    @PostMapping("/saveWeight")
    public String saveWeightAndStatus(@RequestParam("farmerId") Integer farmerId,
                                      @RequestParam("trolley1Weight") double trolley1Weight,
                                      @RequestParam("trolley2Weight") double trolley2Weight,
                                      @RequestParam("totalWeight") double totalWeight) {
        
        Weight weight = new Weight();
        weight.setFarmerId(farmerId);
        weight.setTrolley1Weight(trolley1Weight);
        weight.setTrolley2Weight(trolley2Weight);
        weight.setTotalWeight(totalWeight);
        weightService.saveWeight(weight);

        
        Schedule schedule = scheduleService.getScheduleByFarmerId(farmerId);
        Driver driver = schedule.getDriver();
        driver.setAllocated(false);
        Tractor tractor = schedule.getTractor();
        tractor.setAllocated(false);
        schedule.setStatus("completed");
        scheduleService.saveSchedule(schedule);

        return "redirect:/todayCaneBookings"; 
    }
}

