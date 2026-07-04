package com.Sprout.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sprout.app.Entity.Crops;
import com.Sprout.app.repository.CropRepository;

import java.util.List;

@Service
public class CropService {
 @Autowired
 private CropRepository cropRepository;

 public List<Crops> getAllCrops() {
     return cropRepository.findAll();
 }
}

