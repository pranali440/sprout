

package com.Sprout.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sprout.app.Entity.Driver;
import com.Sprout.app.Service.DriverService;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
    
    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/add")
    public ResponseEntity<Driver> addTractor(@RequestBody Driver driver) {
    	Driver savedDriver = driverService.saveDriver(driver);
        return new ResponseEntity<>(savedDriver, HttpStatus.CREATED);
    }
}


