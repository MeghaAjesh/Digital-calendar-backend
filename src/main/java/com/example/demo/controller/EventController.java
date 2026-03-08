package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/events")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public List<Event> getEvents(){
        return eventRepository.findAll();
    }

 @PostMapping
public Object addEvent(@RequestBody Event event, @RequestParam String role){

    if(!role.equals("ADMIN")){
        return "Only admin can add events";
    }

    return eventRepository.save(event);
}

    @DeleteMapping("/{id}")
public String deleteEvent(@PathVariable Long id, @RequestParam String role){

    if(!role.equals("ADMIN")){
        return "Only admin can delete events";
    }

    eventRepository.deleteById(id);
    return "Event deleted";
}
}