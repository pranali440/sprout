package com.Sprout.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sprout.app.Entity.FertilizerData;
import com.Sprout.app.repository.FertilizerDataRepository;

import java.util.List;

@Service
public class FertilizerDataService {

    private final FertilizerDataRepository fertilizerDataRepository;

  
    public FertilizerDataService(FertilizerDataRepository fertilizerDataRepository) {
        this.fertilizerDataRepository = fertilizerDataRepository;
    }

    public List<FertilizerData> getAllFertilizerData() {
        return fertilizerDataRepository.findAll();
    }
    
}

