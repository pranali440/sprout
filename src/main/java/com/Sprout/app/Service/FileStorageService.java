package com.Sprout.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    private final String uploadDir = "uploads"; // Directory to store uploaded files

    public String saveImageToFileSystem(MultipartFile image) {
        // Create directory if it doesn't exist
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Generate a unique filename for the image
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        String fileExtension = getFileExtension(fileName);
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

        // Path to save the image file
        Path filePath = Paths.get(uploadDir, uniqueFileName);

        try {
            // Copy the image file to the upload directory
            Files.copy(image.getInputStream(), filePath);
        } catch (IOException e) {
            e.printStackTrace(); 
            return null; 
        }

        // Return the image URL (relative path)
        return "/" + uploadDir + "/" + uniqueFileName;
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1) {
            return ""; 
        }
        return fileName.substring(dotIndex);
    }
}

