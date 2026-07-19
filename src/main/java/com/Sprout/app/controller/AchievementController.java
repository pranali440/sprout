package com.Sprout.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.Sprout.app.Entity.Achievement;
import com.Sprout.app.Service.AchievementService;
import com.Sprout.app.Service.FileStorageService;

import java.util.List;

@Controller
public class AchievementController {

    private final AchievementService achievementService;
    private final FileStorageService fileStorageService;
    
    public AchievementController(AchievementService achievementService,FileStorageService fileStorageService) {
        this.achievementService = achievementService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/achievements")
    public String showAchievements(Model model) {
        List<Achievement> achievements = achievementService.getAllAchievements();
        model.addAttribute("achievements", achievements);
        return "achievement";
    }

    @PostMapping("/achievements/add")
    public String addAchievement(@RequestParam("title") String title,
                                 @RequestParam("description") String description,
                                 @RequestParam("image") MultipartFile image) {
        achievementService.addAchievement(title, description, image);
        return "redirect:/achievements"; // Back to the achievements list, where the new one will appear
    }
}
