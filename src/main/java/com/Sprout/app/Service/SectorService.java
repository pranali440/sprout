package com.Sprout.app.Service;

import org.springframework.stereotype.Service;

import com.Sprout.app.Entity.Sector;
import com.Sprout.app.repository.SectorRepository;

@Service
public class SectorService {

    private final SectorRepository sectorRepository;

    public SectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    public Sector saveSector(Sector sector) {
        return sectorRepository.save(sector);
    }

    public java.util.List<Sector> getAllSectors() {
        return sectorRepository.findAll(org.springframework.data.domain.Sort.by("name"));
    }
}