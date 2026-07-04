package com.Sprout.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Sprout.app.Entity.Achievement;
import com.Sprout.app.repository.AchievementRepository;

import java.util.List;

@Service
public class AchievementService {
	
	@Autowired
	FileStorageService fileStorageService;

    private final AchievementRepository achievementRepository;

    
    public AchievementService(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

  
    
    public void addAchievement(String title, String description, MultipartFile image) {
        String imageUrl = fileStorageService.saveImageToFileSystem(image);
        if (imageUrl != null) {
            Achievement achievement = new Achievement(title, description, imageUrl);
            achievementRepository.save(achievement);
        } else {
            // Handle the case where image upload fails
            // You can log an error or throw an exception
        }
    }
}

