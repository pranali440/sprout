package com.Sprout.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sprout.app.Entity.Event;
import com.Sprout.app.repository.EventRepository;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void addEvent(Event event) {
        eventRepository.save(event);
    }
}

