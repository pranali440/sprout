package com.Sprout.app.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Sprout.app.Entity.Attendance;
import com.Sprout.app.Service.AttendanceService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Attendance>> getAttendanceByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Attendance> attendanceList = attendanceService.getAttendanceByDate(date);
        return ResponseEntity.ok(attendanceList);
    }

    @PostMapping("/mark")
    public ResponseEntity<Void> markAttendance(@RequestBody Attendance attendance) {
        attendanceService.markAttendance(attendance);
        return ResponseEntity.ok().build();
    }
}

