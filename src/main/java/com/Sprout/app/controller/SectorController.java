package com.Sprout.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sprout.app.Entity.Sector;
import com.Sprout.app.Service.SectorService;

@RestController
@RequestMapping("/api/sectors")
public class SectorController {

    private final SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @PostMapping("/add")
    public ResponseEntity<Sector> addSector(@RequestBody Sector sector) {
        Sector savedSector = sectorService.saveSector(sector);
        return new ResponseEntity<>(savedSector, HttpStatus.CREATED);
    }
}
