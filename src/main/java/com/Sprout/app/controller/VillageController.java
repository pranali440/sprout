package com.Sprout.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sprout.app.Entity.Village;
import com.Sprout.app.Service.VillageService;

@RestController
@RequestMapping("/api/villages")
public class VillageController {

    private final VillageService villageService;

    public VillageController(VillageService villageService) {
        this.villageService = villageService;
    }

    @PostMapping("/add")
    public ResponseEntity<Village> addVillage(@RequestBody Village village) {
        Village savedVillage = villageService.saveVillage(village);
        return new ResponseEntity<>(savedVillage, HttpStatus.CREATED);
    }
}

