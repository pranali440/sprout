package com.Sprout.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Sprout.app.Entity.SugarFactoryData;
import com.Sprout.app.Service.SugarFactoryDataService;

@Controller
public class SugarFactoryDataController {
    @Autowired
    private SugarFactoryDataService service;

    @GetMapping("/dashboard")
    public String dashboard(Model model, @RequestParam(required = false, defaultValue = "2022-23") String season) {
        List<SugarFactoryData> factoryDataList = service.findBySeason(season);
        model.addAttribute("factoryDataList", factoryDataList);
        return "dashboard";
    }
}

