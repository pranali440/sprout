package com.Sprout.app.Service;



import org.springframework.stereotype.Service;

import com.Sprout.app.Entity.Village;
import com.Sprout.app.repository.VillageRepository;

@Service
public class VillageService {

    private final VillageRepository villageRepository;

    public VillageService(VillageRepository villageRepository) {
        this.villageRepository = villageRepository;
    }

    public Village saveVillage(Village village) {
        return villageRepository.save(village);
    }
    
    public Village findByName(String villageName) {
        return villageRepository.findByName(villageName);
    }

    public java.util.List<Village> getAllVillages() {
        return villageRepository.findAll(org.springframework.data.domain.Sort.by("name"));
    }
}