package com.Sprout.app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sprout.app.Entity.SugarFactoryData;
import com.Sprout.app.repository.SugarFactoryDataRepository;

@Service
public class SugarFactoryDataService {
    @Autowired
    private SugarFactoryDataRepository repository;

    public SugarFactoryData save(SugarFactoryData data) {
        return repository.save(data);
    }
    
    public List<SugarFactoryData> findBySeason(String season) {
        return repository.findBySeason(season);
    }

    // Other service methods as needed
}

