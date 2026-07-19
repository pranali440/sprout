package com.Sprout.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

/**
 * FileStorageService saves uploaded images to an "uploads" folder on disk
 * (relative to the working directory the app is started from), and stores
 * URLs like "/uploads/<uuid>.jpg" against each Achievement. Spring Boot only
 * serves static content from the classpath by default (e.g. static/, public/),
 * so without this handler every "/uploads/**" request 404s and images never
 * render, even though the files are sitting right there on disk.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadsPath = Paths.get("uploads").toAbsolutePath().normalize().toUri().toString();
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uploadsPath);
    }
}
