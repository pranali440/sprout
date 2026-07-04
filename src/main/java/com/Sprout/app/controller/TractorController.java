package com.Sprout.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sprout.app.Entity.Tractor;
import com.Sprout.app.Service.TractorService;

@RestController
@RequestMapping("/api/tractors")
public class TractorController {
    
    private final TractorService tractorService;

    public TractorController(TractorService tractorService) {
        this.tractorService = tractorService;
    }

    @PostMapping("/add")
    public ResponseEntity<Tractor> addTractor(@RequestBody Tractor tractor) {
        Tractor savedTractor = tractorService.saveTractor(tractor);
        return new ResponseEntity<>(savedTractor, HttpStatus.CREATED);
    }
}

