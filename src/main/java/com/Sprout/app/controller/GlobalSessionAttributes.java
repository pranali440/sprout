package com.Sprout.app.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalSessionAttributes {

    @ModelAttribute("loggedInFarmerName")
    public String loggedInFarmerName(HttpSession session) {
        return (String) session.getAttribute("farmerName");
    }

    @ModelAttribute("isFarmerLoggedIn")
    public boolean isFarmerLoggedIn(HttpSession session) {
        return session.getAttribute("farmerEmail") != null;
    }
}