package com.Sprout.app.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.Sprout.app.Entity.HourlyRecord;
import com.Sprout.app.Service.HourlyRecordService;

import java.util.List;

@Controller
public class ElectricalController {
    private final HourlyRecordService hourlyRecordService;

    
    public ElectricalController(HourlyRecordService hourlyRecordService) {
        this.hourlyRecordService = hourlyRecordService;
    }

    @PostMapping("/electrical/store-hourly-records")
    public ResponseEntity<String> storeHourlyRecords(@RequestBody List<HourlyRecord> records) {
        try {
            hourlyRecordService.saveHourlyRecords(records);
            return ResponseEntity.ok("Records saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save records: " + e.getMessage());
        }
    }
}

