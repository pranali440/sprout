package com.Sprout.app.Service;


import org.springframework.stereotype.Service;

import com.Sprout.app.Entity.HourlyRecord;
import com.Sprout.app.repository.HourlyRecordRepository;

import java.util.List;

@Service
public class HourlyRecordService {
    private final HourlyRecordRepository repository;

   
    public HourlyRecordService(HourlyRecordRepository repository) {
        this.repository = repository;
    }

    public void saveHourlyRecords(List<HourlyRecord> records) {
        repository.saveAll(records);
    }
}

