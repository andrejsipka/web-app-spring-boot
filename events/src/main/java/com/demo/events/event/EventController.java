package com.demo.events.event;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/users")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public List<Event> events() {
        return eventRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Event event(@PathVariable Integer id) {
        Optional<Event> event = eventRepository.findById(id);

        if(event.isEmpty()) {
            throw new RunNotFoundExtension();
        }

        return event.get();
    }

    // Post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@Valid  @RequestBody Event event) {
        eventRepository.create(event);
    }

    // Put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "/{id}")
    void update(@RequestBody Event event, @PathVariable Integer id) {
        eventRepository.update(event, id);
    }

    // Delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable Integer id) {
        eventRepository.delete(id);
    }
}
