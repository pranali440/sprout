package com.Sprout.app.Service;


import com.Sprout.app.Entity.Schedule;
import com.Sprout.app.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    
    public List<Schedule> getPendingSchedulesForDate(LocalDate bookingDate) {
        List<Schedule>  schedules = scheduleRepository.findByBookingDateAndStatus(bookingDate, "pending");
      return schedules;
    }
    
    public List<Schedule> getPendingWeightSchedulesForDate(LocalDate bookingDate) {
        List<Schedule>  schedules = scheduleRepository.findByBookingDateAndStatus(bookingDate, "scheduled");
      return schedules;
    }
    
    public List<Schedule> getGroupedSchedulesByDate(LocalDate bookingDate) {
        List<Schedule> schedules = scheduleRepository.findByBookingDate(bookingDate);
        return schedules;
    }
    
    public List<Schedule> getPendingCrushingSchedulesForDate(LocalDate bookingDate) {
        List<Schedule>  schedules = scheduleRepository.findByBookingDateAndStatus(bookingDate, "completed");
      return schedules;
    }
    
    public void updateAndSaveBookings() {
        List<Schedule> schedules = scheduleRepository.findAll();
        for (Schedule schedule : schedules) {
            schedule.setStatus("scheduled");
            scheduleRepository.save(schedule);
        }
    }

    public void saveSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

	public Schedule getScheduleByFarmerId(Integer farmerId) {
		return scheduleRepository.getScheduledByscheduleId(farmerId);
	}

	
}
