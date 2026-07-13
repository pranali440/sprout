package com.Sprout.app.Service;

import java.util.List;

import com.Sprout.app.Entity.Weight;
import com.Sprout.app.repository.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeightService {

    @Autowired
    private WeightRepository weightRepository;

  
    public void saveWeight(Weight weight) {
        weightRepository.save(weight);
    }

    public List<Weight> getWeightsByFarmerId(Integer farmerId) {
        return weightRepository.findByFarmerId(farmerId);
    }
}