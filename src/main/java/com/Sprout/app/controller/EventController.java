package com.Sprout.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Sprout.app.Entity.Event;
import com.Sprout.app.Service.EventService;

import java.util.List;

@Controller
public class EventController {

    private final EventService eventService;

    
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public String showEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "events";
    }
    
    @PostMapping("/events/add")
    public String addEvent(@ModelAttribute Event event) {
        eventService.addEvent(event);
        return "redirect:/events";
    }
}

