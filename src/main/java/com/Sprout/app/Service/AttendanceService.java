package com.Sprout.app.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Sprout.app.Entity.Attendance;

@Service
public class AttendanceService {

    private List<Attendance> dummyData = new ArrayList<>();

    public AttendanceService() {
        
        dummyData.add(new Attendance(1L, LocalDate.now(), "1001", "John Doe", "Present"));
        dummyData.add(new Attendance(2L, LocalDate.now(), "1002", "Jane Smith", "Absent"));
        dummyData.add(new Attendance(3L, LocalDate.now().minusDays(1), "1001", "John Doe", "Present"));
        dummyData.add(new Attendance(4L, LocalDate.now().minusDays(1), "1002", "Jane Smith", "Present"));
    }

    public List<Attendance> getAttendanceByDate(LocalDate date) {
        List<Attendance> result = new ArrayList<>();
        for (Attendance attendance : dummyData) {
            if (attendance.getDate().isEqual(date)) {
                result.add(attendance);
            }
        }
        return result;
    }

    public void markAttendance(Attendance attendance) {
        dummyData.add(attendance);
    }
}
